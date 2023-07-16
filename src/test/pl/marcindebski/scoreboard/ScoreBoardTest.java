package pl.marcindebski.scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class ScoreBoardTest {

    public static final String MEXICO = "Mexico";
    public static final String CANADA = "Canada";

    private ScoreBoard scoreBoard;

    @BeforeEach
    public void setUp() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    public void testCreateMatch() {
        Match match = scoreBoard.createMatch(MEXICO, CANADA);
        assertThat(match.getHomeScore()).isZero();
        assertThat(match.getAwayScore()).isZero();
    }

    @Test
    public void testNullTeamShouldThrowException() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                scoreBoard.createMatch(null, CANADA)).withMessage("home team cannot be null");

        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                scoreBoard.createMatch(CANADA, null)).withMessage("away team cannot be null");
    }

    @Test
    public void testSameTeamNamesShouldThrowException() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                scoreBoard.createMatch(CANADA, CANADA)).withMessage("team names cannot be equal");
    }

    @Test
    public void testGetSummary() {
        scoreBoard.createMatch(MEXICO, CANADA);
        Iterable<Match> matches = scoreBoard.getSummary();
        assertThat(matches).hasSize(1).hasOnlyOneElementSatisfying(match -> {
            assertThat(match.getHomeTeam()).isEqualTo(MEXICO);
            assertThat(match.getAwayTeam()).isEqualTo(CANADA);
        });
    }
}
