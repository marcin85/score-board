package pl.marcindebski.scoreboard;

import java.time.Instant;

@FunctionalInterface
public interface TimeProvider {
    Instant getTime();
}
