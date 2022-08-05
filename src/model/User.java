package model;

import java.time.LocalDate;
import java.util.Objects;
import lombok.*;

@Data
public class User {
    private static int lastId = 0;

    private int id;
    private String email;
    private String login;
    private String name;
    private LocalDate birthdate;

    public static getNewId() {
        lastId++;

        return lastId;
    }
}