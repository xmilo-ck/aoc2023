package common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Util {
  public static List<String> getResourceAsList(String fileName) throws IOException {
    return Files.readAllLines(new File("src/main/resources/inputs/" + fileName).toPath());
  }
}
