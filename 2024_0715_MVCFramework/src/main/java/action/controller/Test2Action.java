package action.controller;

import annotation.RequestMapping;
import annotation.ResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Test2Action {
	
	public Test2Action() {
		// TODO Auto-generated constructor stub
		System.out.println("--Test2Action()--");
	}
	
	@RequestMapping("/hello.do")
	@ResponseBody
	public String hello(HttpServletRequest request, HttpServletResponse response) {
		
		return "Hello~~";
	}
}
