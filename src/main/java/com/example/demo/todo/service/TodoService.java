package com.example.demo.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.todo.entity.Todo;
import com.example.demo.todo.form.TodoCreateForm;
import com.example.demo.todo.form.TodoUpdateForm;
import com.example.demo.todo.repository.TodoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class TodoService {

  @Autowired
  TodoRepository todoRepository;

  public List<Todo> getTodoList () {
    return todoRepository.findAll();
  }

  public Todo getTodo (Long id) {
    return todoRepository.findById(id).get();
  }

  public List<Todo> getTodoListByUserId (Long userId) {
    return todoRepository.findByUserId(userId);
  }

  public void create(TodoCreateForm request) {
    Todo todo = new Todo();
    todo.setUserId(request.getUserId());
    todo.setContent(request.getContent());
    todo.setDeadline(request.getDeadline());

    todoRepository.save(todo);
  }

  public void update(TodoUpdateForm request) {
    Todo todo = todoRepository.findById(request.getId()).get();
    todo.setContent(request.getContent());
    todo.setDeadline(request.getDeadline());

    todoRepository.save(todo);
  }

  public void done (Long id) {
    Todo todo = todoRepository.findById(id).get();
    todo.setDone(true);

    todoRepository.save(todo);
  }
}