package pl.marcindebski.scoreboard;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
public class Match {
    MatchId id;

    String homeTeam;
    String awayTeam;

    int homeScore;
    int awayScore;

    Match(String homeTeam, String awayTeam) {
        this(MatchId.random(), homeTeam, awayTeam, 0, 0);
    }

    private Match(MatchId id, String homeTeam, String awayTeam, int homeScore, int awayScore) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public MatchId getId() {
        return id;
    }

    public Match withScore(int homeScore, int awayScore) {
        return new Match(id, homeTeam, awayTeam, homeScore, awayScore);
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
