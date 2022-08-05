package ru.yandex.practicum.filmorate.model;

import java.time.Duration;
import java.time.LocalDate;

import lombok.*;

@Data
public class Film {
    private static int lastId = 0;

    private int id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Duration duration;

    public static int getNewId() {
        lastId++;

        return lastId;
    }
}