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
タスク名：<input type="title" name=""><br>
詳細：<input type="description" name=""><br>
タスクの状態：
<input type="radio" name="status" value="未着手"> 未着手
<input type="radio" name="status" value="進行中"> 進行中
<input type="radio" name="status" value="完了"> 完了<br>
優先順位：<input type="priority" name=""><br>
実行日：<input type="schedule" name=""><br>
開始時間（分）：<input type="startTime" name=""><br>
目標時間（分）：<input type="target" name=""><br>
リマインダー：<input type="reminderTime" name=""><br>
<input type="submit" value="追加">
</form>


</body>
</html>