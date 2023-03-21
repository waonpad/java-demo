package com.example.demo.todo.entity;

import java.util.Date;
import java.util.List;

import com.example.demo.common.entity.BaseEntity;
import com.example.demo.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true) // https://ohina.work/post/java-equalsandhashcode/
@Table(name = "todo")
public class Todo extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
      name = "todo_helper",
      joinColumns = @JoinColumn(name = "todo_id"),
      inverseJoinColumns = @JoinColumn(name = "helper_id")
    )
    private List<User> helperList;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "content")
    private String content;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "done")
    private Boolean done;
}