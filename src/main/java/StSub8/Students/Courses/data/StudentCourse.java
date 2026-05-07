package StSub8.Students.Courses.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class StudentCourse {

  @Schema(description = "コースID", example = "course123")
  private String id;

  @Schema(description = "受講生ID", example = "abc123")
  private String studentId;  // 外部キー

  @Schema(description = "コース名", example = "Javaコース")
  @NotBlank(message = "コース名は必須です")
  private String courseName;

  @Schema(description = "受講開始日")
  @NotNull(message = "開始日は必須です")
  private LocalDateTime startDate;

  @Schema(description = "受講終了予定日")
  @NotNull(message = "終了予定日は必須です")
  @FutureOrPresent(message = "終了予定日は現在以降の日付を入力してください")
  private LocalDateTime expectedEndDate;

  @Schema(description = "申込状況")
  private EnrollmentStatus enrollmentStatus;
}
