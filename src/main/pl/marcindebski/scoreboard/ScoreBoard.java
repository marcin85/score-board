package pl.marcindebski.scoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class ScoreBoard {
    private List<Match> matches;
    private final TimeSupplier timeSupplier;

    public ScoreBoard(TimeSupplier timeSupplier) {
        this.timeSupplier = timeSupplier;
        this.matches = new ArrayList<>();
    }

    public Match startGame(String homeTeam, String awayTeam) {
        validate(homeTeam, awayTeam);

        Match match = new Match(timeSupplier.get(), homeTeam, awayTeam);
        matches = Stream.concat(Stream.of(match), matches.stream()).sorted().toList();
        return match;
    }

    private void validate(String homeTeam, String awayTeam) {
        requireNonNull(homeTeam, "home team cannot be null");
        requireNonNull(awayTeam, "away team cannot be null");

        if (Objects.equals(homeTeam, awayTeam)) {
            throw new IllegalArgumentException("team names cannot be equal");
        }
    }

    public List<Match> getSummary() {
        return Collections.unmodifiableList(matches);
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