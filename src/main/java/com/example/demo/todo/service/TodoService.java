package com.example.demo.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.todo.entity.Todo;
import com.example.demo.todo.form.TodoCreateForm;
import com.example.demo.todo.form.TodoUpdateForm;
import com.example.demo.todo.repository.TodoRepository;
import com.example.demo.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class TodoService {

  @Autowired
  TodoRepository todoRepository;
  UserRepository userRepository;

  public List<Todo> getTodoList () {
    return todoRepository.findAll();
  }

  public Todo getTodo (Long id) {
    return todoRepository.findById(id).get();
  }

  public List<Todo> getTodoListByUserId (Long userId) {
    return todoRepository.findByUserId(userId);
  }

  public void create(TodoCreateForm form) {
    Todo todo = new Todo();
    // todo.setUser(form.getUser());
    todo.setUser(userRepository.findAll().get(0));
    todo.setContent(form.getContent());
    todo.setDeadline(form.getDeadline());

    todoRepository.save(todo);
  }

  public void update(TodoUpdateForm form) {
    Todo todo = todoRepository.findById(form.getId()).get();
    todo.setContent(form.getContent());
    todo.setDeadline(form.getDeadline());

    todoRepository.save(todo);
  }

  public void done (Long id) {
    Todo todo = todoRepository.findById(id).get();
    todo.setDone(true);

    todoRepository.save(todo);
  }
}