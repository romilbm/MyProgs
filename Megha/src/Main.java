import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    LogParser logParser = new LogParser();
    logParser.parseFile("/Users/romil/Downloads/AGL_0001_o.TXT", "Aug", "2023");
  }
}
