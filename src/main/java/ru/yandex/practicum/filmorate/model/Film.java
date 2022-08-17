package ru.yandex.practicum.filmorate.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.*;
import ru.yandex.practicum.filmorate.utils.Model;
import ru.yandex.practicum.filmorate.validation.FutureOrDate;

import javax.validation.constraints.*;

@Data
public class Film extends Model {

    @NotBlank
    private String name;
    @Size(max=200)
    private String description;
    @FutureOrDate("1895-12-27")
    private LocalDate releaseDate;
    @Positive
    private int duration;

    private final Set<Integer> likes = new HashSet<>();

    public Film(String name, String description, LocalDate releaseDate, int duration) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    public void addLike(int id) {
        likes.add(id);
    }

    public void removeLike(int id) {
        likes.remove(id);
    }
}