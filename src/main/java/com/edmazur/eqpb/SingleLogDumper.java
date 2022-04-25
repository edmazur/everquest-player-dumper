package com.edmazur.eqpb;

import com.edmazur.eqlp.EqLog;

public class SingleLogDumper {

  private final EqLog eqLog;
  private final PlayerUniverse playerUniverse;

  public SingleLogDumper(EqLog eqLog, PlayerUniverse playerUniverse) {
    this.eqLog = eqLog;
    this.playerUniverse = playerUniverse;
  }

  public void parse() {
    eqLog.addListener(new EqPlayerInfoListener(playerUniverse));
    eqLog.run();
  }

}
