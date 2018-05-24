package com.unisys.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unisys.springboot.model.Greet;
@Controller 
public class HelloController {

	@RequestMapping(value="/hello")
	public String sayHello(Model model) {
		System.out.println("Hello, world!");
		model.addAttribute("message", "Spring is cool!");
		return "hello-world";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/greet", 
			produces= {"application/json", "application/xml"})
	@ResponseBody
	public Greet greet() {
		return new Greet("Welcome to Spring MVC", "Vinod");
	}
	
}






