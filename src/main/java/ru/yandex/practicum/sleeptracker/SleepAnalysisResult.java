package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class SleepAnalysisResult {

    private static int counter;
    private static final List<String> MESSAGES = List.of(
            "Общее количество сессий: ",
            "Минимальная продолжительность сессии в минутах: ",
            "Максимальная продолжительнось сессии в минутах: ",
            "Средняя продолжительность сессии в минутах: ",
            "Количество сессий с низким качеством сна: ",
            "Количество бессоных ночей: ",
            "Пользователь классифицируется как: "
    );

    public <T> String getMessage(T value) {
        return MESSAGES.get(counter++) + value;
    }
}
