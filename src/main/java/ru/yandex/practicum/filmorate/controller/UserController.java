package ru.yandex.practicum.filmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.utils.Store;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {
    private final Store<User> users = new Store<>();

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    @GetMapping
    public List<User> findAll() {
        log.info("Получен запрос GET /users");

        return users.getList();
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        log.info("Получен запрос POST /users");

        return users.add(user);
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        log.info("Получен запрос PUT /users");

        User updatedUser = users.update(user);

        if (updatedUser == null) {
            throw new ResourceNotFoundException();
        }

        return users.update(user);
    }
}