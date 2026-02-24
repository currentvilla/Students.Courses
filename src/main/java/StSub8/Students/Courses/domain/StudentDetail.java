package StSub8.Students.Courses.domain;

import StSub8.Students.Courses.data.Student;
import StSub8.Students.Courses.data.StudentCourse;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDetail {

  private Student student;
  private List<StudentCourse> studentCourses;
}
