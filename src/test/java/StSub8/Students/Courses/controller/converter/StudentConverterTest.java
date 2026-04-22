package StSub8.Students.Courses.controller.converter;

import StSub8.Students.Courses.data.Student;
import StSub8.Students.Courses.data.StudentCourse;
import StSub8.Students.Courses.domain.StudentDetail;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StudentConverterTest {

  private StudentConverter sut = new StudentConverter();

  @Test
  void 受講生とコースが正しく紐づいて返ること() {
    Student student = new Student();
    student.setId("abc123");

    StudentCourse course = new StudentCourse();
    course.setStudentId("abc123");
    course.setCourseName("Javaコース");

    List<Student> students = List.of(student);
    List<StudentCourse> studentCourses = List.of(course);

    List<StudentDetail> result = sut.convertStudentDetails(students, studentCourses);

    Assertions.assertEquals(1, result.size());
    Assertions.assertEquals("abc123", result.get(0).getStudent().getId());
    Assertions.assertEquals(1, result.get(0).getStudentCourses().size());
    Assertions.assertEquals("Javaコース", result.get(0).getStudentCourses().get(0).getCourseName());
  }

  @Test
  void 受講生に紐づかないコースは含まれないこと() {
    Student student = new Student();
    student.setId("abc123");

    StudentCourse course = new StudentCourse();
    course.setStudentId("xyz999");  // 別の受講生のコース
    course.setCourseName("Javaコース");

    List<Student> students = List.of(student);
    List<StudentCourse> studentCourses = List.of(course);

    List<StudentDetail> result = sut.convertStudentDetails(students, studentCourses);

    Assertions.assertEquals(1, result.size());
    Assertions.assertEquals(0, result.get(0).getStudentCourses().size());
  }

  @Test
  void 受講生が空のとき空のリストが返ること() {
    List<StudentDetail> result = sut.convertStudentDetails(List.of(), List.of());

    Assertions.assertEquals(0, result.size());
  }
}