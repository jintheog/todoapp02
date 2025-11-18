package com.example.todoapp02.repository;

import com.example.todoapp02.dto.TodoDTO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TodoRepository {
    private final Map<Long, TodoDTO> storage = new ConcurrentHashMap<>();
    private Long nextId = 1L;

    public TodoDTO save(TodoDTO todo) {
        todo.setId(nextId++);
        storage.put(todo.getId(), todo);
        return todo;
    }
}
