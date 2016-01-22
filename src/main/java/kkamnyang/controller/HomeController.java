package kkamnyang.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		System.out.println("Home Controller...");
		return "home";
	}

}
