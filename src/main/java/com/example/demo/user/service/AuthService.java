package com.example.demo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.role.entity.Role;
import com.example.demo.role.repository.RoleRepository;
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
  RoleRepository roleRepository;
  
	@Autowired
	private PasswordEncoder passwordEncoder;
  
  public User register(RegisterForm form) {
		String hashedPassword = passwordEncoder.encode(form.getPassword());

    User user = new User();
    user.setUsername(form.getUsername());
    user.setEmail(form.getEmail());
    user.setPassword(hashedPassword);

	  return userRepository.saveAndFlush(user);
	}

  public User login(LoginForm form) {
    User user = userRepository.findByEmail(form.getEmail());

    if (user == null) {
      return null;
    }
    if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
      return null;
    }

    return user;
  }

  public boolean addRole(User user, String roleName) {
    Role role = roleRepository.findByName(roleName);

    if (user == null) {
      return false;
    }
    if (role == null) {
      return false;
    }
    if (user.getRoles().contains(role)) {
      return true;
    }

    user.getRoles().add(role);

    userRepository.saveAndFlush(user);

    return true;
  }
}
