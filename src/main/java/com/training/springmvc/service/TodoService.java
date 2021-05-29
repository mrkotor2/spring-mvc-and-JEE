package com.training.springmvc.service;

import com.training.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todoList = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todoList.add(new Todo(1, "Alex", "Learn Spring MVC", new Date(),
                false));
        todoList.add(new Todo(2, "Alex", "Learn Struts", new Date(), false));
        todoList.add(new Todo(3, "Alex", "Learn Hibernate", new Date(),
                false));
    }

    public Todo retrieveTodo(int id) {
        for (Todo todo : todoList) {
            if (todo.getId() == id)
                return todo;
        }
        throw new RuntimeException("No such Todo with id " + id + " exist");
    }

    public void updateTodo(Todo todo) {
        todoList.remove(todo);
        todoList.add(todo);
    }

    public List<Todo> retrieveTodoList(String user) {
        List<Todo> filteredTodo = new ArrayList<Todo>();
        for (Todo todo : todoList) {
            if (todo.getUser().equals(user))
                filteredTodo.add(todo);
        }
        return filteredTodo;
    }

    public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
        todoList.add(new Todo(++todoCount, name, desc, targetDate, isDone));
    }

    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todoList.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }
}
