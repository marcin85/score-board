package pl.marcindebski.scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class ScoreBoardTest {

    public static final String MEXICO = "Mexico";
    public static final String CANADA = "Canada";
    public static final String SPAIN = "Spain";
    public static final String BRAZIL = "Brazil";
    public static final String GERMANY = "Germany";
    public static final String FRANCE = "France";
    public static final String URUGUAY = "Uruguay";
    public static final String ITALY = "Italy";
    public static final String ARGENTINA = "Argentina";
    public static final String AUSTRALIA = "Australia";


    private TimeSupplier timeSupplier;

    private ScoreBoard scoreBoard;


    @BeforeEach
    void setUp() {
        timeSupplier = new TimeSupplierMock();
        scoreBoard = new ScoreBoard(timeSupplier);
    }


    @Test
    public void testCreateMatch() {
        Match match = scoreBoard.startGame(MEXICO, CANADA);
        assertThat(match.getHomeScore()).isZero();
        assertThat(match.getAwayScore()).isZero();
    }

    @Test
    public void testNullTeamShouldThrowException() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                scoreBoard.startGame(null, CANADA)).withMessage("home team cannot be null");

        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                scoreBoard.startGame(CANADA, null)).withMessage("away team cannot be null");
    }

    @Test
    public void testSameTeamNamesShouldThrowException() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                scoreBoard.startGame(CANADA, CANADA)).withMessage("team names cannot be equal");
    }

    @Test
    public void testFinishGame() {
        Match match = scoreBoard.startGame(MEXICO, CANADA);
        scoreBoard.finishGame(match.getId());
        Iterable<Match> summary = scoreBoard.getSummary();
        assertThat(summary).isEmpty();
    }

    @Test
    public void testFinishGameWithWrongIdShouldPass() {
        scoreBoard.startGame(MEXICO, CANADA);
        scoreBoard.finishGame(Match.MatchId.random());
        Iterable<Match> summary = scoreBoard.getSummary();
        assertThat(summary).hasSize(1);
    }

    @Test
    public void testUpdateScore() {
        Match match = scoreBoard.startGame(MEXICO, CANADA);

        Iterable<Match> summary = scoreBoard.getSummary();
        assertThat(summary)
                .usingElementComparatorIgnoringFields("id", "startTime")
                .containsOnly(match(MEXICO, CANADA, 0, 0));

        scoreBoard.updateScore(match.getId(), 4, 3);

        Iterable<Match> summaryAfterUpdate = scoreBoard.getSummary();
        assertThat(summaryAfterUpdate)
                .usingElementComparatorIgnoringFields("id", "startTime")
                .containsOnly(match(MEXICO, CANADA, 4, 3));
    }

    @Test
    public void testGetSummary() {
        Match mexCan = scoreBoard.startGame(MEXICO, CANADA);
        Match spaBra = scoreBoard.startGame(SPAIN, BRAZIL);
        Match gerFra = scoreBoard.startGame(GERMANY, FRANCE);
        Match uruIta = scoreBoard.startGame(URUGUAY, ITALY);
        Match argAus = scoreBoard.startGame(ARGENTINA, AUSTRALIA);

        scoreBoard.updateScore(mexCan.getId(), 0, 5);
        scoreBoard.updateScore(spaBra.getId(), 10, 2);
        scoreBoard.updateScore(gerFra.getId(), 2, 2);
        scoreBoard.updateScore(uruIta.getId(), 6, 6);
        scoreBoard.updateScore(argAus.getId(), 3, 1);

        assertThat(scoreBoard.getSummary())
                .usingElementComparatorIgnoringFields("id", "startTime")
                .containsExactly(
                        match(URUGUAY, ITALY, 6, 6),
                        match(SPAIN, BRAZIL, 10, 2),
                        match(MEXICO, CANADA, 0, 5),
                        match(ARGENTINA, AUSTRALIA, 3, 1),
                        match(GERMANY, FRANCE, 2, 2)
                );
    }

    private Match match(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        return new Match(timeSupplier.get(), homeTeam, awayTeam).withScore(homeScore, awayScore);
    }

    private static class TimeSupplierMock extends TimeSupplier {
        private final AtomicLong time = new AtomicLong();

        @Override
        public Instant get() {
            return Instant.now().plusNanos(time.incrementAndGet());
        }
    }

}
