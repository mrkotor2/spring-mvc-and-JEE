package com.training.rest;

import com.training.model.Todo;
import com.training.springmvc.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoRestController { //@TODO do something with response

    private final TodoService todoService;

    @Autowired
    public TodoRestController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "/todo/", method = RequestMethod.GET)
    public List<Todo> listAllTodoList() {
        return todoService.retrieveTodoList("Alex");
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    public Todo retrieveTodo(@PathVariable("id") int id) {
        return todoService.retrieveTodo(id);
    }
}
