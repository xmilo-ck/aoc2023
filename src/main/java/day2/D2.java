package day2;

import static common.Util.getResourceAsList;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class D2 {
  /** Advent of Code 2023 Day 2 */
  public static void main(String... args) throws IOException {
    List<String> inputs = getResourceAsList("2.txt");
    var finalResultA = inputs.stream().map(D2::processGamesA).reduce(0, Integer::sum);
    log.info("Day 2, part A: {}", finalResultA);

    var finalResultB = inputs.stream().map(D2::processGamesB).reduce(0, Integer::sum);
    log.info("Day 2, part B: {}", finalResultB);
  }

  private static Integer processGamesA(String data) {
    Map<String, Integer> maxCounts = new HashMap<>();
    maxCounts.put("red", 12);
    maxCounts.put("green", 13);
    maxCounts.put("blue", 14);

    var line = data.split("(Game )|:|]");
    var gameId = line[1];
    var content = line[2];
    var groups = content.split(";");
    for (String group : groups) {
      String[] elements = group.trim().split(", ");
      for (String element : elements) {
        String[] parts = element.split(" ");
        int count = Integer.parseInt(parts[0]);
        String color = parts[1];

        if (count > maxCounts.getOrDefault(color, Integer.MAX_VALUE)) {
          log.info("impossible {}", data);
          return Integer.parseInt("0");
        }
      }
    }

    log.info("possible {}", data);
    return Integer.parseInt(gameId);
  }

  private static Integer processGamesB(String data) {
    var line = data.split("(Game )|:|]");
    var content = line[2];
    var groups = content.split(";");
    Map<String, Integer> countMap = new HashMap<>();

    for (String group : groups) {
      String[] elements = group.trim().split(", ");
      for (String element : elements) {
        String[] parts = element.split(" ");
        int count = Integer.parseInt(parts[0]);
        String color = parts[1];
        countMap.putIfAbsent(color, count);
        if (countMap.get(color) < count) countMap.replace(color, count);
      }
    }

    AtomicInteger result = new AtomicInteger(1);
    countMap.forEach((key, value) -> result.updateAndGet(v -> v * value));
    log.info("{}", result);
    return result.get();
  }
}
