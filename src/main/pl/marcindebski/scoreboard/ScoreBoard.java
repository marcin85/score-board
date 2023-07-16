package pl.marcindebski.scoreboard;

import java.util.*;

import static java.util.Objects.requireNonNull;

public class ScoreBoard {
    private final List<Match> matches;

    public ScoreBoard() {
        this.matches = new ArrayList<>();
    }

    public Match createMatch(String homeTeam, String awayTeam) {
        validate(homeTeam, awayTeam);

        Match match = new Match(homeTeam, awayTeam);
        matches.add(match);
        return match;
    }

    private static void validate(String homeTeam, String awayTeam) {
        requireNonNull(homeTeam, "home team cannot be null");
        requireNonNull(awayTeam, "away team cannot be null");

        if (Objects.equals(homeTeam, awayTeam)) {
            throw new IllegalArgumentException("team names cannot be equal");
        }
    }

    public Iterable<Match> getSummary() {
        return matches;
    }

}