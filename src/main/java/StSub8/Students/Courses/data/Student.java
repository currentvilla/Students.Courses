package StSub8.Students.Courses.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class Student {

  @Schema(description = "受講生ID", example = "abc123")
  @NotBlank
  private String id;
  @Schema(description = "氏名", example = "山田太郎")
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
  @Schema(description = "年齢", example = "25")
  @NotNull
  private Integer age;
  @NotBlank
  private String gender;
  private String remark;
  @Schema(description = "削除フラグ", example = "false")
  private Boolean isDeleted;

  private List<StudentCourse> courses;

}
