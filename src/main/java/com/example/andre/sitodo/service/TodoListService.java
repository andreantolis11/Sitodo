package com.example.andre.sitodo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.andre.sitodo.model.TodoItem;
import com.example.andre.sitodo.model.TodoList;
import com.example.andre.sitodo.repository.TodoListRepository;

@Service
public class TodoListService {

    private static final Logger LOG = LoggerFactory.getLogger(TodoListService.class);
    private static final String TODO_LIST_DOES_NOT_EXIST_FMT = "TodoList(id=%d) does not exist";

    private TodoListRepository todoListRepository;

    @Autowired
    public void setTodoListRepository(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public TodoList getTodoListById(Long id) throws NoSuchElementException {
        Optional<TodoList> result = todoListRepository.findById(id);

        return result.get();
    }

    public TodoList addTodoItem(TodoItem todoItem) {
        TodoList list = new TodoList(List.of(todoItem));

        return todoListRepository.save(list);
    }

    public TodoList addTodoItem(Long id, TodoItem todoItem) throws NoSuchElementException {
        Optional<TodoList> result = todoListRepository.findById(id);

        TodoList foundList = result.get();
        foundList.addTodoItem(todoItem);

        return todoListRepository.save(foundList);
    }

    public TodoList updateTodoItem(Long listId, Long itemId, Boolean status) throws NoSuchElementException {
        Optional<TodoList> result = todoListRepository.findById(listId);
        
        if(result.isEmpty()) {
        	final String msg = String.format(TODO_LIST_DOES_NOT_EXIST_FMT, listId);
        	throw new NoSuchElementException(msg);
        }
        
        TodoList foundList = result.get();
        for(int i = 0; i < foundList.getItems().size(); i++) {
        	if(foundList.getItems().get(i).getId().equals(itemId)) {
        		foundList.getItems().get(i).setFinished(status);
        	}
        }

        return todoListRepository.save(foundList);
    }

    public Boolean deleteTodoItem(Long listId, Long itemId) throws NoSuchElementException {
        Optional<TodoList> result = todoListRepository.findById(listId);
        TodoList foundList = result.get();
        int id = 0;
        for(int i = 0; i < foundList.getItems().size(); i++) {
        	if(foundList.getItems().get(i).getId().equals(itemId)) {
        		id = i;
        		break;
        	}
        }
    	foundList.getItems().remove(id);
    	todoListRepository.save(foundList);
        return Boolean.TRUE;

        // TODO: Implement me!
        
    }
}