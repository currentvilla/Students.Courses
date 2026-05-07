package StSub8.Students.Courses.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class StudentSearchCriteria {

  @Schema(description = "氏名", example = "山田")
  private String fullName;

  @Schema(description = "エリア", example = "東京")
  private String area;

  @Schema(description = "コース名", example = "Javaコース")
  private String courseName;

  @Schema(description = "申込状況", example = "受講中")
  private Status status;
}
