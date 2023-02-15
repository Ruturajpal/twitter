package com.jwt.token.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

	@RequestMapping("/welcome")
	public String welcome() {
		String text = "welcome to string";
		text += "this is main page";
		return text;
	}
}
