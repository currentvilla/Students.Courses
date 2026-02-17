package StSub8.Students.Courses.data;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCourse {

  private String id;
  private String studentId;  // 外部キー
  private String courseName;
  private LocalDateTime startDate;
  private LocalDateTime expectedEndDate;
}
