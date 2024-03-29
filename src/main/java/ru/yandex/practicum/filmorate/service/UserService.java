package ru.yandex.practicum.filmorate.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final InMemoryUserStorage userStorage;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = (InMemoryUserStorage) userStorage;
    }


    public List<User> findAll() {
        log.info("Запрошен список пользователей");

        return userStorage.getList();
    }

    public User find(int id) {
        log.info("Запрошен пользователь");

        User user = userStorage.get(id);

        if (user == null) {
            throw new ResourceNotFoundException();
        }

        return user;
    }


    public User create(User user) {
        log.info("Создан пользователь");

        return userStorage.add(user);
    }


    public User update(User user) {
        log.info("Обновлен пользователь");

        return userStorage.update(user);
    }

    public List<User> getFriends(int userId) {
        log.info("Получены друзья");

        User user = find(userId);

        return user
                .getFriends()
                .stream()
                .map(this::find)
                .collect(Collectors.toList());
    }

    public void addFriend(int userId, int friendId) {
        log.info("Добавлен друг");

        User user = find(userId);
        User friend = find(friendId);

        user.addFriend(friendId);
        friend.addFriend(userId);
    }

    public void removeFriend(int userId, int friendId) {
        log.info("Удален друг");

        User user = find(userId);
        User friend = find(friendId);

        user.removeFriend(friendId);
        friend.removeFriend(userId);
    }

    public List<User> getCommonFriends(int userId, int friendId) {
        log.info("Получены пересечения друзей");

        User friend = find(friendId);

        return getFriends(userId)
                .stream()
                .filter(u -> friend.getFriends().contains(u.getId()))
                .collect(Collectors.toList());
    }
}
