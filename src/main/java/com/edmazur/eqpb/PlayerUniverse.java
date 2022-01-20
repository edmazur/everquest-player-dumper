package com.edmazur.eqpb;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class PlayerUniverse {

  private final Map<String, EqPlayer> namesToEqPlayers;

  public PlayerUniverse() {
    this.namesToEqPlayers = new TreeMap<>();
  }

  public void addOrUpdatePlayer(EqPlayer newEqPlayer) {
    EqPlayer existingEqPlayer =
        namesToEqPlayers.get(newEqPlayer.getName());
    if (existingEqPlayer == null) {
      namesToEqPlayers.put(newEqPlayer.getName(), newEqPlayer);
    } else {
      namesToEqPlayers.put(
          newEqPlayer.getName(),
          EqPlayer.merge(existingEqPlayer, newEqPlayer));
    }
  }

  public Collection<EqPlayer> getPlayers() {
    return namesToEqPlayers.values();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (EqPlayer eqPlayer : namesToEqPlayers.values()) {
      sb.append(eqPlayer);
      sb.append("\n");
    }
    return sb.toString();
  }

}