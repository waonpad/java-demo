package com.example.demo.user.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.user.entity.User;

public class UserDetailsImpl implements UserDetails {
  
  private final User user;

  public UserDetailsImpl(User user) {
    this.user = user;
  }

  // public User getUser() { // --- Entityである Userを返却するメソッド
  //   return user;
  // }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() { // --- ユーザに与えられている権限リストを返却するメソッド
    return AuthorityUtils.createAuthorityList("ROLE_" + this.user.getRoleName());
  }

  @Override
  public String getPassword() { // --- 登録されているパスワードを返却するメソッド
    return this.user.getPassword();
  }

  @Override
  public String getUsername() { // --- 登録されているユーザ名を返却するメソッド
    return this.user.getUserName();
  }


  @Override
  public boolean isAccountNonExpired() { // --- アカウントの有効期限の状態を判定するメソッド
    return true;
  }

  @Override
  public boolean isAccountNonLocked() { // --- アカウントのロック状態を判定するメソッド
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() { // --- 資格情報の有効期限の状態を判定するメソッド
    return true;
  }

  @Override
  public boolean isEnabled() { // --- 有効なユーザかを判定するメソッド
    return true;
  }
}