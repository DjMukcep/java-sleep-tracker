package ru.yandex.practicum.sleeptracker.function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserClassificationTest {

    private UserClassification userClassification;

    @BeforeEach
    void setUp() {
        userClassification = new UserClassification();
    }

    @Test
    void shouldReturnMessageWhenEmptyListPassed() {
        String result = userClassification.apply(List.of());
        assertEquals("список сонных сессий пуст", result);
    }

    @Test
    void shouldReturnOwlWhenPassedSessionListWithOwlTimePriorityCount() {
        List<SleepingSession> sleepingSessions = List.of(
                        new SleepingSession(
                                LocalDateTime.of(2025, 12, 21, 23, 10),
                                LocalDateTime.of(2025, 12, 22, 9, 10),
                                SleepQuality.GOOD), // сова
                        new SleepingSession(
                                LocalDateTime.of(2025, 12, 25, 2, 0),
                                LocalDateTime.of(2025, 12, 25, 12, 0),
                                SleepQuality.GOOD), // сова
                        new SleepingSession(
                                LocalDateTime.of(2025, 12, 27, 21, 0),
                                LocalDateTime.of(2025, 12, 28, 5, 0),
                                SleepQuality.GOOD), // жаворонок
                        new SleepingSession(
                                LocalDateTime.of(2025, 12, 28, 22, 0),
                                LocalDateTime.of(2025, 12, 29, 9, 0),
                                SleepQuality.GOOD) // голубь
                );

        String result = userClassification.apply(sleepingSessions);

        assertEquals("сова", result);
    }

    @Test
    void shouldReturnLarkWhenPassedSessionListWithOwlTimePriorityCount() {
        List<SleepingSession> sleepingSessions = List.of(
                        new SleepingSession(
                                LocalDateTime.of(2025, 12, 21, 23, 10),
                                LocalDateTime.of(2025, 12, 22, 9, 10),
                                SleepQuality.GOOD), // сова
                        new SleepingSession(
                                LocalDateTime.of(2025, 12, 28, 22, 0),
                                LocalDateTime.of(2025, 12, 29, 8, 0),
                                SleepQuality.GOOD), // голубь
                        new SleepingSession(
                                LocalDateTime.of(2025, 12, 27, 21, 0),
                                LocalDateTime.of(2025, 12, 28, 5, 0),
                                SleepQuality.GOOD), // жаворонок
                        new SleepingSession(
                                LocalDateTime.of(2025, 12, 27, 21, 0),
                                LocalDateTime.of(2025, 12, 28, 5, 0),
                                SleepQuality.GOOD) // жаворонок
                );
        String result = userClassification.apply(sleepingSessions);

        assertEquals("жаворонок", result);
    }

    @Test
    void shouldReturnPigeonWhenPassedSessionListWithOwlTimePriorityCount() {
        List<SleepingSession> sleepingSessions = List.of(
                        new SleepingSession(
                                LocalDateTime.of(2025, 12, 21, 23, 10),
                                LocalDateTime.of(2025, 12, 22, 9, 10),
                                SleepQuality.GOOD), // сова
                        new SleepingSession(
                                LocalDateTime.of(2025, 12, 28, 22, 0),
                                LocalDateTime.of(2025, 12, 29, 8, 0),
                                SleepQuality.GOOD), // голубь
                        new SleepingSession(
                                LocalDateTime.of(2025, 12, 27, 23, 0),
                                LocalDateTime.of(2025, 12, 28, 5, 0),
                                SleepQuality.GOOD), // голубь
                        new SleepingSession(
                                LocalDateTime.of(2025, 12, 27, 21, 0),
                                LocalDateTime.of(2025, 12, 28, 5, 0),
                                SleepQuality.GOOD) // жаворонок
                );
        String result = userClassification.apply(sleepingSessions);

        assertEquals("голубь", result);
    }

    @Test
    void shouldReturnPigeonWhenPassedSessionListWhereEachBirdHasSameTimePriorityCount() {
        List<SleepingSession> sleepingSessions = List.of(
                        new SleepingSession(
                                LocalDateTime.of(2025, 12, 21, 23, 10),
                                LocalDateTime.of(2025, 12, 22, 9, 10),
                                SleepQuality.GOOD), // сова
                        new SleepingSession(
                                LocalDateTime.of(2025, 12, 27, 23, 0),
                                LocalDateTime.of(2025, 12, 28, 5, 0),
                                SleepQuality.GOOD), // голубь
                        new SleepingSession(
                                LocalDateTime.of(2025, 12, 27, 21, 0),
                                LocalDateTime.of(2025, 12, 28, 5, 0),
                                SleepQuality.GOOD) // жаворонок
                );
        String result = userClassification.apply(sleepingSessions);

        assertEquals("голубь", result);
    }
}
