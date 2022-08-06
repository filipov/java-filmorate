package ru.yandex.practicum.filmorate.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Store<T extends Model> {
    private static int lastId = 0;

    private final HashMap<Integer, T> state = new HashMap<>();

    public List<T> getList() {
        return new ArrayList<>(state.values());
    }

    public T add(T newItem) {
        newItem.setId(newId());

        state.put(newItem.getId(), newItem);

        return newItem;
    }

    public T update(T item) {
        if (state.containsKey(item.getId())) {
            state.put(item.getId(), item);

            return item;
        }

        return null;
    }

    private int newId() {
        lastId++;

        return lastId;
    }
}