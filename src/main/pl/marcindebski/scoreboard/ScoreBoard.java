package pl.marcindebski.scoreboard;

import static java.util.Objects.requireNonNull;

public class ScoreBoard {
    public Match createMatch(String homeTeam, String awayTeam) {
        requireNonNull(homeTeam, "home team cannot be null");
        requireNonNull(awayTeam, "away team cannot be null");

        return new Match(homeTeam, awayTeam);
    }
}