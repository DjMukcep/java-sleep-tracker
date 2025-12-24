package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class SleepAnalysisResult {

    private int counter;
    private static final List<String> MESSAGES = List.of(
            "Общее количество сессий: ",
            "Минимальная продолжительность сессии в минутах: ",
            "Максимальная продолжительность сессии в минутах: ",
            "Средняя продолжительность сессии в минутах: ",
            "Количество сессий с низким качеством сна: ",
            "Количество бессонных ночей: ",
            "Пользователь классифицируется как: "
    );

    public String getMessage(String value) {
        return MESSAGES.get(counter++) + value;
    }
}
