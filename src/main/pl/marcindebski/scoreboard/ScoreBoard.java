package pl.marcindebski.scoreboard;

public class ScoreBoard {
    public Match createMatch(String homeTeam, String awayTeam) {
        return new Match(homeTeam, awayTeam);
    }
}