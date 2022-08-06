package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.utils.Store;

import javax.validation.Valid;

@RestController()
@RequestMapping("/films")
public class FilmController {
    private final Store<Film> films = new Store<>();

    @GetMapping
    public List<Film> findAll() {
        return films.getList();
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        return films.add(film);
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        Film updatedFilm = films.update(film);

        if (updatedFilm == null) {
            throw new ResourceNotFoundException();
        }

        return films.update(film);
    }
}