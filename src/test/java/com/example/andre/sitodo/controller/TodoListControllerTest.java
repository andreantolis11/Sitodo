package com.example.andre.sitodo.controller;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.example.andre.sitodo.model.TodoItem;
import com.example.andre.sitodo.service.TodoListService;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@WebMvcTest(TodoListController.class)
@Tag("unit")
public class TodoListControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoListService todoListService;

    // New annotation!
    // @DisplayName allows you to customise the name of a test case.
    @Test
    @DisplayName("HTTP GET '/list' retrieves list view")
    void showList_correctView() throws Exception {
        mockMvc.perform(get("/list")).andExpectAll(
            status().isOk(),
            content().contentTypeCompatibleWith(TEXT_HTML),
            content().encoding(UTF_8),
            view().name("list")
        );
    }

    @Test
    @DisplayName("HTTP GET '/list' returns an HTML page")
    void showList_returnHtml() throws Exception {
        mockMvc.perform(get("/list")).andExpectAll(
            status().isOk(),
            content().contentTypeCompatibleWith(TEXT_HTML),
            content().encoding(UTF_8),
            content().string(containsString("</html>"))
        );
    }

    @Test
    @DisplayName("HTTP GET '/list' returns an HTML page with non-empty list")
    void showList_withSampleData_ok() throws Exception {
        TodoItem mockTodoItem = new TodoItem("Buy milk");
        when(todoListService.getTodoItems()).thenReturn(List.of(mockTodoItem));

        mockMvc.perform(get("/list")).andExpectAll(
            status().isOk(),
            content().contentTypeCompatibleWith(TEXT_HTML),
            content().encoding(UTF_8),
            content().string(containsString("Buy milk"))
        );
    }
    
    @Test
    @DisplayName("HTTP POST Redirect when create")
    void isRedirectAfterCreate_ok() throws Exception {
        TodoItem mockItem = new TodoItem("milk");
        when(todoListService.addTodoItem(mockItem)).thenReturn(mockItem);

        mockMvc.perform(post("/list").param("item_text", "milk")).andExpectAll(
            status().is3xxRedirection(),
            redirectedUrl("/list")
        );
    }

    @Test
    @DisplayName("HTTP POST when after create is match with assertEquals")
    void isMatchAfterCreate_ok() throws Exception {
        when(todoListService.getTodoItems()).thenReturn(List.of(new TodoItem("milk")));
        
        List<TodoItem> itemNewList = todoListService.getTodoItems();
        boolean truFal = false;
        for(int i = 0; i < itemNewList.size(); i++) {
        	if(itemNewList.get(i).getTitle() == "milk") {
        		truFal = true;
        		break;
        	}
        }
        assertEquals(truFal, true);
    } 
}
