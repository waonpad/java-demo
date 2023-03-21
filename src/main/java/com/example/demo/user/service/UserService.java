package com.example.demo.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {

  @Autowired
  UserRepository userRepository;

  public List<User> searchAll() {
    return userRepository.findAll();
  }
}