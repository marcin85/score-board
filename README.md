# Football World Cup Score Board Library

## Description

The Football World Cup Score Board Library is a simple Java implementation
that allows you to keep track of football games, their scores,
and provide a summary of games based on the total score.
This library is designed to be simple, efficient, and thread-safe,
using an in-memory store solution to keep the required information.

## Assumptions

* When Start Game operation will try to create actually running game then new game with new id will be returned.


* Instant time provider must be defined in order to operate instantaneous point on the time-line precisely.


## Supported operations

* Start a Game: You can start a game by providing the home team and away team names. The initial score is set to 0-0
  for the newly started game.


* Finish a Game: Once a game is finished, it will be removed from the scoreboard, and its associated scores will be cleared.


* Update Score: You can update the score of a game by providing the home team score and away team score.


* Get a Summary by Total Score: The library provides a summary of games ordered by the total score. In case multiple
  games have the same total score, they will be returned in the order they were added to the system.
