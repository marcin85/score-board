package pl.marcindebski.scoreboard;

import java.time.Instant;

public interface TimeProvider {
    Instant getTime();
}
