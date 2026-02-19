package StSub8.Students.Courses.service;

import StSub8.Students.Courses.Mapper.StudentMapper;
import StSub8.Students.Courses.Mapper.StudentCourseMapper;
import StSub8.Students.Courses.data.Student;
import StSub8.Students.Courses.data.StudentCourse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private final StudentMapper studentMapper;
  private final StudentCourseMapper courseMapper;

  @Autowired
  public StudentService(StudentMapper studentMapper, StudentCourseMapper courseMapper) {
    this.studentMapper = studentMapper;
    this.courseMapper = courseMapper;
  }

  /**
   * 受講生の全件検索
   */
  public List<Student> getAllStudents() {
    return studentMapper.findAll();
  }

  /**
   * IDで受講生を検索（コース情報も含む）
   */
  public Student getStudentById(String id) {
    Student student = studentMapper.findById(id);
    if (student != null) {
      List<StudentCourse> courses = courseMapper.findByStudentId(id);
      student.setCourses(courses);
    }
    return student;
  }

  /**
   * 名前で受講生を検索
   */
  public List<Student> searchStudentByName(String name) {
    return studentMapper.findByName(name);
  }

  /**
   * 受講生の登録
   */
  public void registerStudent(Student student) {
    studentMapper.insert(student);
  }

  /**
   * 受講生の更新
   */
  public void updateStudent(Student student) {
    studentMapper.update(student);
  }

  /**
   * 受講生の削除（関連するコース情報も削除）
   */
  public void deleteStudent(String id) {
    courseMapper.deleteByStudentId(id);
    studentMapper.delete(id);
  }
}