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
	<nav>
		<ul class="main-nav">
			<li><a href="Logout">ログアウト</a></li>
		</ul>
	</nav>
</header>
<h1>タスク追加</h1>

<!--下記リンクは、ボタンを押すと真ん中の画面表示が切り替わるようにしたい-->
<a href="">タスク追加</a>
<a href="">タスク編集</a>
<a href="${pageContext.request.contextPath}/Main">戻る</a>

<form action="PostTask" method="post">
タスク名：<input type="text" name="title"><br>
詳細：<input type="text" name="description"><br>
タスクの状態：
<input type="radio" name="status" value="pending"> 未着手
<input type="radio" name="status" value="in_progress"> 進行中
<input type="radio" name="status" value="completed"> 完了<br>
優先順位：<input type="number" name="priority"><br>
実行日：<input type="date" name="schedule"><br>
開始時刻：<input type="time" name="startTime"><br>
目標時間（分）：<input type="number" name="target"><br>
リマインダー：<input type="time" name="reminderTime"><br>
<input type="submit" value="追加">
</form>

<h3>今日のタスク</h3>
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

</body>
</html>