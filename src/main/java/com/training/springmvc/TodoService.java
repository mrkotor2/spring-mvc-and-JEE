package com.training.springmvc;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todo = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todo.add(new Todo(1, "Alex", "Learn Spring MVC", new Date(),
                false));
        todo.add(new Todo(2, "Alex", "Learn Struts", new Date(), false));
        todo.add(new Todo(3, "Alex", "Learn Hibernate", new Date(),
                false));
    }

    public List<Todo> retrieveTodo(String user) {
        List<Todo> filteredTodo = new ArrayList<Todo>();
        for (Todo todo : todo) {
            if (todo.getUser().equals(user))
                filteredTodo.add(todo);
        }
        return filteredTodo;
    }

    public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
        todo.add(new Todo(++todoCount, name, desc, targetDate, isDone));
    }

    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todo.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }
}
