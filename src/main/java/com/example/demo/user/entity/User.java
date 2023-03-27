package com.example.demo.user.entity;

import java.util.ArrayList;
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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
  private List<Todo> todos = new ArrayList<>(); // 初期化でnullを回避

  @ManyToMany
  @JoinTable(
    name = "todo_helper",
    joinColumns = @JoinColumn(name = "helper_id"),
    inverseJoinColumns = @JoinColumn(name = "todo_id")
  )
  private List<Todo> helpTodos = new ArrayList<>();

  // @ManyToMany(mappedBy = "users")
  @ManyToMany
  @JoinTable(
    name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private List<Role> roles = new ArrayList<>();

  @Column(name = "username", unique = true)
  @NotNull
  private String username;

  @Column(name = "password")
  @NotNull
  private String password;

  @Column(name = "email", unique = true)
  @NotNull
  private String email;
}