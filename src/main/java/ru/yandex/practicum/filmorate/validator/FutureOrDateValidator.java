package ru.yandex.practicum.filmorate.validator;

import ru.yandex.practicum.filmorate.validation.FutureOrDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class FutureOrDateValidator implements ConstraintValidator<FutureOrDate, LocalDate> {
    private LocalDate startedAt;

    public void initialize(FutureOrDate constraintAnnotation) {
        startedAt = LocalDate.parse(constraintAnnotation.value());
    }

    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return localDate.isEqual(startedAt) || localDate.isAfter(startedAt);
    }
}
