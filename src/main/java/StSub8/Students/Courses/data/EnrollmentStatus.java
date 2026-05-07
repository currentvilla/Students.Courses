package StSub8.Students.Courses.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class EnrollmentStatus {

  @Schema(description = "申込状況ID", example = "status123")
  private String id;

  @Schema(description = "受講生コース情報ID", example = "course123")
  @NotBlank
  private String studentCourseId;

  @Schema(description = "申込状況", example = "仮申込")
  @NotNull
  private Status status;
}
