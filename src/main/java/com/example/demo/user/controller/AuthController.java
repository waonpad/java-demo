package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.user.form.LoginForm;
import com.example.demo.user.form.RegisterForm;
import com.example.demo.user.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {
  
  @Autowired
  AuthService authService;

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
  public String register(Model model, @ModelAttribute("form") @Validated RegisterForm form, BindingResult result) {
    if(result.hasErrors()) {
      return PREFIX + "register";
    }

    authService.register(form);

    return REDIRECT_URL;
  }

  @PostMapping("login")
  public String login(Model model, @ModelAttribute("form") @Validated LoginForm form) {
    authService.login(form);

    return REDIRECT_URL;
  }

  // ログアウト処理はSpring Securityで自動的に行われる
}
