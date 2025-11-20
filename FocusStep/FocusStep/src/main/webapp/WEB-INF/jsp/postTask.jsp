<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Focus Step ― タスク追加画面</title>
</head>
<body>
<h1>タスク追加</h1>

<a href="postTask">タスク追加</a>
<a href="">タスク編集</a>
<a href="main">タスク編集</a>

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
<h2>タスク一覧</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>タイトル</th>
        <th>詳細</th>
        <th>状態</th>
        <th>優先順位</th>
        <th>日付</th>
        <th>開始時刻</th>
        <th>目標時間</th>
        <th>リマインド</th>
    </tr>

    <c:forEach var="task" items="${taskList}">
        <tr>
            <td>${task.taskId}</td>
            <td>${task.title}</td>
            <td>${task.description}</td>
            <td>${task.status}</td>
            <td>${task.priority}</td>
            <td>${task.schedule}</td>
            <td>${task.startTime}</td>
            <td>${task.target}</td>
            <td>${task.reminderTime}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>