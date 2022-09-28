package com.example.andre.sitodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.andre.sitodo.model.TodoItem;
import com.example.andre.sitodo.repository.TodoListRepository;

import java.util.List;

@Service
public class TodoListService {

    private TodoListRepository todoListRepository;

    @Autowired
    public void setTodoListRepository(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<TodoItem> getTodoItems() {
        return (List<TodoItem>) todoListRepository.findAll();
    }

    public TodoItem addTodoItem(TodoItem todoItem) {
        return todoListRepository.save(todoItem);
    }
    public TodoListService() {
    	
    }
}