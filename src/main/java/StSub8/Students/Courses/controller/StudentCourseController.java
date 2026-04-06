package StSub8.Students.Courses.controller;

import StSub8.Students.Courses.data.StudentCourse;
import StSub8.Students.Courses.service.StudentCourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "コース管理", description = "コースの登録・検索・更新・削除")
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
  @Operation(summary = "コース一覧取得", description = "登録されている全コースの一覧を返します。")
  @GetMapping("/courses")
  public List<StudentCourse> getAllCourses() {
    return service.getAllCourses();
  }

  /**
   * 受講生のコース一覧を取得
   */
  @Operation(summary = "受講生コース一覧取得", description = "指定した受講生IDに紐づくコースの一覧を返します。")
  @GetMapping("/student/courses")
  public List<StudentCourse> getStudentCourses(@RequestParam String studentId) {
    return service.getCoursesByStudentId(studentId);
  }

  /**
   * コースの登録
   */
  @Operation(summary = "コース登録", description = "新しいコースを登録します。")
  @PostMapping("/course")
  public void registerCourse(@RequestBody StudentCourse course) {
    service.registerCourse(course);
  }

  /**
   * コースの更新
   */
  @Operation(summary = "コース更新", description = "指定したコース情報を更新します。")
  @PatchMapping("/course")
  public void updateCourse(@RequestBody StudentCourse course) {
    service.updateCourse(course);
  }

  /**
   * コースの削除
   */
  @Operation(summary = "コース削除", description = "指定したIDのコースを削除します。")
  @DeleteMapping("/course")
  public void deleteCourse(@RequestParam String id) {
    service.deleteCourse(id);
  }
}