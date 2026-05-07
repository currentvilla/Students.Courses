package StSub8.Students.Courses.controller;

import StSub8.Students.Courses.data.EnrollmentStatus;
import StSub8.Students.Courses.service.EnrollmentStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "申込状況管理", description = "コースの申込状況に関する操作")
@RestController
public class EnrollmentStatusController {

  private final EnrollmentStatusService enrollmentStatusService;

  @Autowired
  public EnrollmentStatusController(EnrollmentStatusService enrollmentStatusService) {
    this.enrollmentStatusService = enrollmentStatusService;
  }

  /**
   * 申込状況の全件検索
   */
  @Operation(summary = "申込状況一覧取得", description = "全コースの申込状況一覧を返します。")
  @GetMapping("/enrollment-statuses")
  public List<EnrollmentStatus> getAllEnrollmentStatuses() {
    return enrollmentStatusService.getAllEnrollmentStatuses();
  }

  /**
   * 受講生コースIDで申込状況を検索
   */
  @Operation(summary = "申込状況取得", description = "指定した受講生コースIDの申込状況を返します。")
  @GetMapping("/enrollment-status")
  public EnrollmentStatus getEnrollmentStatus(@RequestParam String studentCourseId) {
    return enrollmentStatusService.getEnrollmentStatusByStudentCourseId(studentCourseId);
  }

  /**
   * 申込状況の登録
   */
  @Operation(summary = "申込状況登録", description = "新しい申込状況を登録します。")
  @PostMapping("/enrollment-status")
  public ResponseEntity<?> registerEnrollmentStatus(
      @Valid @RequestBody EnrollmentStatus enrollmentStatus) {
    enrollmentStatusService.registerEnrollmentStatus(enrollmentStatus);
    return ResponseEntity.ok().build();
  }

  /**
   * 申込状況の更新
   */
  @Operation(summary = "申込状況更新", description = "指定した申込状況を更新します。")
  @PatchMapping("/enrollment-status")
  public ResponseEntity<?> updateEnrollmentStatus(
      @Valid @RequestBody EnrollmentStatus enrollmentStatus) {
    enrollmentStatusService.updateEnrollmentStatus(enrollmentStatus);
    return ResponseEntity.ok().build();
  }
}
