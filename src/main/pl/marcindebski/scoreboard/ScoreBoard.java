package pl.marcindebski.scoreboard;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class ScoreBoard {
    public Match createMatch(String homeTeam, String awayTeam) {
        validate(homeTeam, awayTeam);

        return new Match(homeTeam, awayTeam);
    }

    private static void validate(String homeTeam, String awayTeam) {
        requireNonNull(homeTeam, "home team cannot be null");
        requireNonNull(awayTeam, "away team cannot be null");

        if (Objects.equals(homeTeam, awayTeam)) {
            throw new IllegalArgumentException("team names cannot be equal");
        }
    }

}