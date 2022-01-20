package com.edmazur.eqpb;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.edmazur.eqlp.EqLogEvent;

// TODO: Add more unit tests.
class EqPlayerParserTest {

  @Test
  void parseWhoAfkNoLdNoAnonNoRaceNoGuildNoZoneNo() {
    EqPlayer eqPlayer = EqPlayerParser.parse(EqLogEvent.parseFromLine(
        "[Fri Oct 25 19:02:50 2019] [2 Ranger] Eleazar (Wood Elf)"
        ).get()).get();
    assertEquals("Eleazar", eqPlayer.getName());
    assertTrue(eqPlayer.isEqClassKnown());
    assertEquals(EqClass.RANGER, eqPlayer.getEqClass());
    assertTrue(eqPlayer.isLevelKnown());
    assertEquals(2, eqPlayer.getLevel());
    assertTrue(eqPlayer.isGuildStatusKnown());
    assertFalse(eqPlayer.isGuilded());
  }

  @Test
  void parseWhoAnonNoGuildYesZoneYes() {
    EqPlayer eqPlayer = EqPlayerParser.parse(EqLogEvent.parseFromLine(
        "[Tue Jan 18 22:01:20 2022] [60 Warder] Stanvern (Wood Elf) <Force of Will> ZONE: templeveeshan"
        ).get()).get();
    assertEquals("Stanvern", eqPlayer.getName());
    assertTrue(eqPlayer.isEqClassKnown());
    assertEquals(EqClass.RANGER, eqPlayer.getEqClass());
    assertTrue(eqPlayer.isLevelKnown());
    assertEquals(60, eqPlayer.getLevel());
    assertTrue(eqPlayer.isGuildStatusKnown());
    assertTrue(eqPlayer.isGuilded());
    assertEquals("Force of Will", eqPlayer.getGuild());
  }

}