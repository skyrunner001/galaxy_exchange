package com.galaxy.exchange.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.galaxy.exchange.service.TestService;

@Controller
public class TestController {

	@Autowired
	private TestService testService;
	
	@RequestMapping("/test")
	public void test1(){
		testService.test1();
	}
	
	@RequestMapping("/test2")
	public void test2(){
		testService.test2();
	}
}
