package day1;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public enum ValidDigit {
  ONE("one", 1),
  TWO("two", 2),
  THREE("three", 3),
  FOUR("four", 4),
  FIVE("five", 5),
  SIX("six", 6),
  SEVEN("seven", 7),
  EIGHT("eight", 8),
  NINE("nine", 9);

  final String name;
  final Integer value;

  public static String getValidDigit(String data) {
    // log.info("{}", data);
    return Arrays.stream(values())
        .filter(d -> data.equals(d.name))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new)
        .value
        .toString();
  }
}
