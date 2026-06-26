package StSub8.Students.Courses.Mapper;

import StSub8.Students.Courses.data.Student;
import StSub8.Students.Courses.data.StudentSearchCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {

  /**
   * 受講生情報の全件検索
   */
  List<Student> findAll();

  /**
   * IDで検索
   */
  Student findById(String id);

  /**
   * 受講生の登録
   */
  void insert(Student student);

  /**
   * 受講生の更新
   */
  void update(Student student);

  /**
   * 受講生の削除
   */
  void delete(String id);

  /**
   * 条件で受講生を検索
   */
  List<Student> findByConditions(StudentSearchCriteria criteria);
}