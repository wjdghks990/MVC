package controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import annotation.RequestMapping;
import annotation.ResponseBody;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
//@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Object ob = null;
	List<Method> method_list = new ArrayList<Method>();
	List<Object> object_list = new ArrayList<Object>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		String action = config.getInitParameter("action").trim();
		String [] action_array = action.split(",");
		
		for(String action_name : action_array) {
			try {
				action_name = action_name.replaceAll("\r", "").replaceAll("\n", "").trim();
				
				//System.out.println(action_name);
				
				// Java Reflection: 클래스명이 변수일 때 객체를 생성하는 방법으로 reflection
				// action_name = "action.controller.TestAction"
				Class c = Class.forName(action_name);
				Object ob = c.newInstance(); // new action.controller.TestAction()
				
				//object_list.add(ob);
								
				// 해당클래스내의 메서드 목록정보를 얻어온다
				Method [] method_array = c.getDeclaredMethods(); // list / view
				
				for(Method method : method_array) {
					object_list.add(ob);
					method_list.add(method);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
		
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// list.do
		
        //URI Pattern		
		String uri = request.getRequestURI();
		// uri = "/2024_0715_MVCFramework/list.do";
		String forward_page="";
		boolean bResponseBody=false;
		String contentType="";
		
		for(Method method : method_list) {	
		
			// annotation 메서드가 존재하냐?
			if(method.isAnnotationPresent(RequestMapping.class)) {
				RequestMapping annotation = method.getAnnotation(RequestMapping.class);
				// 메서드가 uri에 포함되어 있냐?
				if(uri.contains(((RequestMapping)annotation).value())){
				
					try {
						
						int index = method_list.indexOf(method);
						Object ob = object_list.get(index);
						forward_page = (String) method.invoke(ob, request,response);
						
						/*						
						for(Object ob :object_list) {
							
							try {
								 //
							     forward_page = (String) method.invoke(ob, request,response);
							     break;
							}catch(Exception e) {
								
								//e.printStackTrace();
							}
							
						}
						*/
												
						if(method.isAnnotationPresent(ResponseBody.class)) {
							
							contentType = ((RequestMapping)annotation).produces();
							
							bResponseBody = true;
						}
						
						break;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} 
						
		} // end - for
		
		//forward or redirect
		if(forward_page.isEmpty())return;
		
		if(bResponseBody) {
			
			response.setContentType(contentType);
			response.getWriter().print(forward_page);
			
			return;
		}
		
		if(forward_page.contains("redirect:")) {
			
			String redirect_page = forward_page.replaceAll("redirect:", "");
			response.sendRedirect(redirect_page);
			
		}else {
			//forward
			RequestDispatcher disp = request.getRequestDispatcher(forward_page);
			disp.forward(request, response);
		}
				
	} // end - service()
		
}