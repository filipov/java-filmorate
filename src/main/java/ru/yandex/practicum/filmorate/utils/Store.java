package ru.yandex.practicum.filmorate.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Store<T extends Model> {
    private static int lastId = 0;

    private final HashMap<Integer, T> state = new HashMap<>();

    private static final Logger log = LoggerFactory.getLogger(Store.class);

    public List<T> getList() {
        log.debug("Всего элементов: {}", state.values().size());

        return new ArrayList<>(state.values());
    }

    public T add(T newItem) {
        newItem.setId(newId());

        state.put(newItem.getId(), newItem);

        log.debug("Новый элемент: {}", newItem);

        return newItem;
    }

    public T update(T item) {
        if (state.containsKey(item.getId())) {
            state.put(item.getId(), item);

            log.debug("Обновлен элемент: {}", item);

            return item;
        }

        log.debug("Элемент не найден: {}", item);

        return null;
    }

    private int newId() {
        lastId++;

        return lastId;
    }
}