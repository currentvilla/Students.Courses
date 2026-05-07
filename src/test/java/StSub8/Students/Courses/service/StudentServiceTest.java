package StSub8.Students.Courses.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import StSub8.Students.Courses.Mapper.StudentCourseMapper;
import StSub8.Students.Courses.Mapper.StudentMapper;
import StSub8.Students.Courses.data.Student;
import StSub8.Students.Courses.data.StudentCourse;
import StSub8.Students.Courses.data.StudentSearchCriteria;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentMapper mapper;

  @Mock
  private StudentCourseMapper courseMapper;

  private StudentService sut;

  @BeforeEach
  void before() {
    sut = new StudentService(mapper, courseMapper);
  }

  @Test
  void 受講生全件検索_全件検索の動作検証() {
    List<Student> studentList = new ArrayList<>();
    when(mapper.findAll()).thenReturn(studentList);

    sut.getAllStudents();

    verify(mapper, times(1)).findAll();
  }

  @Test
  void 受講生ID検索_コース情報の取得検証() {
    Student student = new Student();
    student.setId("abc123");

    List<StudentCourse> courses = new ArrayList<>();
    StudentCourse course = new StudentCourse();
    course.setStudentId("abc123");
    courses.add(course);

    when(mapper.findById("abc123")).thenReturn(student);
    when(courseMapper.findByStudentId("abc123")).thenReturn(courses);

    Student actual = sut.getStudentById("abc123");

    verify(mapper, times(1)).findById("abc123");
    verify(courseMapper, times(1)).findByStudentId("abc123");
    Assertions.assertEquals(courses, actual.getCourses());
  }

  @Test
  void 受講生ID検索_IDが異常な場合の動作検証() {
    when(mapper.findById("notExist")).thenReturn(null);

    Student actual = sut.getStudentById("notExist");

    verify(mapper, times(1)).findById("notExist");
    verify(courseMapper, times(0)).findByStudentId("notExist");
    Assertions.assertNull(actual);
  }

  @Test
  void 受講生登録_登録処理の動作検証() {
    Student student = new Student();

    sut.registerStudent(student);

    verify(mapper, times(1)).insert(student);
  }

  @Test
  void 受講生更新_更新処理の動作検証() {
    Student student = new Student();

    sut.updateStudent(student);

    verify(mapper, times(1)).update(student);
  }

  @Test
  void searchStudents_条件で受講生を検索できること() {
    StudentSearchCriteria criteria = new StudentSearchCriteria();
    criteria.setFullName("山田");

    List<Student> studentList = new ArrayList<>();
    Student student = new Student();
    student.setId("abc123");
    student.setFullName("山田太郎");
    studentList.add(student);

    when(mapper.findByConditions(criteria)).thenReturn(studentList);

    List<Student> actual = sut.searchStudents(criteria);

    verify(mapper, times(1)).findByConditions(criteria);
    Assertions.assertEquals(1, actual.size());
    Assertions.assertEquals("山田太郎", actual.get(0).getFullName());
  }

  @Test
  void searchStudents_条件に一致しない場合空のリストが返ること() {
    StudentSearchCriteria criteria = new StudentSearchCriteria();
    criteria.setFullName("存在しない名前");

    when(mapper.findByConditions(criteria)).thenReturn(new ArrayList<>());

    List<Student> actual = sut.searchStudents(criteria);

    verify(mapper, times(1)).findByConditions(criteria);
    Assertions.assertEquals(0, actual.size());
  }
}