package ru.yandex.practicum.filmorate.validation;

import ru.yandex.practicum.filmorate.validator.FutureOrDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDate;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = FutureOrDateValidator.class)
@Documented
public @interface FutureOrDate {
    String message() default "Дата должна быть ровна или более {value}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    public String value();
}
