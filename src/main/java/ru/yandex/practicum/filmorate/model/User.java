package ru.yandex.practicum.filmorate.model;

import java.time.LocalDate;

import lombok.*;
import ru.yandex.practicum.filmorate.utils.Model;

import javax.validation.constraints.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
public class User extends Model {
    private static final String PATTERN_REGEXP = "^[\\S]+$";

    @Email
    private final String email;
    @NotEmpty
    @Pattern(regexp = PATTERN_REGEXP)
    private final String login;
    private String name;
    @Past
    private LocalDate birthday;

    public User(int id, String email, String login, String name, LocalDate birthday) {
        setId(id);

        this.email = email;
        this.login = login;

        if (name == null || name.isEmpty()) {
            this.name = login;
        } else {
            this.name = name;
        }

        this.birthday = birthday;
    }
}