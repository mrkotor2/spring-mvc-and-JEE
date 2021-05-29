package com.training.springmvc.controller;

import com.training.model.Todo;
import com.training.springmvc.service.TodoService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
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

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @RequestMapping(value = "/list-todo", method = RequestMethod.GET)
    public String showTodoList(ModelMap model) {
        String name = getLoggedInUserName();
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
        String name = getLoggedInUserName();
        model.addAttribute("command", todo);

        todoService.addTodo(name, todo.getDescription(), todo.getTargetDate(), false);
        log.info("Todo " + todo + " was added successfully");
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
        log.info("Todo " + todo + " was updated successfully");

        model.clear();// to prevent request parameter "name" to be passed
        return "redirect:/list-todo";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteTodo(id);
        log.info("Todo with id" + id + " was removed successfully");

        return "redirect:/list-todo";
    }

    private String getLoggedInUserName() { //@TODO remove duplicate
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
    }


}
