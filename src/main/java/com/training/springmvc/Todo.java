package com.training.springmvc;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class Todo {

    private int id;
    private String user;

    @Size(min = 5, message = "Enter at least 5 Characters.")
    private String description;

    private Date targetDate;
    private boolean isDone;

    public Todo(int id, String user, String description, Date targetDate, boolean isDone) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    public Todo() {
        super();
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id &&
                isDone == todo.isDone &&
                Objects.equals(user, todo.user) &&
                Objects.equals(description, todo.description) &&
                Objects.equals(targetDate, todo.targetDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, description, targetDate, isDone);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                ", isDone=" + isDone +
                '}';
    }
}
