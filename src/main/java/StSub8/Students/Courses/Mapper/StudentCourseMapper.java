package StSub8.Students.Courses.Mapper;

import StSub8.Students.Courses.data.StudentCourse;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * コース情報を扱うMapper
 */
@Mapper
public interface StudentCourseMapper {

  /**
   * コースの全件検索
   */
  @Select("SELECT * FROM students_courses")
  List<StudentCourse> findAllCourses();

  /**
   * IDでコース検索
   */
  @Select("SELECT * FROM students_courses WHERE id = #{id}")
  StudentCourse findById(String id);

  /**
   * 特定の受講生のコース一覧を取得
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId} ORDER BY start_date")
  List<StudentCourse> findByStudentId(String studentId);

  /**
   * コースの登録
   */
  @Insert(
      "INSERT INTO students_courses (id, student_id, course_name, start_date, expected_end_date) "
          + "VALUES (#{id}, #{studentId}, #{courseName}, #{startDate}, #{expectedEndDate})")
  void insert(StudentCourse studentCourse);

  /**
   * コースの更新
   */
  @Update("UPDATE students_courses SET course_name = #{courseName} WHERE id = #{id}")
  void update(StudentCourse studentCourse);

  /**
   * コースの削除
   */
  @Delete("DELETE FROM students_courses WHERE id = #{id}")
  void delete(String id);

  /**
   * 受講生のコースを一括削除
   */
  @Delete("DELETE FROM students_courses WHERE student_id = #{studentId}")
  void deleteByStudentId(String studentId);
}