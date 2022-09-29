package com.example.andre.sitodo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.andre.sitodo.model.TodoList;

@Repository
public interface TodoListRepository extends CrudRepository<TodoList, Long>{

}
