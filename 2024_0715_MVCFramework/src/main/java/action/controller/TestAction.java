package action.controller;

import java.util.ArrayList;
import java.util.List;

import annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TestAction {
	
	public TestAction() {
		// TODO Auto-generated constructor stub
		System.out.println("--TestAction()--");
	}
	
	@RequestMapping("/list.do") // annotation 메서드(자체 제작)
	public String list(HttpServletRequest request, HttpServletResponse response) {
			
		List<String> list = new ArrayList<String>();
		list.add("Java");
		list.add("Oracle");
		list.add("Html");
		list.add("CSS");
		list.add("Javascript");
		list.add("Spring");
		
		request.setAttribute("list", list);		
		return "list.jsp";
	}
	
	// /view.do?book=Java
	@RequestMapping("/view.do")
	public String view(HttpServletRequest request, HttpServletResponse response) {
			
		// /view.do?book=Java
		// /view.do?book=Oracle
		
		String book = request.getParameter("book");
		String description = "뭐지?";
		
		switch(book.toUpperCase())
		{
			case "JAVA"		 : description="양천모쌤이 1등인 언어";	break;
			case "ORACLE"	 : description="데이터베이스계의 goat"; 	break;
			case "HTML"		 : description="하이퍼텍스트마크업랭귀지"; 	break;
			case "CSS"		 : description="디자인하는 언어중 하나"; 	break;
			case "JAVASCRIPT": description="동적언어로써 함수사용"; 	break;
			case "SPRING"	 : description="봄이랑 용수철아님 웹언어";	break;
		}
		
		// request binding
		request.setAttribute("book", book);
		request.setAttribute("description", description);
		
		return "view.jsp";
	}
}
