package StSub8.Students.Courses;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@RestController
public class Application {

  @Autowired
  private StudentMapper studentMapper;

  @Autowired
  private StudentCourseMapper studentCourseMapper;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  // 受講生の全件検索
  @GetMapping("/students")
  public List<Student> getAllStudents() {
    return studentMapper.findAll();
  }

  // 受講生をIDで検索（コース情報も含む）
  @GetMapping("/student")
  public Student getStudent(@RequestParam String id) {
    Student student = studentMapper.findById(id);
    if (student != null) {
      List<StudentCourse> courses = studentCourseMapper.findByStudentId(id);
      student.setCourses(courses);
    }
    return student;
  }

  // 受講生を名前で検索
  @GetMapping("/students/search")
  public List<Student> searchStudentByName(@RequestParam String name) {
    return studentMapper.findByName(name);
  }

  // 受講生の登録
  @PostMapping("/student")
  public void registerStudent(
      @RequestParam String id,
      @RequestParam String fullName,
      @RequestParam String furigana,
      @RequestParam String nickname,
      @RequestParam String email,
      @RequestParam String area,
      @RequestParam int age,
      @RequestParam String gender) {

    Student student = new Student();
    student.setId(id);
    student.setFullName(fullName);
    student.setFurigana(furigana);
    student.setNickname(nickname);
    student.setEmail(email);
    student.setArea(area);
    student.setAge(age);
    student.setGender(gender);

    studentMapper.insert(student);
  }

  // 受講生の更新
  @PatchMapping("/student")
  public void updateStudent(
      @RequestParam String id,
      @RequestParam String fullName,
      @RequestParam String furigana,
      @RequestParam String nickname,
      @RequestParam String email,
      @RequestParam String area,
      @RequestParam int age,
      @RequestParam String gender) {

    Student student = new Student();
    student.setId(id);
    student.setFullName(fullName);
    student.setFurigana(furigana);
    student.setNickname(nickname);
    student.setEmail(email);
    student.setArea(area);
    student.setAge(age);
    student.setGender(gender);

    studentMapper.update(student);
  }

  // 受講生の削除
  @DeleteMapping("/student")
  public void deleteStudent(@RequestParam String id) {
    // コース情報も一緒に削除
    studentCourseMapper.deleteByStudentId(id);
    studentMapper.delete(id);
  }

  // 受講生のコース一覧を取得
  @GetMapping("/student/courses")
  public List<StudentCourse> getStudentCourses(@RequestParam String studentId) {
    return studentCourseMapper.findByStudentId(studentId);
  }

  // コースの登録
  @PostMapping("/course")
  public void registerCourse(
      @RequestParam String id,
      @RequestParam String studentId,
      @RequestParam String courseName,
      @RequestParam String startDate,
      @RequestParam String expectedEndDate) {

    StudentCourse course = new StudentCourse();
    course.setId(id);
    course.setStudentId(studentId);
    course.setCourseName(courseName);
    course.setStartDate(LocalDateTime.parse(startDate));
    course.setExpectedEndDate(LocalDateTime.parse(expectedEndDate));

    // 日時フォーマットを指定
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    course.setStartDate(LocalDateTime.parse(startDate, formatter));
    course.setExpectedEndDate(LocalDateTime.parse(expectedEndDate, formatter));

    studentCourseMapper.insert(course);

  }

  // コースの更新
  @PatchMapping("/course")
  public void updateCourse(
      @RequestParam String id,
      @RequestParam String courseName,
      @RequestParam String startDate,
      @RequestParam String expectedEndDate) {

    StudentCourse course = new StudentCourse();
    course.setId(id);
    course.setCourseName(courseName);
    course.setStartDate(LocalDateTime.parse(startDate));
    course.setExpectedEndDate(LocalDateTime.parse(expectedEndDate));

    studentCourseMapper.update(course);
  }

  // コースの削除
  @DeleteMapping("/course")
  public void deleteCourse(@RequestParam String id) {
    studentCourseMapper.delete(id);
  }

  // 全コース一覧
  @GetMapping("/courses")
  public List<StudentCourse> getAllCourses() {
    return studentCourseMapper.findAll();
  }
}
