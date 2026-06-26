package StSub8.Students.Courses.service;

import StSub8.Students.Courses.Mapper.StudentCourseMapper;
import StSub8.Students.Courses.data.StudentCourse;
import java.util.List;
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
   * 全コース一覧を取得（enrollment_statusをJOINで同時取得）
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
    if (course.getId() == null) {
      throw new IllegalArgumentException("courseのidが必要です");
    }
    courseMapper.update(course);
  }

  /**
   * コースの削除
   */
  public void deleteCourse(String id) {
    courseMapper.delete(id);
  }
}