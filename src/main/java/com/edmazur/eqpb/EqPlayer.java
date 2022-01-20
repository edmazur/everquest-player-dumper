package com.edmazur.eqpb;

import java.time.LocalDateTime;
import java.util.Optional;

public class EqPlayer {

  private final String name;
  private EqPlayerMetadata<EqClass> eqClass;
  private EqPlayerMetadata<Integer> level;
  private EqPlayerMetadata<String> guild;

  public EqPlayer(
      String name,
      EqPlayerMetadata<EqClass> eqClass,
      EqPlayerMetadata<Integer> level,
      EqPlayerMetadata<String> guild) {
    this.name = name;
    this.eqClass = eqClass;
    this.level = level;
    this.guild = guild;
  }

  public EqPlayer(
      String name,
      Optional<EqClass> maybeEqClass,
      Optional<Integer> maybeLevel,
      Optional<String> maybeGuild,
      LocalDateTime observationTime) {
    this(
        name,
        new EqPlayerMetadata<EqClass>(maybeEqClass, observationTime),
        new EqPlayerMetadata<Integer>(maybeLevel, observationTime),
        new EqPlayerMetadata<String>(maybeGuild, observationTime));
  }

  // TODO: Handle edge cases involving multiple log files. Example:
  // - File 1 has older log entries showing a non-/anon player.
  // - File 2 has newer log entries showing the same player, but now /anon.
  // - If file 2 is parsed before file 1, the non-/anon data will be ignored.
  public static EqPlayer merge(EqPlayer eqPlayer1, EqPlayer eqPlayer2) {
    // Identify name.
    if (!eqPlayer1.getName().equals(eqPlayer2.getName())) {
      throw new IllegalArgumentException(String.format(
          "Can't merge EqPlayer objects with different names (%s and %s)",
          eqPlayer1, eqPlayer2));
    }
    String name = eqPlayer1.getName();

    // TODO: There's probably a better way to organize this. The logic in here
    // is basically copy/pasted for each piece of metadata.

    // Pick class.
    EqPlayerMetadata<EqClass> eqClass = null;
    if (!eqPlayer1.isEqClassKnown() && !eqPlayer2.isEqClassKnown()) {
      eqClass = eqPlayer1.eqClass;
    } else if (eqPlayer1.isEqClassKnown() && !eqPlayer2.isEqClassKnown()) {
      eqClass = eqPlayer1.eqClass;
    } else if (!eqPlayer1.isEqClassKnown() && eqPlayer2.isEqClassKnown()) {
      eqClass = eqPlayer2.eqClass;
    } else if (eqPlayer1.getEqClassObservationTime().isAfter(
        eqPlayer2.getEqClassObservationTime())) {
      eqClass = eqPlayer1.eqClass;
    } else {
      eqClass = eqPlayer2.eqClass;
    }

    // Pick level.
    EqPlayerMetadata<Integer> level = null;
    if (!eqPlayer1.isLevelKnown() && !eqPlayer2.isLevelKnown()) {
      level = eqPlayer1.level;
    } else if (eqPlayer1.isLevelKnown() && !eqPlayer2.isLevelKnown()) {
      level = eqPlayer1.level;
    } else if (!eqPlayer1.isLevelKnown() && eqPlayer2.isLevelKnown()) {
      level = eqPlayer2.level;
    } else if (eqPlayer1.getLevelObservationTime().isAfter(
        eqPlayer2.getLevelObservationTime())) {
      level = eqPlayer1.level;
    } else {
      level = eqPlayer2.level;
    }

    // Pick guild.
    EqPlayerMetadata<String> guild = null;
    if (!eqPlayer1.isGuildStatusKnown() && !eqPlayer2.isGuildStatusKnown()) {
      guild = eqPlayer1.guild;
    } else if (eqPlayer1.isGuildStatusKnown() && !eqPlayer2.isGuildStatusKnown()) {
      guild = eqPlayer1.guild;
    } else if (!eqPlayer1.isGuildStatusKnown() && eqPlayer2.isGuildStatusKnown()) {
      guild = eqPlayer2.guild;
    } else if (eqPlayer1.getGuildStatusObservationTime().isAfter(
        eqPlayer2.getGuildStatusObservationTime())) {
      guild = eqPlayer1.guild;
    } else {
      guild = eqPlayer2.guild;
    }

    return new EqPlayer(name, eqClass, level, guild);
  }

  public String getName() {
    return name;
  }

  public boolean isEqClassKnown() {
    return eqClass.getData().isPresent();
  }

  public LocalDateTime getEqClassObservationTime() {
    return eqClass.getObservationTime();
  }

  public EqClass getEqClass() {
    return eqClass.getData().get();
  }

  public boolean isLevelKnown() {
    return level.getData().isPresent();
  }

  public LocalDateTime getLevelObservationTime() {
    return level.getObservationTime();
  }

  public int getLevel() {
    return level.getData().get();
  }

  public boolean isGuildStatusKnown() {
    return guild.getData().isPresent();
  }

  public LocalDateTime getGuildStatusObservationTime() {
    return guild.getObservationTime();
  }

  public boolean isGuilded() {
    return !guild.getData().get().equals("");
  }

  public String getGuild() {
    return guild.getData().get();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (level.getData().isPresent()) {
      sb.append(String.format("[%d %s]",
          level.getData().get(),
          eqClass.getData().get().getName()));
    } else {
      sb.append("[ANONYMOUS]");
    }
    sb.append(" " + name);
    if (guild.getData().isPresent() && !guild.getData().get().equals("")) {
      sb.append(" <" + guild.getData().get() + ">");
    }
    return sb.toString();
  }

}