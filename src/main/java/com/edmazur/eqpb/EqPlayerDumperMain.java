package com.edmazur.eqpb;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;

import com.edmazur.eqlp.EqLog;

public class EqPlayerDumperMain {

  public static void main(String[] args) {
    // TODO: Validate input.
    final Path eqInstallDirectory = Paths.get(args[0]);
    final ZoneId timezone = ZoneId.of(args[1]);
    final String server = args[2];

    PlayerUniverse playerUniverse = new PlayerUniverse();
    // TODO: Iterate over all characters that have log files.
    EqLog eqLog = new EqLog(
        eqInstallDirectory,
        timezone,
        server,
        "Stanvern",
        Instant.MIN,
        Instant.now());
    new SingleLogDumper(eqLog, playerUniverse).parse();

    for (EqPlayer eqPlayer : playerUniverse.getPlayers()) {
      String eqClass = eqPlayer.isEqClassKnown() ?
          eqPlayer.getEqClass().getName() :
          "UNKNOWN";
      System.out.println(eqPlayer.getName() + "," + eqClass);
    }
  }

}