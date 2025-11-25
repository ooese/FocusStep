<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- オリジナルCSS -->
<link rel="stylesheet" type="text/css"
      href="<c:url value='/css/style.css' />">
<title>HOME</title>
</head>
<body>
	<header class="page-header">
		<h1>Focus Step</h1>
		<nav>
			<ul class="page-nav">
				<li><a href="">今月の予定</a></li>
				<li><a href="">今までの振り返り</a></li>
				<li><a href="Logout">ログアウト</a></li>
			</ul>
		</nav>
	</header>
	<div class="wrapper center">
		<div class="flex">
			<div class="item">
				<img class="img-inf" src="images/information.png">
			</div>
			<div class="item-f">
				<div class="speechBubble">
					<p>
						<c:out value="${fullName}" />
						さん、おはようございます🌞
					</p>
				</div>
			</div>
		</div>
		<div class="grid-main">
			<section class="item-left">
				<ul class="menu">
					<li><a href="${pageContext.request.contextPath}/PostTask">タスク追加</a></li>
					<li><a href="${pageContext.request.contextPath}/EditTask">タスク編集</a></li>
					<li><a href="Navigation?action=review">今日の振り返り</a></li>
					<li><a href="Navigation?action=prepare">明日の準備</a></li>
				</ul>
			</section>
			<section class="item-center">
				<div class="task-top outline">
					<h2>今日のタスク</h2>
					<c:if test="${not empty todayTasks}">
						<ul>
							<c:forEach var="t" items="${todayTasks}">
								<li>${t.title}</li>
							</c:forEach>
						</ul>
					</c:if>
					<c:if test="${empty todayTasks}">
						<p>表示するタスクがありません。</p>
					</c:if>
				</div>
				<div class="task-bottom outline">
					<h2>今やること</h2>
					<c:if test="${not empty nextTaskToDo}">
				        <p>${nextTask.title}</p>
				        <p>優先度: ${nextTask.priority}</p>
				        <p>ステータス: ${nextTask.status}</p>
				    </c:if>
				
				    <c:if test="${empty nextTaskToDo}">
				        <p>表示するタスクがありません。</p>
				    </c:if>
				</div>
			</section>
			<section class="item-right outline">
				<h2>タイマー</h2>
			</section>
		</div>
	</div>
</body>
</html>