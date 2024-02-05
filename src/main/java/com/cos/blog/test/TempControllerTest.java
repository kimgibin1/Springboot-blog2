package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 파일을 리턴함
public class TempControllerTest {
	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		//파일리턴 기본 경로 : src/main/resources/static
		// 리턴명 : /home.html
		//풀경로 : src/main/resources/static/home.html
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public String tempImg() {
		return "/1.jpg";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		// prefix : /WEB-INF/views/   -- yml세팅 경로
		//suffix : .jsp   --yml세팅 경로
		// 풀네임 : /WEB-INF/views/test.jsp
		return "test";
	}
}
