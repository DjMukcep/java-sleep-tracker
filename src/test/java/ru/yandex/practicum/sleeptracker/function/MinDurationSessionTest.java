package ru.yandex.practicum.sleeptracker.function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MinDurationSessionTest {

    private MinDurationSession minDurationSession;

    @BeforeEach
    void setUp() {
        minDurationSession = new MinDurationSession();
    }

    @Test
    void shouldReturnZeroWhenEmptyListPassed() {
        long result = minDurationSession.apply(List.of());
        assertEquals(0, result);
    }

    @Test
    void shouldReturnCorrectMaxSessionWhenValidSessionListPassed() {
        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 12, 21, 8, 0),
                        LocalDateTime.of(2025, 12, 21, 8, 2), SleepQuality.GOOD),
                new SleepingSession(
                        LocalDateTime.of(2025, 12, 21, 8, 2),
                        LocalDateTime.of(2025, 12, 21, 8, 5), SleepQuality.BAD),
                new SleepingSession(
                        LocalDateTime.of(2025, 12, 21, 8, 5),
                        LocalDateTime.of(2025, 12, 21, 8, 6), SleepQuality.BAD)
        );
        long result = minDurationSession.apply(sleepingSessions);

        assertEquals(1, result);
    }
}
