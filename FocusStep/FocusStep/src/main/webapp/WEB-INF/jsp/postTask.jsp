<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- オリジナルCSS -->
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />">
<title>Focus Step ― タスク追加画面</title>
</head>
<body>
	<header class="page-header">
		<h2>Focus Step</h2>
		<nav>
			<ul class="page-nav"><!--下記リンクは、ボタンを押すと真ん中の画面表示が切り替わるようにしたい-->
				<li><a href="">今月の予定</a></li>
				<li><a href="">今までの振り返り</a></li>
				<li><a href="Logout">ログアウト</a></li>
			</ul>
		</nav>
	</header>
	<div class="post-task wrapper center">
		<div class="top">
			<div class="item">
				<img class="img-inf" src="images/information.png">
			</div>
			<div class="item-speech">
				<div class="speechBubble">
					<p>
						<h1>タスク追加</h1>
					</p>
				</div>
			</div>
		</div>
		<div class="main">
			<section class="item-menu">
				<ul class="menu">
					<li><a href="PostTask">タスク追加</a></li>
					<li><a href="EditTask">タスク編集</a></li>
					<li><a href="Main">戻る</a></li>
				</ul>
			</section>
			<section class="item-flex outline">
				<form action="PostTask" method="post" class="form">
					タスク名：<input type="text" name="title"><br>
					詳細：<input type="text" name="description"><br>
					タスクの状態：
					<input type="radio" name="status" value="未着手"> 未着手
					<input type="radio" name="status" value="進行中"> 進行中
					<input type="radio" name="status" value="完了"> 完了<br>
					優先順位：<input type="number" name="priority"><br>
					実行日：<input type="date" name="schedule"><br>
					開始時刻：<input type="time" name="startTime"><br>
					目標時間（分）：<input type="number" name="target"><br>
					リマインダー：<input type="time" name="reminderTime"><br>
					<div class="button">
						<input type="submit" value="追加">
					</div>
				</form>
			</section>
			<section class="item-200">
				<div class="item-top outline">
					<h3>今日のタスク</h3>
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
				<div class="item-bottom outline">
					<h3>明日のタスク</h3>
					<c:if test="${not empty tomorrowTasks}">
					    <c:forEach var="t" items="${tomorrowTasks}">
					        <p>${t.title}</p>
					    </c:forEach>
					</c:if>
					<c:if test="${empty tomorrowTasks}">
						<p>表示するタスクがありません。</p>
					</c:if>
				</div> 
			</section>
			
			
		</div>
	</div>
</body>
</html>