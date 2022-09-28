package com.example.andre.sitodo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.andre.sitodo.model.TodoItem;
import com.example.andre.sitodo.repository.TodoListRepository;
import com.example.andre.sitodo.service.TodoListService;
@Controller
public class TodoListController {
	@Autowired
    private TodoListService todoListService;
	
	
    @Autowired
    public void setTodoListService(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping("/list")
    public String showList(Model model) {
        List<TodoItem> todoItems = todoListService.getTodoItems();
        model.addAttribute("todoList", todoItems);
        return "list";
    }
    
    @PostMapping("/list")
    public String createToDo(@RequestParam("item_text") String text) {
    	todoListService.addTodoItem(new TodoItem(text));
    	return "redirect:/list";
    }
}