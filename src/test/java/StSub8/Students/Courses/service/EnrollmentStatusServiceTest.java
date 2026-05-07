package StSub8.Students.Courses.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import StSub8.Students.Courses.Mapper.EnrollmentStatusMapper;
import StSub8.Students.Courses.data.EnrollmentStatus;
import StSub8.Students.Courses.data.Status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EnrollmentStatusServiceTest {

  @Mock
  private EnrollmentStatusMapper enrollmentStatusMapper;

  private EnrollmentStatusService sut;

  @BeforeEach
  void before() {
    sut = new EnrollmentStatusService(enrollmentStatusMapper);
  }

  @Test
  void getAllEnrollmentStatuses_全件取得できること() {
    List<EnrollmentStatus> statusList = new ArrayList<>();
    when(enrollmentStatusMapper.findAll()).thenReturn(statusList);

    sut.getAllEnrollmentStatuses();

    verify(enrollmentStatusMapper, times(1)).findAll();
  }

  @Test
  void getEnrollmentStatusByStudentCourseId_コースIDで取得できること() {
    EnrollmentStatus status = new EnrollmentStatus();
    status.setStudentCourseId("course123");
    status.setStatus(Status.仮申込);

    when(enrollmentStatusMapper.findByStudentCourseId("course123")).thenReturn(status);

    EnrollmentStatus actual = sut.getEnrollmentStatusByStudentCourseId("course123");

    verify(enrollmentStatusMapper, times(1)).findByStudentCourseId("course123");
    Assertions.assertEquals(Status.仮申込, actual.getStatus());
  }

  @Test
  void registerEnrollmentStatus_登録できること() {
    EnrollmentStatus status = new EnrollmentStatus();

    sut.registerEnrollmentStatus(status);

    verify(enrollmentStatusMapper, times(1)).insert(status);
  }

  @Test
  void updateEnrollmentStatus_更新できること() {
    EnrollmentStatus status = new EnrollmentStatus();

    sut.updateEnrollmentStatus(status);

    verify(enrollmentStatusMapper, times(1)).update(status);
  }
}
