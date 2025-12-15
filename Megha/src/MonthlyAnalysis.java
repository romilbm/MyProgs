import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthlyAnalysis {
  private static final String DELIMITER = "\t";
  public static Map<Integer, String> EMP_MAP = new HashMap<>() {
    {
      put(2,"VK");
      put(3,"MW");
      put(4,"MN");
      put(5,"KM");
      put(6,"MW");
      put(9,"SM");
      put(10,"VB");
      put(11,"PC");
      put(12,"AS");
      put(15,"KA");
      put(16,"SP");
      put(17,"SR");
      put(21,"AP");
      put(22,"AB");
    }
  };

  private Integer numOfDays;
  private List<LocalDateTime> lateStarts;
  private List<LocalDateTime[]> shortOnTime;
  private Integer empNo;

  public MonthlyAnalysis(Integer numOfDays, List<LocalDateTime> lateStarts,
      List<LocalDateTime[]> shortOnTime, Integer empNo) {
    this.numOfDays = numOfDays;
    this.lateStarts = lateStarts;
    this.shortOnTime = shortOnTime;
    this.empNo = empNo;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(EMP_MAP.get(empNo)).append(DELIMITER);
    sb.append(numOfDays).append(DELIMITER);
    if (lateStarts.isEmpty()) {
      sb.append(0).append(DELIMITER);
    } else {
      sb.append(lateStarts.size()).append("(");
      String prefix = "";
      for (LocalDateTime ls : lateStarts) {
        sb.append(prefix).append(ls);
        prefix = ", ";
      }
      sb.append(")").append(DELIMITER);
    }

    if (shortOnTime.isEmpty()) {
      sb.append(0).append(DELIMITER);
    } else {
      sb.append(shortOnTime.size()).append("(");
      String prefix = "";
      for (LocalDateTime[] ls : shortOnTime) {
        Duration diff = Duration.between(ls[1], ls[0]);
        int hrs = diff.toHoursPart();
        int min = diff.toMinutesPart();

        sb.append(prefix);
        if (hrs == 0) sb.append(String.format("%s - %s = %s min", ls[0], ls[1], min));
        else sb.append(String.format("%s - %s = %s hr %s min", ls[0], ls[1], hrs, min));
        prefix = ", ";
      }
      sb.append(")").append(DELIMITER);
    }

    return sb.toString();
  }
}
