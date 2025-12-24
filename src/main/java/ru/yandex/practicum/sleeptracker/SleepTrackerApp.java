package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.function.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    private static final  DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    private static final List<Function<List<SleepingSession>, String>> FUNCTIONS = List.of(
            new SessionsAmount(),
            new MinDurationSession(),
            new MaxDurationSession(),
            new AverageDurationSession(),
            new BadSessionsAmount(),
            new SleeplessNightsAmount(),
            new UserClassification()
    );
    private static List<SleepingSession> sleepingSessions;
    private static Path path;
    private static SleepAnalysisResult result;


    public static void main(String[] args) {
        System.out.println("СТАТИСТИКА СЕССИЙ СНА");
        if (args.length != 1) {
            System.err.println(
                    "Приложение должно принимать на вход как аргумент " +
                            "командной строки путь к файлу с логом сна.\n" +
                            "Команда для запуска: java -cp out/production/java-sleep-tracker " +
                            "ru.yandex.practicum.sleeptracker.SleepTrackerApp " +
                            "src/main/resources/sleep_log.txt"
            );
            return;
        }

        path = new File(args[0]).toPath();
        if (!Files.exists(path)) {
            System.err.println("Файл не найден: " + path);
            return;
        }

        processSleepingSessions();
    }

    private static void processSleepingSessions() {
        result = new SleepAnalysisResult();
        loadSleepingSessions();
        processFunctions();
    }

    private static void loadSleepingSessions() {
        sleepingSessions = new ArrayList<>();
        try (var reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            reader.lines().forEach(line -> sleepingSessions.add(createSleepingSession(line)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static SleepingSession createSleepingSession(String line) {
        String[] elements = line.split(";");
        LocalDateTime sleepStartTime = LocalDateTime.parse(elements[0], DATE_TIME_FORMATTER);
        LocalDateTime sleepEndTime = LocalDateTime.parse(elements[1], DATE_TIME_FORMATTER);
        SleepQuality quality = SleepQuality.valueOf(elements[2]);
        return new SleepingSession(sleepStartTime, sleepEndTime, quality);
    }

    private static void processFunctions() {
        if (FUNCTIONS.isEmpty()) {
            System.err.println("Ошибка: список функций не должен быть пустым");
            return;
        }

        if (sleepingSessions == null) {
            System.err.println("Ошибка: список сонных сессий не проинициализирован");
            return;
        }

        FUNCTIONS.forEach(function ->
                System.out.println(result.getMessage(function.apply(sleepingSessions)))
        );
    }
}