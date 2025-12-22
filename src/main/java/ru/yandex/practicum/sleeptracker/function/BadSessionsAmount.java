package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;


public class BadSessionsAmount implements Function<List<SleepingSession>, Long> {
    @Override
    public Long apply(List<SleepingSession> sessions) {
        return sessions.stream()
                .filter(s -> s.sleepQuality().equals(SleepQuality.BAD))
                .count();
    }
}
