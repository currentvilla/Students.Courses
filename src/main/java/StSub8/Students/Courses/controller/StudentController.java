package StSub8.Students.Courses.controller;

import StSub8.Students.Courses.controller.converter.StudentConverter;
import StSub8.Students.Courses.data.Student;
import StSub8.Students.Courses.data.StudentCourse;
import StSub8.Students.Courses.domain.StudentDetail;
import StSub8.Students.Courses.exception.GlobalExceptionHandler.ResourceNotFoundException;
import StSub8.Students.Courses.service.StudentCourseService;
import StSub8.Students.Courses.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Validated
@Tag(name = "受講生管理", description = "受講生の登録・検索・更新・削除")
@RestController
public class StudentController {

  private final StudentService studentService;
  private final StudentCourseService studentCourseService;
  private final StudentConverter converter;

  @Autowired
  public StudentController(StudentService studentService,
      StudentCourseService studentCourseService,
      StudentConverter converter) {
    this.studentService = studentService;
    this.studentCourseService = studentCourseService;
    this.converter = converter;
  }

  /**
   * 受講生の全件検索
   */
  @Operation(summary = "一覧検索", description = "受講生の一覧を検索します。")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "取得成功")})
  @GetMapping("/students")
  public List<StudentDetail> getAllStudents() {
    List<Student> students = studentService.getAllStudents();
    List<StudentCourse> studentCourses = studentCourseService.getAllCourses();
    return converter.convertStudentDetails(students, studentCourses);
  }

  /**
   * 受講生をIDで検索
   */
  @Operation(summary = "受講生ID検索", description = "指定したIDの受講生を1件返します。")
  @GetMapping("/student")
  public Student getStudent(@RequestParam String id) {
    Student student = studentService.getStudentById(id);
    if (student == null) {
      throw new ResourceNotFoundException("受講生が見つかりません。ID: " + id);
    }
    return student;
  }

  /**
   * 受講生の登録
   */
  @Operation(summary = "受講生登録", description = "新しい受講生とコース情報を登録します。")
  @PostMapping("/student")
  public ResponseEntity<?> registerStudent(
      @Valid @RequestBody StudentDetail studentDetail,
      BindingResult bindingResult) {

    // 入力チェック
    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }

    // nullチェック（業務的に必要な最低限）
    if (studentDetail.getStudent() == null) {
      return ResponseEntity.badRequest().body("studentが必要です");
    }

    Student student = studentDetail.getStudent();
    student.setIsDeleted(false);
    studentService.registerStudent(student);

    List<StudentCourse> courses = studentDetail.getStudentCourses();
    if (courses != null && !courses.isEmpty()) {
      for (StudentCourse course : courses) {

        // 簡単なチェック（例：コース名）
        if (course.getCourseName() == null || course.getCourseName().isBlank()) {
          return ResponseEntity.badRequest().body("courseNameは必須です");
        }

        course.setStudentId(student.getId());
        studentCourseService.registerCourse(course);
      }
    }

    return ResponseEntity.ok().build();
  }

  /**
   * 受講生の更新
   */
  @Operation(summary = "受講生更新", description = "指定した受講生とコース情報を更新します。")
  @PatchMapping("/student")
  public ResponseEntity<?> updateStudent(
      @Valid @RequestBody StudentDetail studentDetail,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }

    if (studentDetail.getStudent() == null) {
      return ResponseEntity.badRequest().body("studentが必要です");
    }

    studentService.updateStudent(studentDetail.getStudent());

    List<StudentCourse> courses = studentDetail.getStudentCourses();
    if (courses != null) {
      for (StudentCourse course : courses) {

        if (course.getId() == null) {
          return ResponseEntity.badRequest().body("courseのidが必要です");
        }

        studentCourseService.updateCourse(course);
      }
    }

    return ResponseEntity.ok().build();
  }


  /**
   * 受講生の削除
   */
  @Operation(summary = "受講生削除", description = "指定したIDの受講生を削除します。")
  @DeleteMapping("/student")
  public ResponseEntity<?> deleteStudent(@RequestParam String id) {

    if (id == null || id.isBlank()) {
      return ResponseEntity.badRequest().body("idは必須です");
    }

    studentService.deleteStudent(id);
    return ResponseEntity.ok().build();
  }
}