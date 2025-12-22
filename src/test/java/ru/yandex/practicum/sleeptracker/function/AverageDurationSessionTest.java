package ru.yandex.practicum.sleeptracker.function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AverageDurationSessionTest {

    private AverageDurationSession averageDurationSession;

    @BeforeEach
    void setUp() {
        averageDurationSession = new AverageDurationSession();
    }

    @Test
    void shouldReturnZeroWhenEmptyListOrNullPassed() {
        int result = averageDurationSession.apply(List.of());
        assertEquals(0, result);
    }

    @Test
    void shouldReturnCorrectAverageDurationWhenValidSessionListPassed() {
        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 12, 21, 8, 0),
                        LocalDateTime.of(2025, 12, 21, 8, 2), SleepQuality.GOOD),
                new SleepingSession(
                        LocalDateTime.of(2025, 12, 21, 8, 2),
                        LocalDateTime.of(2025, 12, 21, 8, 5), SleepQuality.GOOD),
                new SleepingSession(
                        LocalDateTime.of(2025, 12, 21, 8, 5),
                        LocalDateTime.of(2025, 12, 21, 8, 6), SleepQuality.GOOD)
        );
        int result = averageDurationSession.apply(sleepingSessions);

        assertEquals(2, result);
    }
}
