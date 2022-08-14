package ru.yandex.practicum.filmorate.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void shouldHaveNoViolations() {
        User object = new User(
                1,
                "andrey@example.ru",
                "andrey",
                "Andrey",
                LocalDate.of(1992, 1, 30));

        Set<ConstraintViolation<User>> violations = validator.validate(object);

        boolean result = violations.isEmpty();
        assertTrue(result);
    }

    @Test
    public void shouldDetectInvalidEmailForEmptyData() {
        User object = new User(
                1,
                null,
                "andrey",
                "Andrey",
                LocalDate.of(1992, 1, 30));


        Set<ConstraintViolation<User>> violations = validator.validate(object);


        assertEquals(violations.size(), 1);

        ConstraintViolation<User> violation = violations.iterator().next();
        assertEquals("email", violation.getPropertyPath().toString());
    }

    @Test
    public void shouldDetectInvalidEmailForWrongData() {
        User object = new User(
                1,
                "andrey.example.ru@",
                "andrey",
                "Andrey",
                LocalDate.of(1992, 1, 30));

        Set<ConstraintViolation<User>> violations = validator.validate(object);

        assertEquals(violations.size(), 1);

        ConstraintViolation<User> violation = violations.iterator().next();
        assertEquals("email", violation.getPropertyPath().toString());
    }

    @Test
    public void shouldDetectInvalidLoginForEmptyData() {
        User object = new User(
                1,
                "andrey@example.ru",
                "",
                "Andrey",
                LocalDate.of(1992, 1, 30));

        Set<ConstraintViolation<User>> violations = validator.validate(object);

        assertEquals(violations.size(), 2);

        ConstraintViolation<User> violation = violations.iterator().next();
        assertEquals("login", violation.getPropertyPath().toString());
    }

    @Test
    public void shouldDetectInvalidLoginForWrongData() {
        User object = new User(
                1,
                "andrey@example.ru",
                "and rey",
                "Andrey",
                LocalDate.of(1992, 1, 30));

        Set<ConstraintViolation<User>> violations = validator.validate(object);

        assertEquals(violations.size(), 1);

        ConstraintViolation<User> violation = violations.iterator().next();
        assertEquals("login", violation.getPropertyPath().toString());
    }

    @Test
    public void shouldUseLoginForEmptyName() {
        User object = new User(
                1,
                "andrey@example.ru",
                "andrey",
                null,
                LocalDate.of(1992, 1, 30));

        String result = object.getName();

        assertEquals("andrey", result);
    }

    @Test
    public void shouldDetectInvalidBirthdayForDateInTheFuture() {
        User object = new User(
                1,
                "andrey@example.ru",
                "andrey",
                null,
                LocalDate.of(2992, 1, 30));

        Set<ConstraintViolation<User>> violations = validator.validate(object);

        assertEquals(violations.size(), 1);

        ConstraintViolation<User> violation = violations.iterator().next();
        assertEquals("birthday", violation.getPropertyPath().toString());
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }
}
