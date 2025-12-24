package ru.yandex.practicum.sleeptracker;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class SleepTrackerAppTest {

    @Test
    void shouldParseCorrectLine() {
        String line = "01.12.24 23:00;02.12.24 07:00;GOOD";

        SleepingSession session = SleepTrackerApp.createSleepingSession(line);

        assertEquals(
                LocalDateTime.of(2024, 12, 1, 23, 0),
                session.startTime()
        );
        assertEquals(
                LocalDateTime.of(2024, 12, 2, 7, 0),
                session.endTime()
        );
        assertEquals(SleepQuality.GOOD, session.sleepQuality());
    }

    @Test
    void shouldThrowExceptionWhenLineHasWrongFormat() {
        String invalidLine = "01.12.24 23:00;GOOD";

        assertThrows(
                RuntimeException.class,
                () -> SleepTrackerApp.createSleepingSession(invalidLine)
        );
    }


}