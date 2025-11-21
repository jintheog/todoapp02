package com.example.todoapp02.controller;

import com.example.todoapp02.dto.TodoDTO;
import com.example.todoapp02.repository.TodoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TodoController {
    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

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

    @GetMapping("/todos/{id}")
    public String detail(@PathVariable Long id, Model model) {
        try {
            TodoDTO todo = todoRepository.findById(id)
                    .orElseThrow(()-> new IllegalArgumentException("todo not found"));

            model.addAttribute("todo",todo);
            return "detail";
        } catch (IllegalArgumentException e) {
            return "redirect:/todos";
        }

    }

    @GetMapping("/todos/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        todoRepository.deleteById(id);
        return "redirect:/todos";
    }

    @GetMapping("/todos/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        try {
            TodoDTO todo = todoRepository.findById(id)
                            .orElseThrow(()-> new IllegalArgumentException("todo not found"));
            model.addAttribute("todo",todo);
            return "edit";
        } catch (IllegalArgumentException e) {
            return "redirect:/todos";
        }

    }

    @GetMapping()
    public String update(
            @RequestParam Long id,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam(defaultValue="false") Boolean complete,
            Model model) {
        try{
            TodoDTO todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("to do not found"));

            todo.setTitle(title);
            todo.setContent(content);
            todo.setCompleted(complete);

            todoRepository.save(todo);

            return "redirect:/todos" + id;
        } catch (IllegalArgumentException e) {
            return "redirect:/todos";
        }
    }

    @GetMapping("/todos/search")
    public String search(@RequestParam String keyword, Model model){
        List<TodoDTO> todos = todoRepository.findByTitleContaining(keyword);
        model.addAttribute("todos", todos);
        return "todos";
    }
}
