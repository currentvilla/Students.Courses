package StSub8.Students.Courses.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class Student {

  @Schema(description = "受講生ID", example = "abc123")
  @NotBlank
  @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "IDは半角英数字のみ使用できます")
  private String id;

  @Schema(description = "氏名", example = "山田太郎")
  @NotBlank
  private String fullName;

  @NotBlank
  private String furigana;

  @NotBlank
  private String nickname;

  @NotBlank
  @Email(message = "メールアドレスの形式が正しくありません")
  private String email;

  @NotBlank
  private String area;

  @Schema(description = "年齢", example = "25")
  @NotNull
  @Min(value = 0, message = "年齢は0以上で入力してください")
  @Max(value = 120, message = "年齢は120以下で入力してください")
  private Integer age;

  @NotBlank
  private String gender;

  private String remark;

  @Schema(description = "削除フラグ", example = "false")
  private Boolean isDeleted;

}
