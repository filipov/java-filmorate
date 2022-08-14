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

public class FilmTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void shouldHaveNoViolations() {
        Film object = new Film(
            "Какой-то фильм",
            "Описание фильма",
            LocalDate.of(2000, 1, 1),
            1234
        );

        Set<ConstraintViolation<Film>> violations = validator.validate(object);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldDetectBlankName() {
        Film object = new Film(
                "",
                "Описание фильма",
                LocalDate.of(2000, 1, 1),
                1234
        );

        Set<ConstraintViolation<Film>> violations = validator.validate(object);

        assertEquals(violations.size(), 1);

        ConstraintViolation<Film> violation = violations.iterator().next();
        assertEquals("name", violation.getPropertyPath().toString());

    }

    @Test
    public void shouldDetectEmptyName() {
        Film object = new Film(
                null,
                "Описание фильма",
                LocalDate.of(2000, 1, 1),
                1234
        );

        Set<ConstraintViolation<Film>> violations = validator.validate(object);

        assertEquals(violations.size(), 1);

        ConstraintViolation<Film> violation = violations.iterator().next();
        assertEquals("name", violation.getPropertyPath().toString());

    }

    @Test
    public void shouldDetectInvalidDescriptionForTooManyCharacters() {
        Film object = new Film(
                "Какой-то фильм",
                "Описание фильма в 200 символов, Описание фильма в 200 символов, Описание фильма в 200 " +
                        "символов, Описание фильма в 200 символов, Описание фильма в 200 символов, Описание фильма " +
                        "в 200 символов, Описание фильма в 200 символов, Описание фильма в 200 символов, Описание " +
                        "фильма в 200 символов, Описание фильма в 200 символов, Описание фильма в 200 символов,",
                LocalDate.of(2000, 1, 1),
                1234
        );

        Set<ConstraintViolation<Film>> violations = validator.validate(object);

        assertEquals(violations.size(), 1);

        ConstraintViolation<Film> violation = violations.iterator().next();
        assertEquals("description", violation.getPropertyPath().toString());
    }

    @Test
    public void shouldDetectInvalidDurationForNegativeValue() {
        Film object = new Film(
                "Какой-то фильм",
                "Описание фильма",
                LocalDate.of(2000, 1, 1),
                -1234
        );

        Set<ConstraintViolation<Film>> violations = validator.validate(object);

        assertEquals(violations.size(), 1);

        ConstraintViolation<Film> violation = violations.iterator().next();
        assertEquals("duration", violation.getPropertyPath().toString());
    }

    @Test
    public void shouldDetectInvalidDurationForZeroValue() {
        Film object = new Film(
                "Какой-то фильм",
                "Описание фильма",
                LocalDate.of(2000, 1, 1),
                0
        );

        Set<ConstraintViolation<Film>> violations = validator.validate(object);

        assertEquals(violations.size(), 1);

        ConstraintViolation<Film> violation = violations.iterator().next();
        assertEquals("duration", violation.getPropertyPath().toString());
    }

    @Test
    public void shouldDetectInvalidReleaseDate() {
        Film object = new Film(
                "Какой-то фильм",
                "Описание фильма",
                LocalDate.of(1000, 1, 1),
                1234
        );

        Set<ConstraintViolation<Film>> violations = validator.validate(object);

        assertEquals(violations.size(), 1);

        ConstraintViolation<Film> violation = violations.iterator().next();
        assertEquals("releaseDate", violation.getPropertyPath().toString());
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }
}
