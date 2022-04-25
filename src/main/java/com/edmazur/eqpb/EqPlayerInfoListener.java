package com.edmazur.eqpb;

import com.edmazur.eqlp.EqLogEvent;
import com.edmazur.eqlp.EqLogListener;
import java.util.Optional;

public class EqPlayerInfoListener implements EqLogListener {

  private final PlayerUniverse playerUniverse;

  public EqPlayerInfoListener(PlayerUniverse playerUniverse) {
    this.playerUniverse = playerUniverse;
  }

  @Override
  public void onEvent(EqLogEvent eqLogEvent) {
    Optional<EqPlayer> maybeEqPlayer = EqPlayerParser.parse(eqLogEvent);
    if (maybeEqPlayer.isPresent()) {
      playerUniverse.addOrUpdatePlayer(maybeEqPlayer.get());
    }
  }

}
