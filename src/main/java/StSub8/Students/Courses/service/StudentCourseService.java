package StSub8.Students.Courses.service;

import StSub8.Students.Courses.Mapper.EnrollmentStatusMapper;
import StSub8.Students.Courses.Mapper.StudentCourseMapper;
import StSub8.Students.Courses.data.EnrollmentStatus;
import StSub8.Students.Courses.data.StudentCourse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService {

  private final StudentCourseMapper courseMapper;
  private final EnrollmentStatusMapper enrollmentStatusMapper;

  @Autowired
  public StudentCourseService(StudentCourseMapper courseMapper,
      EnrollmentStatusMapper enrollmentStatusMapper) {
    this.courseMapper = courseMapper;
    this.enrollmentStatusMapper = enrollmentStatusMapper;
  }

  /**
   * 全コース一覧を取得
   */
  public List<StudentCourse> getAllCourses() {
    List<StudentCourse> courses = courseMapper.findAllCourses();
    courses.forEach(course -> {
      EnrollmentStatus status = enrollmentStatusMapper.findByStudentCourseId(course.getId());
      course.setEnrollmentStatus(status);
    });
    return courses;
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
}