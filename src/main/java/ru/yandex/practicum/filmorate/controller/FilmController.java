package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(FilmController.class);

    @GetMapping
    public List<Film> findAll() {
        log.info("Получен запрос GET /films");

        return films.getList();
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        log.info("Получен запрос POST /films");

        return films.add(film);
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        log.info("Получен запрос PUT /films");

        Film updatedFilm = films.update(film);

        if (updatedFilm == null) {
            throw new ResourceNotFoundException();
        }

        return films.update(film);
    }
}