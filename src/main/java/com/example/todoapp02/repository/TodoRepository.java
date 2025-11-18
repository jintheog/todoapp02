package com.example.todoapp02.repository;

import com.example.todoapp02.dto.TodoDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TodoRepository {
    private final Map<Long, TodoDTO> storage = new ConcurrentHashMap<>();
    private Long nextId = 1L;

    public TodoDTO save(TodoDTO todo) {
        todo.setId(nextId++);
        storage.put(todo.getId(), todo);
        return todo;
    }

    public List<TodoDTO> findAll() {
        return new ArrayList<>(storage.values());
    }

    public TodoDTO findById(Long id) {
        return storage.get(id);
    }
}
