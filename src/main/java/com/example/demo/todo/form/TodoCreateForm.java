package com.example.demo.todo.form;

import java.util.Date;

import lombok.Data;

@Data
public class TodoCreateForm {
  private Long id;
  private Long userId;
  private String content;
  private Date deadline;
  private Boolean done;
}
