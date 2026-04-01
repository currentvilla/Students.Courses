package StSub8.Students.Courses.data;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  @NotBlank
  private String id;
  @NotBlank
  private String fullName;
  @NotBlank
  private String furigana;
  @NotBlank
  private String nickname;
  @NotBlank
  private String email;
  @NotBlank
  private String area;
  @NotBlank
  private Integer age;
  @NotBlank
  private String gender;
  private String remark;
  private Boolean isDeleted;

  private List<StudentCourse> courses;

}
