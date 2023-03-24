package com.example.demo.user.form;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterForm {

  @NotBlank
  @Size(min = 4, max = 20)
  private String username;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  @Size(min = 8)
  private String password;

  @NotBlank
  @Size(min = 8)
  private String confirmPassword;
  
	@AssertTrue
	public boolean isPasswordValid() {
		if(password == null || confirmPassword == null) {
			return false;
		}
		return password.equals(confirmPassword);
	}
}
