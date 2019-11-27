<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>
</head>
<body>
	<h1>HOME 입니다.</h1>
	<c:if test="${empty sessionScope.user}">
		로그인 안 됨</br>
	</c:if>
	<a href="/board/list">게시글 목록</a>
</body>
</html>