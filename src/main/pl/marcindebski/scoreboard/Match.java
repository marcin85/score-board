package pl.marcindebski.scoreboard;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
public class Match {
    String homeTeam;
    String awayTeam;

    MatchId id;

    public Match(MatchId id, String homeTeam, String awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return 0;
    }

    public int getAwayScore() {
        return 0;
    }

    public MatchId getId() {
        return id;
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
