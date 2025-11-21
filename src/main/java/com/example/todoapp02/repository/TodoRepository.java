package com.example.todoapp02.repository;

import com.example.todoapp02.dto.TodoDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TodoRepository {
    private final Map<Long, TodoDTO> storage = new ConcurrentHashMap<>();
    private Long nextId = 1L;

    public TodoDTO save(TodoDTO todo) {
        if(todo.getId()==null){
            todo.setId(nextId++);
        }
        storage.put(todo.getId(), todo);
        return todo;
    }

    public List<TodoDTO> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<TodoDTO> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }
    public void deleteById(Long id) {
        storage.remove(id);
    }

    public List<TodoDTO> findByTitleContaining(String keyword) {
        return storage.values().stream()
                .filter((todo)-> todo.getTitle().contains(keyword))
                .toList();
    }

}
