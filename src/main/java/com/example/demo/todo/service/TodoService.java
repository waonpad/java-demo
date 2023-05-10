package com.example.demo.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.todo.entity.Todo;
import com.example.demo.todo.form.TodoCreateForm;
import com.example.demo.todo.form.TodoUpdateForm;
import com.example.demo.todo.repository.TodoRepository;
import com.example.demo.user.entity.User;
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

  public void create(TodoCreateForm form, User user) {
    Todo todo = new Todo();
    todo.setUser(user);
    todo.setContent(form.getContent());
    todo.setDeadline(form.getDeadline());
    todo.setDone(false);

    todoRepository.save(todo);
  }

  public void update(TodoUpdateForm form, User user) {
    Todo todo = todoRepository.findById(form.getId()).get();

    // 作成者と更新者が一致しているかチェック
    if (todo.getUser().getId() != user.getId()) {
      throw new RuntimeException("権限がありません");
    }

    todo.setContent(form.getContent());
    todo.setDeadline(form.getDeadline());
    todo.setDone(false);

    todoRepository.save(todo);
  }

  public void done (Long id) {
    Todo todo = todoRepository.findById(id).get();
    todo.setDone(true);

    todoRepository.save(todo);
  }

  public void delete (Long id) {
    todoRepository.deleteById(id);
  }

  public void help (Long id, User user) {
    Todo todo = todoRepository.findById(id).get();
    List<User> helpers = todo.getHelpers();

    if (helpers.stream().map(User::getId).anyMatch(helperId -> helperId == user.getId())) {
      // throw new RuntimeException("既にヘルプしています");
      return;
    }

    helpers.add(user);
    todo.setHelpers(helpers);

    todoRepository.save(todo);
  }

  public void unHelp (Long id, User user) {
    Todo todo = todoRepository.findById(id).get();
    List<User> helpers = todo.getHelpers();

    helpers.removeIf(helper -> helper.getId().equals(user.getId()));
    todo.setHelpers(helpers);

    todoRepository.save(todo);
  }
}