package StSub8.Students.Courses.controller;

import StSub8.Students.Courses.controller.converter.StudentConverter;
import StSub8.Students.Courses.data.Student;
import StSub8.Students.Courses.data.StudentCourse;
import StSub8.Students.Courses.domain.StudentDetail;
import StSub8.Students.Courses.service.StudentCourseService;
import StSub8.Students.Courses.service.StudentService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {

  private final StudentService studentService;
  private final StudentCourseService studentCourseService;
  private final StudentConverter converter;

  @Autowired
  public StudentController(StudentService studentService, StudentCourseService studentCourseService,
      StudentConverter converter) {
    this.studentService = studentService;
    this.studentCourseService = studentCourseService;
    this.converter = converter;
  }

  /**
   * 受講生の全件検索
   */
  @GetMapping("/students")
  public String getAllStudents(Model model) {
    List<Student> students = studentService.getAllStudents();
    List<StudentCourse> studentCourses = studentCourseService.getAllCourses();

    model.addAttribute("studentList", converter.convertStudentDetails(students, studentCourses));
    return "studentList";
  }

  /**
   * 受講生をIDで検索（コース情報も含む）
   */
  @GetMapping("/student")
  @ResponseBody
  public Student getStudent(@RequestParam String id) {
    return studentService.getStudentById(id);
  }

  /**
   * 受講生を名前で検索
   */
  @GetMapping("/students/search")
  public List<Student> searchStudentByName(@RequestParam String name) {
    return studentService.searchStudentByName(name);
  }

  /**
   * 受講生の登録
   */
  @PostMapping("/student")
  public void registerStudent(
      @RequestParam String id,
      @RequestParam String fullName,
      @RequestParam String furigana,
      @RequestParam String nickname,
      @RequestParam String email,
      @RequestParam String area,
      @RequestParam int age,
      @RequestParam String gender,
      @RequestParam(required = false) String remark) {

    Student student = new Student();
    student.setId(id);
    student.setFullName(fullName);
    student.setFurigana(furigana);
    student.setNickname(nickname);
    student.setEmail(email);
    student.setArea(area);
    student.setAge(age);
    student.setGender(gender);
    student.setRemark(remark);

    studentService.registerStudent(student);
  }

  /**
   * 受講生の更新
   */
  @PatchMapping("/student")
  public void updateStudent(
      @RequestParam String id,
      @RequestParam String fullName,
      @RequestParam String furigana,
      @RequestParam String nickname,
      @RequestParam String email,
      @RequestParam String area,
      @RequestParam int age,
      @RequestParam String gender,
      @RequestParam(required = false) String remark) {

    Student student = new Student();
    student.setId(id);
    student.setFullName(fullName);
    student.setFurigana(furigana);
    student.setNickname(nickname);
    student.setEmail(email);
    student.setArea(area);
    student.setAge(age);
    student.setGender(gender);
    student.setRemark(remark);

    studentService.updateStudent(student);
  }

  /**
   * 受講生の削除
   */
  @DeleteMapping("/student")
  public void deleteStudent(@RequestParam String id) {
    studentService.deleteStudent(id);
  }


  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(new Student());

// 初期状態で1つのコース入力欄を用意
    List<StudentCourse> courses = new ArrayList<>();
    courses.add(new StudentCourse());
    studentDetail.setStudentCourses(courses);

    model.addAttribute("studentDetail", studentDetail);
    return "registerStudent";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if (result.hasErrors()) {
      return "registerStudent";
    }

    // 受講生情報を登録
    Student student = studentDetail.getStudent();
    student.setIsDeleted(false);
    studentService.registerStudent(student);

    // コース情報を登録
    List<StudentCourse> courses = studentDetail.getStudentCourses();
    if (courses != null && !courses.isEmpty()) {
      for (StudentCourse course : courses) {
        // 受講生IDを設定
        course.setStudentId(student.getId());
        studentCourseService.registerCourse(course);
      }
    }

    return "redirect:/students";
  }
}