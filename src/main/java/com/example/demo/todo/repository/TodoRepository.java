package com.example.demo.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.todo.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

  List<Todo> findByUserId(Long id);
}