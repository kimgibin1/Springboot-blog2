package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice // 모든 익셉션은 해당 클래스로 오게 됨.
@RestController
public class GlobalExceptionHandler {

		@ExceptionHandler(value=Exception.class) // 모든 익셉션 시 Exception(모든 익셉션의 부모)
		public ResponseDto<String> handleArgumentException(Exception e) {
			return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}
}
