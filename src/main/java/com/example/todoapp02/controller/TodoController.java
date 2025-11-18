package com.example.todoapp02.controller;

import com.example.todoapp02.dto.TodoDTO;
import com.example.todoapp02.repository.TodoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TodoController {
    private final TodoRepository todoRepository = new TodoRepository();


    @GetMapping("/todos")
    public String todos(Model model) {
        List<TodoDTO> todos = todoRepository.findAll();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @GetMapping("/todos/new")
    public String newTodo() {
        return "new";
    }

    @GetMapping("/todos/create")
    public String create(
            @RequestParam String title,
            @RequestParam String content,
            Model model
    ){
        TodoDTO todoDTO = new TodoDTO(null, title, content, false);

        TodoDTO todo = todoRepository.save(todoDTO);
        model.addAttribute("todo",todo);

        return "redirect:/todos";
    }

}
