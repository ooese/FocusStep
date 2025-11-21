<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- オリジナルCSS -->
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />">
<title>HOME</title>
</head>
<body>
	<header class="page-header">
		<nav>
			<ul class="main-nav">
				<li><a href="Logout">ログアウト</a></li>
				<li><a href="">今月の予定</a></li>
				<li><a href="">今までの振り返り</a></li>
			</ul>
		</nav>
	</header>
<div wrapper>
	<img src="images/information.png">
	<p>
		<c:out value="${fullName}" />
		さん、おはようございます🌞
	</p>
	<p>ひとこと</p>

	<a href="${pageContext.request.contextPath}/PostTask">タスク追加</a>
	<a href="Navigation?action=edit">タスク編集</a>
	<a href="Navigation?action=review">今日の振り返り</a>
	<a href="Navigation?action=prepare">明日の準備</a>

	<h3>今日のタスク</h3>
	<c:if test="${not empty todayTasks}">
		<ul>
			<c:forEach var="t" items="${todayTasks}">
				<li>${t.title}</li>
			</c:forEach>
		</ul>
	</c:if>

	<c:if test="${empty todayTasks}">
		<p>表示するタスクはありません。</p>
	</c:if>


	<h3>今やること</h3>

	<h3>タイマー</h3>

</div>
</body>
</html>