<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
	<h3>Book List</h3>
<hr>
	<ul>
		<c:forEach var="book" items="${ requestScope.list }">
		<li><a href="view.do?book=${ book }">${ book }</a></li>
		</c:forEach>
	</ul>	


</body>
</html>