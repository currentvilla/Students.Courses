package StSub8.Students.Courses.controller;

import StSub8.Students.Courses.controller.converter.StudentConverter;
import StSub8.Students.Courses.data.Student;
import StSub8.Students.Courses.data.StudentCourse;
import StSub8.Students.Courses.domain.StudentDetail;
import StSub8.Students.Courses.service.StudentCourseService;
import StSub8.Students.Courses.service.StudentService;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  private final StudentService studentService;
  private final StudentCourseService studentCourseService;
  private final StudentConverter converter;

  @Autowired
  public StudentController(StudentService studentService, StudentCourseService studentCourseService, StudentConverter converter) {
    this.studentService = studentService;
    this.studentCourseService = studentCourseService;
    this.converter = converter;
  }

  /**
   * 受講生の全件検索
   */
  @GetMapping("/students")
  public List<StudentDetail> getAllStudents() {
    List<Student> students = studentService.getAllStudents();
    List<StudentCourse> studentCourses = studentCourseService.getAllCourses();

    return converter.convertStudentDetails(students, studentCourses);
  }

  /**
   * 受講生をIDで検索（コース情報も含む）
   */
  @GetMapping("/student")
  public Student getStudent(@RequestParam String id) {
    return studentService.getStudentById(id);
  }

  /**
   * 受講生を名前で検索
   */
  @GetMapping("/students/search")
  public List<Student> searchStudentByName(@RequestParam String name) {
    return studentService.searchStudentByName(name);
  }

  /**
   * 受講生の登録
   */
  @PostMapping("/student")
  public void registerStudent(
      @RequestParam String id,
      @RequestParam String fullName,
      @RequestParam String furigana,
      @RequestParam String nickname,
      @RequestParam String email,
      @RequestParam String area,
      @RequestParam int age,
      @RequestParam String gender,
      @RequestParam(required = false) String remark) {

    Student student = new Student();
    student.setId(id);
    student.setFullName(fullName);
    student.setFurigana(furigana);
    student.setNickname(nickname);
    student.setEmail(email);
    student.setArea(area);
    student.setAge(age);
    student.setGender(gender);
    student.setRemark(remark);

    studentService.registerStudent(student);
  }

  /**
   * 受講生の更新
   */
  @PatchMapping("/student")
  public void updateStudent(
      @RequestParam String id,
      @RequestParam String fullName,
      @RequestParam String furigana,
      @RequestParam String nickname,
      @RequestParam String email,
      @RequestParam String area,
      @RequestParam int age,
      @RequestParam String gender,
      @RequestParam(required = false) String remark) {

    Student student = new Student();
    student.setId(id);
    student.setFullName(fullName);
    student.setFurigana(furigana);
    student.setNickname(nickname);
    student.setEmail(email);
    student.setArea(area);
    student.setAge(age);
    student.setGender(gender);
    student.setRemark(remark);

    studentService.updateStudent(student);
  }

  /**
   * 受講生の削除
   */
  @DeleteMapping("/student")
  public void deleteStudent(@RequestParam String id) {
    studentService.deleteStudent(id);
  }
}