package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.BeforeArticleInterceptor;
import com.example.demo.interceptor.NeedLoginInterceptor;
import com.example.demo.interceptor.NeedLogoutInterceptor;

@Configuration
public class MyMvcConfigurer implements WebMvcConfigurer {
	@Autowired
	BeforeArticleInterceptor beforeArticleInterceptor;
	
	@Autowired
	NeedLoginInterceptor needLoginInterceptor;
	
	@Autowired
	NeedLogoutInterceptor needLogoutInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 모든 요청이 들어오기 전에 before 인터셉터를 활용하겠다.
		registry.addInterceptor(beforeArticleInterceptor).addPathPatterns("/**");
		
		registry.addInterceptor(needLoginInterceptor).addPathPatterns("/usr/article/write").addPathPatterns("/usr/article/doWrite")
		.addPathPatterns("/usr/article/modify").addPathPatterns("/usr/article/doModify").addPathPatterns("/usr/article/doDelete")
		.addPathPatterns("/usr/member/doLogout");
		
		registry.addInterceptor(needLogoutInterceptor).addPathPatterns("/usr/member/login").addPathPatterns("/usr/member/doLogin")
		.addPathPatterns("/usr/member/join").addPathPatterns("/usr/member/doJoin");
	}
}
