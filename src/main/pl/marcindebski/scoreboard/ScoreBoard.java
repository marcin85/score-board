package pl.marcindebski.scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class ScoreBoard {
    private List<Match> matches;
    private final TimeProvider timeProvider;

    public ScoreBoard(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
        this.matches = new ArrayList<>();
    }

    public Match createMatch(String homeTeam, String awayTeam) {
        validate(homeTeam, awayTeam);

        Match match = new Match(timeProvider, homeTeam, awayTeam);
        matches = Stream.concat(Stream.of(match), matches.stream()).sorted().toList();
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

    public void finishGame(Match.MatchId id) {
        matches = matches.stream().filter(match -> !Objects.equals(id, match.getId())).sorted().toList();
    }

    public void updateScore(Match.MatchId id, int homeScore, int awayScore) {
        matches = matches.stream().map(match -> {
            if (match.getId().equals(id)) {
                return match.withScore(homeScore, awayScore);
            } else {
                return match;
            }
        }).sorted().toList();
    }

}