<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#disp{
		width: 200px;
		height: 400px;
		background: blue;
		
	}
</style>
</head>
<body>
<hr>
	<h3>${ book }(이)란?</h3>
<hr>
	<div id="disp">${ description }</div>
	
<a href="list.do">목록보기</a>	
</body>
</html>