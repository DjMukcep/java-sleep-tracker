package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class SessionsAmount implements Function<List<SleepingSession>, Integer> {
    @Override
    public Integer apply(List<SleepingSession> sessions) {
        return sessions.size();
    }
}
