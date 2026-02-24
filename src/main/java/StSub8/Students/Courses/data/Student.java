package StSub8.Students.Courses.data;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  private String id;
  private String fullName;
  private String furigana;
  private String nickname;
  private String email;
  private String area;
  private Integer age;
  private String gender;
  private String remark;
  private Boolean isDeleted;

  private List<StudentCourse> courses;

}
