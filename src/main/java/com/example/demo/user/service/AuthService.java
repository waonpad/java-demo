package com.example.demo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.user.entity.User;
import com.example.demo.user.form.LoginForm;
import com.example.demo.user.form.RegisterForm;
import com.example.demo.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class AuthService {
  
  @Autowired
  UserRepository userRepository;
  
	@Autowired
	private PasswordEncoder passwordEncoder;
  
  public void register(RegisterForm form) {
		String hashedPassword = passwordEncoder.encode(form.getPassword());

    User user = new User();
    user.setUsername(form.getUsername());
    user.setEmail(form.getEmail());
    user.setPassword(hashedPassword);

		userRepository.saveAndFlush(user);
	}

  public void login(LoginForm form) {
    User user = userRepository.findByEmail(form.getEmail());
    if (user == null) {
      throw new RuntimeException("User not found");
    }
    if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
      throw new RuntimeException("Password is incorrect");
    }
  }
}
