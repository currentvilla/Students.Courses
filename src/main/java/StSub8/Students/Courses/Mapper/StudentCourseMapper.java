package StSub8.Students.Courses.Mapper;

import StSub8.Students.Courses.data.StudentCourse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * コース情報を扱うMapper
 */
@Mapper
public interface StudentCourseMapper {

  /**
   * コースの全件検索
   */
  List<StudentCourse> findAllCourses();

  /**
   * IDでコース検索
   */
  StudentCourse findById(String id);

  /**
   * 特定の受講生のコース一覧を取得
   */
  List<StudentCourse> findByStudentId(String studentId);

  /**
   * コースの登録
   */
  void insert(StudentCourse studentCourse);

  /**
   * コースの更新
   */
  void update(StudentCourse studentCourse);

  /**
   * コースの削除
   */
  void delete(String id);

  /**
   * 受講生のコースを一括削除
   */
  void deleteByStudentId(String studentId);
}