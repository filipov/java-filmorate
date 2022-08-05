package model;

import java.time.LocalDate;
import java.util.Objects;
import lombok.*;

@Data
public class Film {
    private static int lastId = 0;

    private int id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Duration duration;

    public static getNewId() {
        lastId++;

        return lastId;
    }
}