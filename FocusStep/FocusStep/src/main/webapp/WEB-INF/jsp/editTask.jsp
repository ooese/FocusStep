<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- オリジナルCSS -->
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css' />">
<title>Focus Step ― タスク編集画面</title>
</head>
<body>
	<header class="page-header">
		<h1>Focus Step</h1>
		<nav>
			<ul class="page-nav"><!--下記リンクは、ボタンを押すと真ん中の画面表示が切り替わるようにしたい-->
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
						<h1>タスク編集</h1>
					</p>
				</div>
			</div>
		</div>
		<div class="grid-main">
			<section class="item-left">
				<ul class="menu">
					<li><a href="${pageContext.request.contextPath}/PostTask">タスク追加</a></li>
					<li><a href="${pageContext.request.contextPath}/GetTask">タスク編集</a></li>
					<li><a href="${pageContext.request.contextPath}/Main">戻る</a></li>
				</ul>
			</section>
			<section class="item-center outline">
				<h3>今日のタスク</h3>
				<div class="task-top">
					<c:if test="${not empty todayTasks}">
					    <table>
					        <thead>
					            <tr>
					                <th>タイトル</th>
					                <th>詳細</th>
					                <th>状態</th>
					                <th>優先順位</th>
					                <th>日付</th>
					                <th>開始時刻</th>
					                <th>目標時間</th>
					                <th>リマインド</th>
					            </tr>
					        </thead>
					        <tbody>
					            <c:forEach var="t" items="${todayTasks}">
					                <tr>
					                    <td>${t.title}</td>
					                    <td>${t.description}</td>
					                    <td>${t.status}</td>
					                    <td>${t.priority}</td>
					                    <td>${t.scheduleStr}</td>
					                    <td>${t.startTimeStr}</td>
					                    <td>${t.target}分</td>
					                    <td>${t.reminderTimeStr}</td>
					                </tr>
					            </c:forEach>
					        </tbody>
					    </table>
					</c:if>
					
					<c:if test="${empty todayTasks}">
					    <p>表示するタスクはありません。</p>
					</c:if>
				</div>
			</section>
			<section class="item-right outline">
			</section>
		</div>
	</div>
</body>
</html>