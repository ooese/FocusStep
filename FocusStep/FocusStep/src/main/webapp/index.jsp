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
		<div class="login wrapper center">
			<h1>Focus Step</h1>
			<section class="item-flex outline">
				<form action="LoginServlet" method="post" class="form">
					ログインID：<input type="loginId" name="loginId"><br> 
					パスワード：<input type="password" name="pass"><br> 
					<div class="button">
						<input type="submit" value="ログイン">
					</div>
				</form>
			</section>
		</div>
	</body>
</html>