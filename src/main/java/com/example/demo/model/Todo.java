package com.example.demo.model;

import java.util.Date;

import lombok.Data;

@Data
public class Todo {
  private int id;
  private String title;
  private boolean completed;
  private Date createdAt;
}
