package tech.marie.todolist.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(int id) {
        super(String.valueOf(id));


    }

}
