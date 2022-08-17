package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;

@RestController()
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    private static final Logger log = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<Film> findAll() {
        log.info("Получен запрос GET /films");

        return filmService.findAll();
    }

    @GetMapping("/{id}")
    public Film find(@PathVariable int id) {
        log.info("Получен запрос GET /films/{}", id);

        return filmService.find(id);
    }

    @PutMapping("/{id}/like/{userId}")
    public Film addLike(@PathVariable int id, @PathVariable int userId) {
        log.info("Получен запрос GET /films/{}/like/{}", id, userId);

        return filmService.addLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public Film removeLike(@PathVariable int id, @PathVariable int userId) {
        log.info("Получен запрос GET /films/{}/like/{}", id, userId);

        return filmService.removeLike(id, userId);
    }

    @GetMapping("/popular")
    public List<Film> getMostPopular(@RequestParam Optional<Integer> count) {
        log.info("Получен запрос GET /films/popular");

        int defaultCount = 10;

        if (count.isPresent()) {
            defaultCount = count.get();
        }

        return filmService.mostPopular(defaultCount);
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        log.info("Получен запрос POST /films");

        return filmService.create(film);
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        log.info("Получен запрос PUT /films");

        return filmService.update(film);
    }

    @ExceptionHandler
    public Map<String, String> handleNegativeCount(final RuntimeException e) {
        return Map.of("error", e.getMessage());
    }
}
