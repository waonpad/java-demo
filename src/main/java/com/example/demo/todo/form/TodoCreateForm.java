package com.example.demo.todo.form;

import java.util.Date;

import com.example.demo.user.entity.User;

import lombok.Data;

@Data
public class TodoCreateForm {
  
  private Long id;
  private User user;
  private String content;
  private Date deadline;
  private Boolean done;
}
