package StSub8.Students.Courses.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import StSub8.Students.Courses.data.EnrollmentStatus;
import StSub8.Students.Courses.data.Status;
import StSub8.Students.Courses.service.EnrollmentStatusService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(EnrollmentStatusController.class)
class EnrollmentStatusControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private EnrollmentStatusService enrollmentStatusService;

  @Test
  void getAllEnrollmentStatuses_一覧取得できること() throws Exception {
    when(enrollmentStatusService.getAllEnrollmentStatuses()).thenReturn(List.of());

    mockMvc.perform(MockMvcRequestBuilders.get("/enrollment-statuses"))
        .andExpect(status().isOk());

    verify(enrollmentStatusService, times(1)).getAllEnrollmentStatuses();
  }

  @Test
  void getEnrollmentStatus_コースIDで取得できること() throws Exception {
    EnrollmentStatus status = new EnrollmentStatus();
    status.setStudentCourseId("course123");
    status.setStatus(Status.仮申込);

    when(enrollmentStatusService.getEnrollmentStatusByStudentCourseId("course123"))
        .thenReturn(status);

    mockMvc.perform(MockMvcRequestBuilders.get("/enrollment-status")
            .param("studentCourseId", "course123"))
        .andExpect(status().isOk());

    verify(enrollmentStatusService, times(1))
        .getEnrollmentStatusByStudentCourseId("course123");
  }

  @Test
  void registerEnrollmentStatus_登録できること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/enrollment-status")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                  "studentCourseId": "course123",
                  "status": "仮申込"
                }
                """))
        .andExpect(status().isOk());

    verify(enrollmentStatusService, times(1)).registerEnrollmentStatus(
        org.mockito.ArgumentMatchers.any());
  }

  @Test
  void updateEnrollmentStatus_更新できること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.patch("/enrollment-status")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                  "id": "status123",
                  "studentCourseId": "course123",
                  "status": "本申込"
                }
                """))
        .andExpect(status().isOk());

    verify(enrollmentStatusService, times(1)).updateEnrollmentStatus(
        org.mockito.ArgumentMatchers.any());
  }
}
