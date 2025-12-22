package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserClassification implements Function<List<SleepingSession>, String> {

    @Override
    public String apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return "список сонных сессий пуст";
        }

        return sessions.stream()
                .collect(Collectors.groupingBy(s -> {
                    LocalTime start = s.startTime().toLocalTime();
                    LocalTime end = s.endTime().toLocalTime();
                    int startDay = s.startTime().toLocalDate().getDayOfMonth();
                    int endDay = s.endTime().toLocalDate().getDayOfMonth();
                    boolean isNotSameDay = startDay != endDay;
                    if ((start.isAfter(LocalTime.of(23, 0))
                            || start.isBefore(LocalTime.of(6, 0)))
                            && end.isAfter(LocalTime.of(9, 0))) {
                        return "сова";
                    } else if (start.isBefore(LocalTime.of(22, 0))
                            && end.isBefore(LocalTime.of(7, 0))
                            && isNotSameDay) {
                        return "жаворонок";
                    } else if (isNotSameDay) {
                        return "голубь";
                    }
                    return "undefined";
                }, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> !e.getKey().equals("undefined"))
                .max(Map.Entry.comparingByValue())
                .orElse(Map.entry("голубь", 0L))
                .getKey();
    }
}
