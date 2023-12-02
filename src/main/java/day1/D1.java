package day1;

import static common.Util.getResourceAsList;
import static day1.ValidDigit.getValidDigit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

/** Advent of Code 2023 Day 1 */
@Slf4j
public class D1 {
  public static void main(String... args) throws IOException {
    List<String> inputs = getResourceAsList("1.txt");
    var finalResultA =
        inputs.stream()
            .map(D1::getFirstAndLastFromDigit)
            .map(Integer::valueOf)
            .reduce(0, Integer::sum);

    var finalResultB =
        inputs.stream()
            .map(D1::getFirstAndLastFromRegex)
            .map(Integer::valueOf)
            .reduce(0, Integer::sum);

    log.info("Day 1, part A: {}", finalResultA);
    log.info("Day 1, part B: {}", finalResultB);
  }

  private static String getFirstAndLastFromDigit(String data) {
    String merged = getFirstDigit(data) + getLastDigit(data);
    log.info("merged: {}, original: {}", merged, data);
    return merged;
  }

  private static String getFirstDigit(String data) {
    return getDigitFromString(data);
  }

  private static String getLastDigit(String data) {
    String reversed = new StringBuilder(data).reverse().toString();
    return getDigitFromString(reversed);
  }

  private static String getDigitFromString(String data) {
    List<String> found = new ArrayList<>();
    for (int i = 0; i < data.length(); i++) {
      char c = data.charAt(i);
      if (Character.isDigit(c) && found.isEmpty()) found.add(String.valueOf(c));
    }
    return found.getFirst();
  }

  private static String getFirstAndLastFromRegex(String data) {
    Pattern pattern = Pattern.compile("(?=([1-9]|nine|eight|seven|six|five|four|three|two|one)).");
    Matcher m = pattern.matcher(data);
    var merged = getFirstMatchFromRegex(m) + getLastMatchFromRegex(m);
    if (merged.length() == 1) merged = merged + merged;
    log.info("merged: {}, original: {}", merged, data);
    return merged;
  }

  private static String getFirstMatchFromRegex(Matcher matcher) {
    if (matcher.find()) {
      var digit = matcher.group(1);
      try {
        Integer.parseInt(digit);
      } catch (NumberFormatException e) {
        return getValidDigit(digit);
      }
      return digit;
    }
    return "";
  }

  private static String getLastMatchFromRegex(Matcher matcher) {
    String lastMatch = null;
    while (matcher.find()) {
      lastMatch = matcher.group(matcher.groupCount());
    }
    if (lastMatch == null) return "";
    try {
      Integer.parseInt(lastMatch);
    } catch (NumberFormatException e) {
      return getValidDigit(lastMatch);
    }
    return lastMatch;
  }
}
