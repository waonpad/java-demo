package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.user.entity.User;
import com.example.demo.user.form.LoginForm;
import com.example.demo.user.form.RegisterForm;
import com.example.demo.user.service.AuthService;
import com.example.demo.user.service.UserDetailsImpl;
import com.example.demo.user.service.UserService;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/auth")
public class AuthController {
  
  @Autowired
  AuthService authService;

  @Autowired
  UserService userService;

  private static final String PREFIX = "auth/";
  private static final String REDIRECT_URL = "redirect:/";
  
  @GetMapping("register")
  public String displayRegister(Model model, @ModelAttribute("form") RegisterForm form) {
    return PREFIX + "register";
  }

  @GetMapping("login")
  public String displayLogin(Model model, @ModelAttribute("form") LoginForm form) {
    return PREFIX + "login";
  }

  @PostMapping("register")
  @Transactional(rollbackOn = Exception.class)
  public String register(Model model, @ModelAttribute("form") @Validated RegisterForm form, BindingResult result) {
    if(result.hasErrors()) {
      return PREFIX + "register";
    }

    User registeredUser = authService.register(form);
    
    authService.addRole(registeredUser, "ROLE_USER");

    return REDIRECT_URL;
  }

  @PostMapping("login")
  public String login(Model model, @ModelAttribute("form") @Validated LoginForm form) {
    authService.login(form);

    return REDIRECT_URL;
  }

  // ログアウト処理はSpring Securityで自動的に行われる

  @PostMapping("addRole")
  public String addRole(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    authService.addRole(userDetails.getUser(), "ROLE_ADMIN");

    return REDIRECT_URL;
  }
}
