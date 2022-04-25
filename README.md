# everquest-player-dumper
EverQuest player information dumper

# Usage

Setup:

```
$ git clone https://github.com/edmazur/everquest-player-dumper
$ cd everquest-player-dumper
```

Run:

```
$ git pull
$ ./gradlew installDist
$ ./build/install/everquest-player-dumper/bin/everquest-robot-stanvern \
      $EQ_INSTALL_DIRECTORY \
      $TIMEZONE \
      $SERVER \
      $CHARACTER
```

Parameter notes:

* $EQ_INSTALL_DIRECTORY - The directory where EverQuest is installed, e.g. `/opt/everquest/EverQuest\ Project\ 1999`.
* $TIMEZONE - The timezone of the log file, e.g. `America/New_York`.
* $SERVER - The server of the character, e.g. `green`.
* $CHARACTER - The name of the character, e.g. `Stanvern`.