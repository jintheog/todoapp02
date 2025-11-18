package com.example.todoapp02;

import com.example.todoapp02.dto.TodoDTO;
import com.example.todoapp02.repository.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Todoapp02Application {

    public static void main(String[] args) {
        SpringApplication.run(Todoapp02Application.class, args);
    }

    @Bean
    public CommandLineRunner init(TodoRepository todoRepository) {
        return args -> {
            todoRepository.save(new TodoDTO(null, "study", "JAVA", false));
            todoRepository.save(new TodoDTO(null, "cook", "ramen", false));
            todoRepository.save(new TodoDTO(null, "workout", "running", false));
        };
    }
}
