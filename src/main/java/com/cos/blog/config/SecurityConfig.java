package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.blog.model.User;


@Configuration // 빈등록 (IoC관리)
@EnableWebSecurity // 시큐리티 필터가 등록이 된다
public class SecurityConfig {
	
	@Bean // IoC
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean // 스프링 컨테이너에서 객체를 관리할수있게 하는것
	public  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests((auth) -> auth
/*        		.requestMatchers("/","/customer/**","/report/list/index","/register/**","/request-key/*","/auth/loginForm").permitAll()
        		.requestMatchers("/board/**","/mypage/**","/report/**","/atention/**").hasAnyRole("USER","ADMIN")
        		// .requestMatchers("/auth/**").hasAnyRole("ADMIN")
        		.anyRequest().authenticated());*/
        		.requestMatchers("/").hasAnyRole("ADMIN")
        		.anyRequest().permitAll()
	);
        http 
        .formLogin(form -> form
        		.usernameParameter("username")
        		.passwordParameter("password")
				 .loginPage("/auth/loginForm") 
        		.loginProcessingUrl("/auth/loginForm")
        		.defaultSuccessUrl("/")
        		.permitAll());
        		

        return http.getOrBuild();
	}
}
