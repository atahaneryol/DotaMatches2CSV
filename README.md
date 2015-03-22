# DotaMatches2CSV
This project creates a csv file that has all the relevent data for each match the user has played.
It creates a Nx113 matrix where each column refers to each hero and the first column refers to the hero the player has played in that match. Basically N is the number of all the matches user has played. Each column indicates if the hero associated with that column is present in that match. If it is present and on the users side, it has the value "a" as in ally. If the hero is on the opposing team it has the value "e" as in enemy. If the hero is not selected in that match it has the value "n". The hero selected by the user is indicated in the first column.

Currently the API key needed to make the calls, and the user Steam ID that the matches requested for are hardcoded. Hoping to change that soon.

It is exported from an Eclipse project. Therefore it can be imported directly into Eclipse using:
File -> Import -> Select Root Directory, and select the downloaded directory.

The project uses Jackson Json Parser, and it needs to be added as a dependency. It uses the following libraries:
jackson-annotations-2.5.0.jar
jackson-core-2.5.0.jar
jackson-databind-2.5.0.jar

TODO: Will provide the links to the libraries, and at some point create a Maven project for building and dependencies.



