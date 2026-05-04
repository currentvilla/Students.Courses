package StSub8.Students.Courses.service;

import StSub8.Students.Courses.Mapper.EnrollmentStatusMapper;
import StSub8.Students.Courses.data.EnrollmentStatus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentStatusService {

  private final EnrollmentStatusMapper enrollmentStatusMapper;

  @Autowired
  public EnrollmentStatusService(EnrollmentStatusMapper enrollmentStatusMapper) {
    this.enrollmentStatusMapper = enrollmentStatusMapper;
  }

  /**
   * 申込状況の全件検索
   */
  public List<EnrollmentStatus> getAllEnrollmentStatuses() {
    return enrollmentStatusMapper.findAll();
  }

  /**
   * 受講生コースIDで申込状況を検索
   */
  public EnrollmentStatus getEnrollmentStatusByStudentCourseId(String studentCourseId) {
    return enrollmentStatusMapper.findByStudentCourseId(studentCourseId);
  }

  /**
   * 申込状況の登録
   */
  public void registerEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
    enrollmentStatusMapper.insert(enrollmentStatus);
  }

  /**
   * 申込状況の更新
   */
  public void updateEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
    enrollmentStatusMapper.update(enrollmentStatus);
  }
}
