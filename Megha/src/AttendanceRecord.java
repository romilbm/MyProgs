import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class AttendanceRecord {
  public static long LOGIN_WINDOW_MINUTES = 60;
  public static long TOLERANCE_WINDOW = 30;
  public static long AD_HOC_SHIFT_MIN_DURATION_MIN = 480;
  public AttendanceRecord(LocalDateTime startDateTime, LocalDateTime endDateTime, Integer empNo) {
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.empNo = empNo;
    analyzeRecord();
  }

  private void analyzeRecord() {
    LocalTime startTime = startDateTime.toLocalTime();
    if (startTime.isAfter(Shift.NIGHT.loginWinStart)) {
      shift = Shift.NIGHT;
    } else if (startTime.isAfter(Shift.AFTERNOON.loginWinStart)) {
      shift = Shift.AFTERNOON;
    } else if (startTime.isAfter(Shift.MORNING.loginWinStart)) {
      shift = Shift.MORNING;
    } else {
      shift = null;
      //return;
    }
    if (shift != null) {
      this.startedLate = startTime.isAfter(shift.loginWinClosed);
      this.shortOnTime = Math.abs(Duration.between(startDateTime, endDateTime).toMinutes()
          - TimeUnit.HOURS.toMinutes(shift.duration)) > TOLERANCE_WINDOW;
    } else {
      this.startedLate = false;
      this.shortOnTime = Duration.between(endDateTime, startDateTime).toMinutesPart() >=
          AD_HOC_SHIFT_MIN_DURATION_MIN - TOLERANCE_WINDOW;
    }
  }

  public LocalDateTime getStartDateTime() {
    return startDateTime;
  }

  public LocalDateTime getEndDateTime() {
    return endDateTime;
  }

  public Integer getEmpNo() {
    return empNo;
  }

  private LocalDateTime startDateTime;
  private LocalDateTime endDateTime;
  private Integer empNo;
  private Shift shift;

  private boolean shortOnTime;
  private boolean startedLate;

  public boolean isShortOnTime() {
    return shortOnTime;
  }

  public boolean isStartedLate() {
    return startedLate;
  }
}
