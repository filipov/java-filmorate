package ru.yandex.practicum.filmorate.model;

import java.time.Duration;
import java.time.LocalDate;

import lombok.*;
import ru.yandex.practicum.filmorate.utils.Model;
import ru.yandex.practicum.filmorate.validation.FutureOrDate;

import javax.validation.constraints.*;

@Data
public class Film extends Model {
    @NotEmpty
    @NotBlank
    private String name;
    @Size(max=200)
    private String description;
    @FutureOrDate("1895-12-27")
    private LocalDate releaseDate;
    @Positive
    private int duration;
}