package ru.yandex.practicum.filmorate.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {
    private InMemoryFilmStorage filmStorage;

    private static final Logger log = LoggerFactory.getLogger(FilmService.class);

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = (InMemoryFilmStorage) filmStorage;
    }


    public List<Film> findAll() {
        log.info("Запрошен список фильмов");

        return filmStorage.getList();
    }

    public Film find(int id) {
        log.info("Запрошен фильм");

        Film film = filmStorage.get(id);

        if (film == null) {
            throw new ResourceNotFoundException();
        }

        return film;
    }

    public Film create(Film film) {
        log.info("Создан фильм");

        return filmStorage.add(film);
    }


    public Film update(Film film) {
        log.info("Обновлен фильм");

        Film updatedFilm = filmStorage.update(film);

        if (updatedFilm == null) {
            throw new ResourceNotFoundException();
        }

        return updatedFilm;
    }

    public Film addLike(int filmId, int userId) {
        log.info("Добавлен лайк фильму");

        Film film = find(filmId);

        film.addLike(userId);

        return film;
    }

    public Film removeLike(int filmId, int userId) {
        log.info("Удален лайк у фильма");

        Film film = find(filmId);

        if (!film.getLikes().contains(userId)) {
            throw new ResourceNotFoundException();
        }

        film.removeLike(userId);

        return film;
    }

    public List<Film> mostPopular(int count) {
        return findAll()
                .stream()
                .sorted(Comparator.comparingInt(f -> -f.getLikes().size()))
                .limit(count)
                .collect(Collectors.toList());
    }
}
