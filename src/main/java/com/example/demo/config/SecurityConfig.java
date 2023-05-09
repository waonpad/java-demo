package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// https://qiita.com/suke_masa/items/908805dd45df08ba28d8

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  // favicon.icoを配置しないと404でエラーになる

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.formLogin(login -> login
                      .loginProcessingUrl("/auth/login") // ログイン処理のパス
                      .loginPage("/auth/login") // ログインページのパス
                      .usernameParameter("username") // ユーザー名のリクエストパラメータ名
                      .passwordParameter("password") // パスワードのリクエストパラメータ名
                      .defaultSuccessUrl("/general") // ログイン成功時の遷移先
                      .failureUrl("/auth/login?error") // ログイン失敗時の遷移先
                      .permitAll() // ログインページは誰でもアクセス可能
      ).logout(logout -> logout
                      .logoutUrl("/auth/logout") // ログアウト処理のパス
                      .logoutSuccessUrl("/") // ログアウト後の遷移先
      ).authorizeHttpRequests(authz -> authz
                      .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // 静的リソースへのアクセスは認可不要
                      .requestMatchers("/").permitAll() // トップページは誰でもアクセス可能
                      .requestMatchers("/auth/register").permitAll() // 登録ページは誰でもアクセス可能
                      .requestMatchers("/general").hasRole("USER") // 一般ユーザーのみアクセス可能
                      .requestMatchers("/admin").hasRole("ADMIN") // 管理者のみアクセス可能
                      .anyRequest().authenticated() // それ以外は認証が必要
                      // .anyRequest().permitAll() // テスト用
      ).exceptionHandling(handling -> handling
                      .accessDeniedPage("/403") // アクセス拒否された時に遷移するパス
      ).rememberMe(rememberMe -> rememberMe
                      .key("remember-me") // RememberMeのキー
                      .rememberMeParameter("remember-me") // RememberMeのリクエストパラメータ名
                      .tokenValiditySeconds(86400) // RememberMeの有効期限(秒)
      );
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() { // パスワードのハッシュ化
    return new BCryptPasswordEncoder();
  }
}