package com.example.demo.user.entity;

// import java.util.Date;
import java.util.List;

import com.example.demo.common.entity.BaseEntity;
import com.example.demo.role.entity.Role;
import com.example.demo.todo.entity.Todo;

// javaxからjakartaに変更
// https://teratail.com/questions/1kcvjvej7eal4s
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "user")
public class User extends BaseEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "user")
  private List<Todo> todos;

  @ManyToMany(mappedBy = "helpers")
  private List<Todo> helpers;

  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;
}