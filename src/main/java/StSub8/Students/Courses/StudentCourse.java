package StSub8.Students.Courses;

import java.time.LocalDateTime;

public class StudentCourse {

  private String id;
  private String studentId;  // 外部キー
  private String courseName;
  private LocalDateTime startDate;
  private LocalDateTime expectedEndDate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public LocalDateTime getExpectedEndDate() {
    return expectedEndDate;
  }

  public void setExpectedEndDate(LocalDateTime expectedEndDate) {
    this.expectedEndDate = expectedEndDate;
  }

}
