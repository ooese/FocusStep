<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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

</body>
</html>