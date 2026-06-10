package StSub8.Students.Courses.domain;

import StSub8.Students.Courses.data.Student;
import StSub8.Students.Courses.data.StudentCourse;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class StudentDetail {

  @NotNull(message = "studentは必須です")
  @Valid
  private Student student;
  private List<StudentCourse> studentCourses;
}
