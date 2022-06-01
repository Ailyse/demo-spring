package com.formation.service;

import com.formation.dao.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.formation.entity.Todo;

import java.util.List;

@RestController
public class TodoService {

    @Autowired
    private TodoRepository todoJpa;

    @CrossOrigin(origins ="*")
    @GetMapping(path = "/todos")
    public List<Todo> getAllTodos(){

        return todoJpa.findAll();
    }

    @GetMapping(path = "/todos/{id}")
    public Todo getTodo(@PathVariable Integer id) {
        return todoJpa.findById(id).get();
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id){
        todoJpa.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/todos")
    public ResponseEntity<Void> createTodo(@RequestBody Todo todo){

        Todo newTodo=todoJpa.save(todo);

        System.out.println(newTodo);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Integer id, @RequestBody Todo todo){

        todo.setId(id);

        Todo newTodo=todoJpa.save(todo);

        System.out.println(newTodo);

        return new ResponseEntity<Todo>(newTodo, HttpStatus.OK);
    }
}
