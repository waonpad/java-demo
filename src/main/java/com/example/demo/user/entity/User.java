package com.example.demo.user.entity;

import java.util.Date;
import java.util.List;

import com.example.demo.todo.entity.Todo;

// javaxからjakartaに変更
// https://teratail.com/questions/1kcvjvej7eal4s
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany
  private List<Todo> todoList;

  @ManyToMany
  private List<Todo> helperList;

  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;

  @Column(name = "phone")
  private String phone;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "delete_date")
  private Date deleteDate;
}