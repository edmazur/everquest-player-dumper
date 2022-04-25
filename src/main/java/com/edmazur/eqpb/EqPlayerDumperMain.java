package com.edmazur.eqpb;

import com.edmazur.eqlp.EqLog;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;

public class EqPlayerDumperMain {

  public static void main(String[] args) {
    // TODO: Validate input.
    final Path eqInstallDirectory = Paths.get(args[0]);
    final ZoneId timezone = ZoneId.of(args[1]);
    final String server = args[2];
    final String character = args[3];

    PlayerUniverse playerUniverse = new PlayerUniverse();
    // TODO: Iterate over all characters that have log files.
    EqLog eqLog = new EqLog(
        eqInstallDirectory,
        timezone,
        server,
        character,
        Instant.MIN,
        Instant.now());
    new SingleLogDumper(eqLog, playerUniverse).parse();

    for (EqPlayer eqPlayer : playerUniverse.getPlayers()) {
      String eqClass = eqPlayer.isEqClassKnown()
          ? eqPlayer.getEqClass().getName()
          : "UNKNOWN";
      System.out.println(eqPlayer.getName() + "," + eqClass);
    }
  }

}
