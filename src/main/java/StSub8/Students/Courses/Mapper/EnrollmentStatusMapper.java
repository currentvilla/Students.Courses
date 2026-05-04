package StSub8.Students.Courses.Mapper;

import StSub8.Students.Courses.data.EnrollmentStatus;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EnrollmentStatusMapper {

  /**
   * 全件検索
   */
  List<EnrollmentStatus> findAll();

  /**
   * 受講生コースIDで検索
   */
  EnrollmentStatus findByStudentCourseId(String studentCourseId);

  /**
   * 申込状況の登録
   */
  void insert(EnrollmentStatus enrollmentStatus);

  /**
   * 申込状況の更新
   */
  void update(EnrollmentStatus enrollmentStatus);
}
