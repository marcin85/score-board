package pl.marcindebski.scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreBoardTest {
    private ScoreBoard scoreBoard;

    @BeforeEach
    public void setUp() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    public void testCreateMatch() {
        String homeTeam = "Mexico";
        String awayTeam = "Canada";
        Match match = scoreBoard.createMatch(homeTeam, awayTeam);
        assertThat(match.getHomeScore()).isZero();
        assertThat(match.getAwayScore()).isZero();

    }
}
