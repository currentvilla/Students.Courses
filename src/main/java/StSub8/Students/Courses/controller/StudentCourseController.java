package StSub8.Students.Courses.controller;

import StSub8.Students.Courses.data.StudentCourse;
import StSub8.Students.Courses.service.StudentCourseService;
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
public class StudentCourseController {

  private final StudentCourseService service;

  @Autowired
  public StudentCourseController(StudentCourseService service) {
    this.service = service;
  }

  /**
   * 全コース一覧
   */
  @GetMapping("/courses")
  public List<StudentCourse> getAllCourses() {
    return service.getAllCourses();
  }

  /**
   * 受講生のコース一覧を取得
   */
  @GetMapping("/student/courses")
  public List<StudentCourse> getStudentCourses(@RequestParam String studentId) {
    return service.getCoursesByStudentId(studentId);
  }

  /**
   * コースの登録
   */
  @PostMapping("/course")
  public void registerCourse(@RequestBody StudentCourse course) {
    service.registerCourse(course);
  }

  /**
   * コースの更新
   */
  @PatchMapping("/course")
  public void updateCourse(@RequestBody StudentCourse course) {
    service.updateCourse(course);
  }

  /**
   * コースの削除
   */
  @DeleteMapping("/course")
  public void deleteCourse(@RequestParam String id) {
    service.deleteCourse(id);
  }
}