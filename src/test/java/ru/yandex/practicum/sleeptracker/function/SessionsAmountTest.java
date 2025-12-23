package ru.yandex.practicum.sleeptracker.function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SessionsAmountTest {

    private SessionsAmount sessionsAmount;

    @BeforeEach
    void setUp() {
        sessionsAmount = new SessionsAmount();
    }

    @Test
    void shouldReturnZeroWhenEmptyListOrNullPassed() {
        String result = sessionsAmount.apply(List.of());
        assertEquals("0", result);
    }

    @Test
    void shouldReturnCorrectSizeWhenValidSessionListPassed() {
        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now(), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now(), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now(), SleepQuality.GOOD));

        String result = sessionsAmount.apply(sleepingSessions);
        assertEquals("3", result);
    }
}
