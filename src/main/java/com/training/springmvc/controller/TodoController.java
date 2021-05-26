package com.training.springmvc.controller;

import com.training.springmvc.Todo;
import com.training.springmvc.TodoService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Date;

@Log4j
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
    public String addTodo(ModelMap model) {
        model.addAttribute("command", new Todo());
        return "add-todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        String username = (String) model.getAttribute("name");
        model.addAttribute("command",  todo);

        if (result.hasErrors()) {
            log.error(result);
            return "add-todo";
        }
        todoService.addTodo(username, todo.getDescription(), new Date(), false);
        model.clear();// to prevent request parameter "name" to be passed
        return "redirect:/list-todo";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteTodo(id);
        return "redirect:/list-todo";
    }
}
