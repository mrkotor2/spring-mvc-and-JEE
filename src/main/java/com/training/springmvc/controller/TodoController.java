package com.training.springmvc.controller;

import com.training.springmvc.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "/list-todo", method = RequestMethod.GET)
    public String retrieveTodo(ModelMap model) {
        String name = (String) model.get("name");
        model.addAttribute("todoList", todoService.retrieveTodo(name));
        return "list-todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String addTodo() {
        return "todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @RequestParam String description) {
        String user = (String) model.getAttribute("name");
        todoService.addTodo(user, description, new Date(), false);
        model.clear();// to prevent request parameter "name" to be passed
        return "redirect:/list-todo";
    }
}
