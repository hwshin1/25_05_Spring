package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.BeforeArticleInterceptor;

@Configuration
public class MyMvcConfigurer implements WebMvcConfigurer {
	@Autowired
	BeforeArticleInterceptor beforeArticleInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 모든 요청이 들어오기 전에 before 인터셉터를 활용하겠다.
		registry.addInterceptor(beforeArticleInterceptor).addPathPatterns("/**");
	}
}
