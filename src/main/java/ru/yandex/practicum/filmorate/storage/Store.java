package ru.yandex.practicum.filmorate.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.practicum.filmorate.utils.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Store<T extends Model> implements Storage<T>{
    private int lastId = 0;

    private final HashMap<Integer, T> state = new HashMap<>();

    private static final Logger log = LoggerFactory.getLogger(Store.class);

    public List<T> getList() {
        log.debug("Всего элементов: {}", state.values().size());

        return new ArrayList<>(state.values());
    }

    public T get(Integer id) {
        T item = state.get(id);

        log.debug("Найден элемент: {}", item);

        return item;
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
