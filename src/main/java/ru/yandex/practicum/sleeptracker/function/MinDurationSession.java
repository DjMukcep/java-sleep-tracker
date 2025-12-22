package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MinDurationSession implements Function<List<SleepingSession>, Long> {
    @Override
    public Long apply(List<SleepingSession> sessions) {
        return sessions.stream()
                .map(s -> Duration.between(s.startTime(), s.endTime()))
                .map(Duration::toMinutes)
                .min(Long::compareTo)
                .orElse(0L);
    }
}
