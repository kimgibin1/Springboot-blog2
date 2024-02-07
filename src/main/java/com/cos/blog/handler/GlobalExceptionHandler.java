package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 익셉션은 해당 클래스로 오게 됨.
@RestController
public class GlobalExceptionHandler {

		@ExceptionHandler(value=IllegalArgumentException.class) // 모든 익셉션 시 Exception(모든 익셉션의 부모)으로 value 설정
		public String handleArgumentException(IllegalArgumentException e) {
			return "<h1>"+e.getMessage()+"</h1>";
		}
}
