package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.yandex.practicum.filmorate.model.Film;

@RestController()
@RequestMapping("/films")
public class FilmController {
    private final HashMap<Integer, Film> films = new HashMap<>();

    @GetMapping
    public List<Film> findAll() {
        return new ArrayList<>(films.values());
    }

    @PostMapping
    public Film create(@RequestBody Film film) {
        film.setId(Film.getNewId());

        films.put(film.getId(), film);

        return film;
    }

    @PutMapping
    public Film update(@RequestBody Film film) {
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
        }

        return film;
    }
}