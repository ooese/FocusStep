<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- オリジナルCSS -->
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />">
<title>Focus Step ―ログイン画面</title>
</head>
	<body>
		<div class="wrapper center">
			<h1>Focus Step</h1>
			<form action="LoginServlet" method="post">
				ログインID：<input type="loginId" name="loginId"><br> 
				パスワード：<input type="password" name="pass"><br> 
				<input type="submit" value="ログイン">
			</form>
		</div>
	</body>
</html>