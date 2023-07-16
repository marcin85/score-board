package pl.marcindebski.scoreboard;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.Instant;
import java.util.Comparator;
import java.util.UUID;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Match implements Comparable<Match> {
    static Comparator<Match> MATCH_COMPARATOR = Comparator.comparingInt(Match::getTotalScore).thenComparing(Match::getStartTime).reversed();

    MatchId id;
    Instant startTime;

    String homeTeam;
    String awayTeam;

    int homeScore;
    int awayScore;

    Match(Instant startTime, String homeTeam, String awayTeam) {
        this(MatchId.random(), startTime, homeTeam, awayTeam, 0, 0);
    }

    @Override
    public int compareTo(Match match) {
        return MATCH_COMPARATOR.compare(this, match);
    }

    private int getTotalScore() {
        return homeScore + awayScore;
    }

    public Match withScore(int homeScore, int awayScore) {
        return new Match(id, startTime, homeTeam, awayTeam, homeScore, awayScore);
    }

    @Value
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MatchId {
        UUID uuid;

        public static MatchId random() {
            return new MatchId(UUID.randomUUID());
        }
    }
}
