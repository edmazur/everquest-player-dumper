package com.edmazur.eqpb;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum EqClass {

  UNKNOWN(
      "Unknown",
      "Unknown",
      "Unknown",
      "Unknown"),

  BARD(
      "Bard",
      "Minstrel",
      "Troubadour",
      "Virtuoso"),

  CLERIC(
      "Cleric",
      "Vicar",
      "Templar",
      "High Priest"),

  DRUID(
      "Druid",
      "Wanderer",
      "Preserver",
      "Hierophant"),

  ENCHANTER(
      "Enchanter",
      "Illusionist",
      "Beguiler",
      "Phantasmist"),

  MAGICIAN(
      "Magician",
      "Elementalist",
      "Conjurer",
      "Arch Mage"),

  MONK(
      "Monk",
      "Disciple",
      "Master",
      "Grandmaster"),

  NECROMANCER(
      "Necromancer",
      "Heretic",
      "Defiler",
      "Warlock"),

  PALADIN(
      "Paladin",
      "Cavalier",
      "Knight",
      "Crusader"),

  RANGER(
      "Ranger",
      "Pathfinder",
      "Outrider",
      "Warder"),

  ROGUE(
      "Rogue",
      "Rake",
      "Blackguard",
      "Assassin"),

  SHADOW_KNIGHT(
      "Shadow Knight",
      "Reaver",
      "Revenant",
      "Grave Lord"),

  SHAMAN(
      "Shaman",
      "Mystic",
      "Luminary",
      "Oracle"),

  WARRIOR(
      "Warrior",
      "Champion",
      "Myrmidon",
      "Warlord"),

  WIZARD(
      "Wizard",
      "Channeler",
      "Evoker",
      "Sorcerer"),

  ;

  private static final Map<String, EqClass> NAME_TO_EQ_CLASS =
      Collections.unmodifiableMap(initializeNameToEqClassMap());

  private final String name;
  private final String name51To54;
  private final String name55To59;
  private final String name60;

  private EqClass(
      String name, String name51To54, String name55To59, String name60) {
    this.name = name;
    this.name51To54 = name51To54;
    this.name55To59 = name55To59;
    this.name60 = name60;
  }

  public String getName() {
    return name;
  }

  public static EqClass fromAnyName(String anyName) {
    EqClass eqClass = NAME_TO_EQ_CLASS.get(anyName);
    if (eqClass == null) {
      // TODO: Figure out how to complain here.
    }
    return eqClass;
  }

  private static Map<String, EqClass> initializeNameToEqClassMap() {
    Map<String, EqClass> map = new HashMap<>();
    for (EqClass eqClass : EqClass.values()) {
      map.put(eqClass.name, eqClass);
      map.put(eqClass.name51To54, eqClass);
      map.put(eqClass.name55To59, eqClass);
      map.put(eqClass.name60, eqClass);
    }
    return map;
  }

  @Override
  public String toString() {
    return name;
  }

}
