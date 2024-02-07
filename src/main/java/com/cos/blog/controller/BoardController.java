package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
		@GetMapping({"","/"})
		public String index() {
			// yml 설정 경로 : /WEB-INF/views/index.jsp
			return "index";
		}
}
