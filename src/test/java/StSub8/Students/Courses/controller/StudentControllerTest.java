package StSub8.Students.Courses.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import StSub8.Students.Courses.controller.converter.StudentConverter;
import StSub8.Students.Courses.data.Student;
import StSub8.Students.Courses.domain.StudentDetail;
import StSub8.Students.Courses.service.StudentCourseService;
import StSub8.Students.Courses.service.StudentService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private StudentService studentService;

  @MockitoBean
  private StudentCourseService studentCourseService;

  @MockitoBean
  private StudentConverter converter;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void 受講生一覧検索字に空のリストが帰ってくること() throws Exception {
    when(converter.convertStudentDetails(any(), any())).thenReturn(List.of(new StudentDetail()));

    mockMvc.perform(MockMvcRequestBuilders.get("/students"))
        .andExpect(status().isOk());

    verify(studentService, times(1)).getAllStudents();
    verify(studentCourseService, times(1)).getAllCourses();
  }

  @Test
  void 受講生詳細の受講生でIDに数字以外を用いたときに入力チェックにかかること() {
    Student student = new Student();
    student.setId("テストです");
    student.setFullName("山田太郎");
    student.setFurigana("ヤマダタロウ");
    student.setNickname("タロウ");
    student.setEmail("test@example.com");
    student.setArea("東京");
    student.setAge(25);
    student.setGender("男性");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    Assertions.assertEquals(1, violations.size());
  }

  @Test
  void 受講生登録_正常に登録できること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/student")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                  "student": {
                    "id": "123",
                    "fullName": "山田太郎",
                    "furigana": "ヤマダタロウ",
                    "nickname": "タロウ",
                    "email": "test@example.com",
                    "area": "東京",
                    "age": 25,
                    "gender": "男性"
                  },
                  "studentCourses": []
                }
                """))
        .andExpect(status().isOk());

    verify(studentService, times(1)).registerStudent(any());
  }

  @Test
  void 受講生更新_正常に更新できること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.patch("/student")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                  "student": {
                    "id": "123",
                    "fullName": "山田太郎",
                    "furigana": "ヤマダタロウ",
                    "nickname": "タロウ",
                    "email": "test@example.com",
                    "area": "東京",
                    "age": 25,
                    "gender": "男性"
                  },
                  "studentCourses": []
                }
                """))
        .andExpect(status().isOk());

    verify(studentService, times(1)).updateStudent(any());
  }

  @Test
  void 受講生ID検索_データが返ってくること() throws Exception {
    Student student = new Student();
    student.setId("123");
    student.setFullName("山田太郎");

    when(studentService.getStudentById("123")).thenReturn(student);

    mockMvc.perform(MockMvcRequestBuilders.get("/student")
            .param("id", "123"))
        .andExpect(status().isOk())
        .andExpect(content().json("""
            {
              "id": "123",
              "fullName": "山田太郎"
            }
            """));
  }

  @Test
  void searchStudents_条件検索で200が返ること() throws Exception {
    when(studentService.searchStudents(any())).thenReturn(List.of());
    when(converter.convertStudentDetails(any(), any())).thenReturn(List.of());

    mockMvc.perform(MockMvcRequestBuilders.get("/students/search")
            .param("fullName", "山田")
            .param("area", "東京"))
        .andExpect(status().isOk());

    verify(studentService, times(1)).searchStudents(any());
  }

  @Test
  void searchStudents_条件なしで200が返ること() throws Exception {
    when(studentService.searchStudents(any())).thenReturn(List.of());
    when(converter.convertStudentDetails(any(), any())).thenReturn(List.of());

    mockMvc.perform(MockMvcRequestBuilders.get("/students/search"))
        .andExpect(status().isOk());

    verify(studentService, times(1)).searchStudents(any());
  }
}