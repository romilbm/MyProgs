import java.time.LocalTime;

public enum Shift {
  MORNING(9, 0, 8),
  AFTERNOON(13, 0, 8),
  NIGHT(21, 0, 12);

  public final LocalTime startTime;
  public final LocalTime loginWinStart;
  public final LocalTime loginWinClosed;
  public final Integer duration;

  private Shift(int hour, int min, int duration) {
    this.startTime = LocalTime.of(hour, min);
    this.loginWinStart = startTime.minusMinutes(AttendanceRecord.LOGIN_WINDOW_MINUTES/2 + 1);
    this.loginWinClosed = startTime.plusMinutes(AttendanceRecord.LOGIN_WINDOW_MINUTES/2 + 1);
    this.duration = duration;
  }
}
