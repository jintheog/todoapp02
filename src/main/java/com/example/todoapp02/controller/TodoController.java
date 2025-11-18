package com.example.todoapp02.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class TodoController {

    @GetMapping("/todos")
    public String todos() {
        return "todos";
    }

}
