package com.edmazur.eqpb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import com.edmazur.eqlp.EqLogEvent;

/**
 * Utility for smoking out obvious parser problems. Takes in as input a file
 * where all lines are expected to be able to parsed.
 */
public class BasicWhoParseCheck {

  public static void main(String[] args) throws IOException {
    List<String> lines = Files.readAllLines(Paths.get(args[0]));
    for (String line : lines) {
      Optional<EqLogEvent> maybeEqLogEvent = EqLogEvent.parseFromLine(line);
      if (maybeEqLogEvent.isEmpty()) {
        continue;
      }
      if (EqPlayerParser.parse(maybeEqLogEvent.get()).isEmpty()) {
        System.out.println("Expected to be able to parse line: " + line);
      }
    }
  }

}
