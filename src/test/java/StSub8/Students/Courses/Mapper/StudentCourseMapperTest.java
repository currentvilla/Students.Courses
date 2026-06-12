package StSub8.Students.Courses.Mapper;

import static org.assertj.core.api.Assertions.assertThat;

import StSub8.Students.Courses.data.StudentCourse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

@MybatisTest
class StudentCourseMapperTest {

  @Autowired
  private StudentCourseMapper sut;

  @Test
  void コース情報の全件検索が行えること() {
    List<StudentCourse> actual = sut.findAllCourses();
    assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void コースIDで検索できること() {
    StudentCourse actual = sut.findById("SC-001");
    assertThat(actual.getCourseName()).isEqualTo("Javaフルコース");
  }

  @Test
  void 受講生IDでコースを検索できること() {
    List<StudentCourse> actual = sut.findByStudentId("1");  // String型
    assertThat(actual.size()).isEqualTo(1);
    assertThat(actual.get(0).getCourseName()).isEqualTo("Javaフルコース");
  }

  @Test
  void コース情報の登録が行えること() {
    StudentCourse course = new StudentCourse();
    course.setId("SC-006");
    course.setStudentId("1");  // String型
    course.setCourseName("テストコース");
    course.setStartDate(null);
    course.setExpectedEndDate(null);

    sut.insert(course);

    List<StudentCourse> actual = sut.findAllCourses();
    assertThat(actual.size()).isEqualTo(6);
  }

  @Test
  void コース情報の更新が行えること() {
    StudentCourse course = sut.findById("SC-001");
    course.setCourseName("Javaアドバンスコース");

    sut.update(course);

    StudentCourse actual = sut.findById("SC-001");
    assertThat(actual.getCourseName()).isEqualTo("Javaアドバンスコース");
  }

  @Test
  void コース情報の削除が行えること() {
    sut.delete("SC-001");

    List<StudentCourse> actual = sut.findAllCourses();
    assertThat(actual.size()).isEqualTo(4);
  }

  @Test
  void 受講生IDでコースを削除できること() {
    sut.deleteByStudentId("1");  // String型

    List<StudentCourse> actual = sut.findByStudentId("1");
    assertThat(actual.size()).isEqualTo(0);
  }
}