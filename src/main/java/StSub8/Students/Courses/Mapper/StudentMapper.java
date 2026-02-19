package StSub8.Students.Courses.Mapper;

import StSub8.Students.Courses.data.Student;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 受講生情報を扱う
 *
 * 検索・登録・更新・削除を行う
 */
@Mapper
public interface StudentMapper {

  /**
   *
   * @return 受講生情報の全件検索
   */
  @Select("SELECT * FROM students")
  List<Student> findAll();

  /**
   * IDで検索
   */
  @Select("SELECT * FROM students WHERE id = #{id}")
  Student findById(String id);

  /**
   * 名前で検索
    */
  @Select("SELECT * FROM students WHERE full_name LIKE CONCAT('%', #{name}, '%')")
  List<Student> findByName(String name);

  /**
   * 受講生の登録
    */
  @Insert(
      "INSERT INTO students (id, full_name, furigana, nickname, email, area, age, gender) "
          + "VALUES (#{id}, #{fullName}, #{furigana}, #{nickname}, #{email}, #{area}, #{age}, #{gender})")
  void insert(Student student);

  /**
   * 受講生の更新
    */
  @Update(
      "UPDATE students SET full_name = #{fullName}, furigana = #{furigana}, nickname = #{nickname}, "
          + "email = #{email}, area = #{area}, age = #{age}, gender = #{gender} WHERE id = #{id}")
  void update(Student student);

  /**
   * 受講生の削除
    */
  @Delete("DELETE FROM students WHERE id = #{id}")
  void delete(String id);
}