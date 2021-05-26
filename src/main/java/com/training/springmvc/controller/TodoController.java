package com.training.springmvc.controller;

import com.training.springmvc.Todo;
import com.training.springmvc.TodoService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("todoList", todoService.retrieveTodoList(name));
        return "list-todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showGetTodoPage(ModelMap model) {
        model.addAttribute("command", new Todo());
        return "todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)  //@TODO fix errors appearance in jsp
    public String addTodo(ModelMap model,
                          @ModelAttribute("todo") @Valid Todo todo,
                          BindingResult result) {

        if (result.hasErrors()) {
            log.error(result);
            model.addAttribute("command", todo);
//            model.put(BindingResult.class.getName() + ".todo", result);
//            model.addAttribute("description",  todo.getDescription());
            return "todo";
        }
        String username = (String) model.getAttribute("name");
        model.addAttribute("command", todo);

        todoService.addTodo(username, todo.getDescription(), new Date(), false);
        model.clear();// to prevent request parameter "name" to be passed
        return "redirect:/list-todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(ModelMap model, @RequestParam int id) {
        model.addAttribute("command", todoService.retrieveTodo(id));
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model,
                             @ModelAttribute("todo") @Valid Todo todo,
                             BindingResult result) {

        if (result.hasErrors()) {
            log.error(result);
            model.addAttribute("command", todo);
            return "todo";
        }
        String username = (String) model.getAttribute("name");
        model.addAttribute("command", todo);
        todo.setUser(username);                         //@TODO fix hardcoding
        todoService.updateTodo(todo);
        model.clear();// to prevent request parameter "name" to be passed
        return "redirect:/list-todo";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteTodo(id);
        return "redirect:/list-todo";
    }
}
