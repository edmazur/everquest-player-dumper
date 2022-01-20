package com.edmazur.eqpb;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.edmazur.eqlp.EqLogEvent;

// TODO: Extend this to look at other data-carrying messages like /guildst.
public class EqPlayerParser {

  private static final Pattern WHO_PATTERN = Pattern.compile(
      // AFK or not.
      "(?: AFK )?"
      // Linkdead or not.
      + "(?: <LINKDEAD>)?"
      // Either level and class, or anon.
      + "\\[(?:(?<level>\\d{1,2}) (?<class>\\p{Upper}.+)|(?<anon>ANONYMOUS))\\]"
      // Name.
      + " (?<name>\\p{Upper}\\p{Lower}+)"
      // Race or not.
      + "(?: \\(.+\\))?"
      // Guild or not.
      + "(?: {1,2}<(?<guild>.+)>)?"
      // Zone or not.
      + "(?: {1,2}ZONE:(?: \\p{Alnum}+)?)?"
      // LFG or not.
      + "(?: {1,3}LFG)?"
      // Trailing whitespace.
      + "(?:\\s+)?");

  public static Optional<EqPlayer> parse(EqLogEvent eqLogEvent) {
    Matcher matcher = WHO_PATTERN.matcher(eqLogEvent.getPayload());
    if (!matcher.matches()) {
      return Optional.empty();
    }

    String name = matcher.group("name");
    Optional<EqClass> maybeEqClass = matcher.group("class") == null ?
        Optional.empty() :
        Optional.of(EqClass.fromAnyName(matcher.group("class")));
    Optional<Integer> maybeLevel = matcher.group("level") == null ?
        Optional.empty() :
        Optional.of(Integer.parseInt(matcher.group("level")));
    Optional<String> maybeGuild = matcher.group("anon") == null ?
        // Not anon, guild information will be visible if guilded.
        (matcher.group("guild") == null ?
            Optional.of("") :
            Optional.of(matcher.group("guild"))) :
        // Anon, can't make determination about guild unless it's present.
        (matcher.group("guild") == null ?
            Optional.empty() :
            Optional.of(matcher.group("guild")));
    return Optional.of(new EqPlayer(
        name, maybeEqClass, maybeLevel, maybeGuild, eqLogEvent.getTimestamp()));
  }

}