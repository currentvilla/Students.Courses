package StSub8.Students.Courses.Mapper;

import static org.assertj.core.api.Assertions.assertThat;

import StSub8.Students.Courses.data.Student;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

@MybatisTest
class StudentMapperTest {

  @Autowired
  private StudentMapper sut;

  @Test
  void 受講生情報の全件検索が行えること() {
    List<Student> actual = sut.findAll();
    assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void IDで受講生を検索できること() {
    String id = sut.findAll().get(0).getId();

    Student actual = sut.findById(id);

    assertThat(actual.getFullName()).isEqualTo("田中 太郎");
  }

  @Test
  void 受講生の登録が行えること() {
    Student student = new Student();
    student.setFullName("山田太郎");
    student.setFurigana("ヤマダタロウ");
    student.setNickname("タロウ");
    student.setEmail("test@example.com");
    student.setArea("東京");
    student.setAge(25);
    student.setGender("男性");
    student.setRemark("");
    student.setIsDeleted(false);

    sut.insert(student);

    List<Student> actual = sut.findAll();

    assertThat(actual.size()).isEqualTo(6);
  }

  @Test
  void 受講生情報の更新が行えること() {
    Student student = sut.findAll().get(0);
    student.setFullName("田中 次郎");
    student.setEmail("jiro.tanaka@example.com");

    sut.update(student);

    Student actual = sut.findById(student.getId());
    assertThat(actual.getFullName()).isEqualTo("田中 次郎");
    assertThat(actual.getEmail()).isEqualTo("jiro.tanaka@example.com");
  }

  @Test
  void 受講生の論理削除が行えること() {
    String id = sut.findAll().get(0).getId();

    sut.delete(id);

    Student actual = sut.findById(id);
    assertThat(actual.getIsDeleted()).isTrue();
  }
}
