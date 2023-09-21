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
$ ./build/install/everquest-player-dumper/bin/everquest-player-dumper \
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

Gotcha:

The last line in the log file must be in the future or the program will hang. To
deal with this in standalone files, just copy/paste the last line and manually
update it to a future timestamp.

Merging:

```
$ (run) >> ../everquest-effort-analyzer/src/main/resources/players.txt
(repeat for each log)
$ cat ../everquest-effort-analyzer/src/main/resources/players.txt | sort -u -t, -k1,1 > foo
$ mv foo ../everquest-effort-analyzer/src/main/resources/players.txt
```
