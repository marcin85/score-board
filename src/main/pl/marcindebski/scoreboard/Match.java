package pl.marcindebski.scoreboard;

import java.util.UUID;

public class Match {
    private final String homeTeam;
    private final String awayTeam;

    private final MatchId id;

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

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public MatchId getId() {
        return id;
    }

    public static class MatchId {
        UUID uuid;

        public MatchId(UUID uuid) {
            this.uuid = uuid;
        }

        public static MatchId random() {
            return new MatchId(UUID.randomUUID());
        }
    }
}
