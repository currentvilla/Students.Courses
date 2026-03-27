package StSub8.Students.Courses.controller;

import StSub8.Students.Courses.controller.converter.StudentConverter;
import StSub8.Students.Courses.data.Student;
import StSub8.Students.Courses.data.StudentCourse;
import StSub8.Students.Courses.domain.StudentDetail;
import StSub8.Students.Courses.service.StudentCourseService;
import StSub8.Students.Courses.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
  @GetMapping("/students")
  public List<StudentDetail> getAllStudents() {
    List<Student> students = studentService.getAllStudents();
    List<StudentCourse> studentCourses = studentCourseService.getAllCourses();
    return converter.convertStudentDetails(students, studentCourses);
  }

  /**
   * 受講生をIDで検索
   */
  @GetMapping("/student")
  public Student getStudent(@RequestParam String id) {
    return studentService.getStudentById(id);
  }

  /**
   * 受講生の登録
   */
  @PostMapping("/student")
  public void registerStudent(@RequestBody StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();
    student.setIsDeleted(false);
    studentService.registerStudent(student);

    List<StudentCourse> courses = studentDetail.getStudentCourses();
    if (courses != null && !courses.isEmpty()) {
      for (StudentCourse course : courses) {
        course.setStudentId(student.getId());
        studentCourseService.registerCourse(course);
      }
    }
  }

  /**
   * 受講生の更新
   */
  @PatchMapping("/student")
  public void updateStudent(@RequestBody StudentDetail studentDetail) {
    studentService.updateStudent(studentDetail.getStudent());

    List<StudentCourse> courses = studentDetail.getStudentCourses();
    if (courses != null) {
      for (StudentCourse course : courses) {
        studentCourseService.updateCourse(course);
      }
    }
  }

  /**
   * 受講生の削除
   */
  @DeleteMapping("/student")
  public void deleteStudent(@RequestParam String id) {
    studentService.deleteStudent(id);
  }
}