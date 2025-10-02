package com.hk.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value="/home.do",method=RequestMethod.GET)
	public String home() {
		
		return "home"; // WEBINF/views/home.jsp로 응답 : ViewResolver가 찾아줌
	}

}
