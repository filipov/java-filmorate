package ru.yandex.practicum.filmorate.controller;

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

    @GetMapping
    public List<User> findAll() {
        return users.getList();
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        return users.add(user);
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        User updatedUser = users.update(user);

        if (updatedUser == null) {
            throw new ResourceNotFoundException();
        }

        return users.update(user);
    }
}