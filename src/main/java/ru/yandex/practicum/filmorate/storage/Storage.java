package ru.yandex.practicum.filmorate.storage;

import java.util.ArrayList;
import java.util.List;

public interface Storage<T> {
    List<T> getList();

    T add(T newItem);

    T update(T item);
}