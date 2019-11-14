package com.sundays.merryaround;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/main")
	public String indexPage() {
		return "index";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "member/login";
	}
	
	@GetMapping("/join")
	public String joinPage() {
		return "member/join";
	}
}
