import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LogParser {
  private static final String DELIMITER = "\t";
  private static final DateTimeFormatter DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
  private static final String HEADER_ROW = "Employee\tNum of Days\tLate Starts\tShort Time";

  private static final int DATE_INDEX = 9;
  private static final int LINE_NO_INDEX = 0;
  private static final int EMP_NO_INDEX = 2;
  private static final int END_START_INDEX = 6;

  private List<List<String>> getFileContents(String filePath, String month, String year)
      throws IOException {
    List<List<String>> records = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (!isData(line)) continue;

        String[] values = line.split(DELIMITER);
        if (!isWithinDateRange(values[DATE_INDEX], month, year)) continue;

        records.add(Arrays.asList(values));
      }
    }
    return records;
  }

  public void parseFile(String filePath, String month, String year) throws IOException {
    List<List<String>> records = getFileContents(filePath, month, year);
    Map<Integer, List<AttendanceRecord>> attRecs = getEmployeeRecord(records);
    List<MonthlyAnalysis> monthlyAnalysisList = attRecs.entrySet()
        .stream()
        .map((entry) -> getMonthlyAnalysis(entry.getKey(), entry.getValue()))
        .toList();

    System.out.println(HEADER_ROW);
    monthlyAnalysisList.forEach(System.out::println);
  }

  private MonthlyAnalysis getMonthlyAnalysis(Integer empNo, List<AttendanceRecord> attRec) {
    List<LocalDateTime> lateStarts = new ArrayList<>();
    List<LocalDateTime[]> shortOnTime = new ArrayList<>();

    attRec.forEach(arec -> {
      if (arec.isStartedLate()) lateStarts.add(arec.getStartDateTime());

      if (arec.isShortOnTime()) shortOnTime.add(new LocalDateTime[] {arec.getStartDateTime(),
          arec.getEndDateTime()});
    });
    return new MonthlyAnalysis(attRec.size(), lateStarts, shortOnTime, empNo);
  }

  private Map<Integer, List<AttendanceRecord>> getEmployeeRecord(List<List<String>> records) {
    Map<Integer, List<AttendanceRecord>> empRecords = new HashMap<>();
    Map<Integer, LocalDateTime> currentShift = new HashMap<>();
    records.forEach(record -> {
      Integer empNo = Integer.parseInt(record.get(EMP_NO_INDEX));
      String endStart = record.get(END_START_INDEX);
      LocalDateTime recordedTimeStamp =
          LocalDateTime.parse(record.get(DATE_INDEX), DATE_TIME_FORMATTER);

      if (!currentShift.containsKey(empNo)) {
        currentShift.put(empNo, null);
        empRecords.put(empNo, new ArrayList<>());
      }

      if (endStart.equals("S")) {
        //check if it has a start time already
        LocalDateTime currentStartDateTimeValue = currentShift.get(empNo);
        if (currentStartDateTimeValue != null &&
            Duration.between(recordedTimeStamp, currentStartDateTimeValue).toHours() > 4) {
          // Didn't log out. Log this here.
          System.out.printf("%s Didn't log out on %s%n", record.get(EMP_NO_INDEX),
              record.get(DATE_INDEX));
        } else {
          currentShift.put(empNo, recordedTimeStamp);
        }

      } else if (endStart.equals("E")) {
        LocalDateTime currentStartDateTimeValue = currentShift.get(empNo);
        if (currentStartDateTimeValue == null && recordedTimeStamp.getDayOfMonth() != 1) {
          // This should not happen. Log this.
          System.out.printf("%s's log in not found. Direct log out found at %s%n",
              record.get(EMP_NO_INDEX), record.get(DATE_INDEX));
        } else if (currentStartDateTimeValue == null) {
          System.out.printf("Ignoring direct end for %s on first day %s. %n",
              record.get(EMP_NO_INDEX), record.get(DATE_INDEX));
        } else {
          empRecords.get(empNo)
              .add(new AttendanceRecord(currentStartDateTimeValue, recordedTimeStamp, empNo));
          currentShift.put(empNo, null);
        }

      } else {
        System.out.printf("Unknown START / END entry for %s on %s", empNo, recordedTimeStamp);
      }
    });

    return empRecords;
  }

  private boolean isData(String line) {
    try {
      Integer.parseInt(line.substring(0, 1));
    } catch (NumberFormatException e) {
      return false;
    }

    return true;
  }

  private boolean isWithinDateRange(String dateFromFile, String month, String year) {
    // ex: month = Sep, year = 2023
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
    // firstOfMonth = "01 Sep 2023"
    LocalDate firstOfMonth = LocalDate.parse("01" + " " + month + " " + year, formatter);
    // startDate = "31 Aug 2023"
    LocalDate startDate = firstOfMonth.minusDays(1);
    // endDate = "02 Oct 2023"
    LocalDate endDate = firstOfMonth.plusMonths(1).plusDays(1);

    LocalDateTime dateTimeFromFile = LocalDateTime.parse(dateFromFile, DATE_TIME_FORMATTER);
    LocalDate onlyDateFromFile = dateTimeFromFile.toLocalDate();

    // onlyDateFromFile should be after "31 Aug 2023" and before "02 Oct 2023"
    return onlyDateFromFile.isAfter(startDate) && onlyDateFromFile.isBefore(endDate);
  }
}
