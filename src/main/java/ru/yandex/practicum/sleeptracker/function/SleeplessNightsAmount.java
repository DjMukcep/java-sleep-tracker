package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.function.Function;

public class SleeplessNightsAmount implements Function<List<SleepingSession>, Long> {
    @Override
    public Long apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return 0L;
        }

        int nightsAmount = Period.between(sessions.getFirst().startTime().toLocalDate(),
                sessions.getLast().endTime().toLocalDate()).getDays();

        long sleepingNights = sessions.stream()
                .filter(s -> {
                    LocalTime start = s.startTime().toLocalTime();
                    int startDay = s.startTime().toLocalDate().getDayOfMonth();
                    int endDay = s.endTime().toLocalDate().getDayOfMonth();
                    boolean isNotSameDay = startDay != endDay;
                    return isNotSameDay || start.isBefore(LocalTime.of(6, 0));
                })
                .count();

        return nightsAmount - sleepingNights;
    }
}
