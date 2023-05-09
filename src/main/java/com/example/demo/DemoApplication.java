package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.demo.config.LoggingFilter;
import com.example.demo.config.Utf8Filter;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args); // ここで起動される
	}

	@Bean
	public FilterRegistrationBean<?> utf8Filter() { // 文字コードをUTF-8に変換するフィルタを登録する
		FilterRegistrationBean<Utf8Filter> registrationBean = new FilterRegistrationBean<>(new Utf8Filter());
		// 一番最初に実行されるようにする
		registrationBean.setOrder(Integer.MIN_VALUE);
		registrationBean.addUrlPatterns("/*"); // すべてのパスに対してフィルタを適用する
		return registrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<?> loggingFilter() { // ログ出力用のフィルタを登録する
		FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>(new LoggingFilter());
		registrationBean.setOrder(Integer.MIN_VALUE + 1); // 他のフィルタより先に実行されるようにする
		registrationBean.addUrlPatterns("/*"); // すべてのパスに対してフィルタを適用する
		return registrationBean;
	}
}
