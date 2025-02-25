package action;

import java.io.IOException;
import java.net.URLEncoder;

import dao.VisitDao;
import db.vo.VisitVo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VisitModifyAction
 */
//@WebServlet("/visit/modify.do")
public class X_VisitModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 0. 수신 인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		// 1.parameter(전달인자) 받기
		int    idx  	= Integer.parseInt(request.getParameter("idx"));

		String name 	= request.getParameter("name");
		String content	= request.getParameter("content").replaceAll("\n", "<br>");
		String pwd 		= request.getParameter("pwd");
		
		String page 	= request.getParameter("page");
		String search	= request.getParameter("search");
		String search_text = request.getParameter("search_text");
		
		// 2. ip정보 얻어오기
		String ip 		= request.getRemoteAddr();
		
		// 3. VisitVo 포장
		VisitVo vo = new VisitVo(idx, name, content, pwd, ip);
		
		// 4. DB insert
		int res = VisitDao.getInstance().update(vo);
		
		String redirect_page = String.format("list.do?page=%s&search=%s&search_text=%s", 
											page,search,URLEncoder.encode(search_text,"utf-8"));
		
		// 5. 목록보기 이동
		response.sendRedirect(redirect_page);

	}

}
