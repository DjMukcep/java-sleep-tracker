package ru.yandex.practicum.sleeptracker.function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleeplessNightsAmountTest {

    private SleeplessNightsAmount sleeplessNightsAmount;

    @BeforeEach
    void setUp() {
        sleeplessNightsAmount = new SleeplessNightsAmount();
    }

    @Test
    void shouldReturnZeroWhenEmptyListPassed() {
        long result = sleeplessNightsAmount.apply(List.of());
        assertEquals(0, result);
    }

    @Test
    void shouldNotReturnAnySleeplessNights() {
        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 12, 21, 23, 0),
                        LocalDateTime.of(2025, 12, 22, 7, 0), SleepQuality.GOOD),
                new SleepingSession(
                        LocalDateTime.of(2025, 12, 22, 19, 0),
                        LocalDateTime.of(2025, 12, 23, 3, 0), SleepQuality.BAD),
                new SleepingSession(
                        LocalDateTime.of(2025, 12, 24, 2, 0),
                        LocalDateTime.of(2025, 12, 24, 7, 0), SleepQuality.BAD),
                new SleepingSession(
                        LocalDateTime.of(2025, 12, 25, 2, 0),
                        LocalDateTime.of(2025, 12, 25, 5, 0), SleepQuality.BAD)
        );
        long result = sleeplessNightsAmount.apply(sleepingSessions);

        assertEquals(0, result);
    }

    @Test
    void shouldReturnOneSleeplessNight() {
        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 12, 21, 17, 0),
                        LocalDateTime.of(2025, 12, 21, 23, 59), SleepQuality.GOOD),
                new SleepingSession(
                        LocalDateTime.of(2025, 12, 22, 6, 0),
                        LocalDateTime.of(2025, 12, 22, 11, 0), SleepQuality.BAD)
        );
        long result = sleeplessNightsAmount.apply(sleepingSessions);

        assertEquals(1, result);
    }

    @Test
    void shouldReturnTwoSleeplessNight() {
        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(
                        LocalDateTime.of(2025, 12, 21, 17, 0),
                        LocalDateTime.of(2025, 12, 21, 23, 59), SleepQuality.GOOD),
                new SleepingSession(
                        LocalDateTime.of(2025, 12, 23, 6, 0),
                        LocalDateTime.of(2025, 12, 23, 11, 0), SleepQuality.BAD)
        );
        long result = sleeplessNightsAmount.apply(sleepingSessions);

        assertEquals(2, result);
    }
}
