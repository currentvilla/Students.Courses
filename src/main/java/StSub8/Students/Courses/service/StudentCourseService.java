package StSub8.Students.Courses.service;

import StSub8.Students.Courses.Mapper.StudentCourseMapper;
import StSub8.Students.Courses.data.StudentCourse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService {

  private final StudentCourseMapper courseMapper;

  @Autowired
  public StudentCourseService(StudentCourseMapper courseMapper) {
    this.courseMapper = courseMapper;
  }

  /**
   * 全コース一覧を取得
   */
  public List<StudentCourse> getAllCourses() {
    return courseMapper.findAllCourses();
  }

  /**
   * 特定の受講生のコース一覧を取得
   */
  public List<StudentCourse> getCoursesByStudentId(String studentId) {
    return courseMapper.findByStudentId(studentId);
  }

  /**
   * コースの登録
   */
  public void registerCourse(StudentCourse course) {
    courseMapper.insert(course);
  }

  /**
   * コースの更新
   */
  public void updateCourse(StudentCourse course) {
    courseMapper.update(course);
  }

  /**
   * コースの削除
   */
  public void deleteCourse(String id) {
    courseMapper.delete(id);
  }

  /**
   * Javaコースのみを抽出（課題用）
   */
  public List<StudentCourse> getJavaCourses() {
    return courseMapper.findAllCourses().stream()
        .filter(course -> "Java".equals(course.getCourseName()))
        .collect(Collectors.toList());
  }
}