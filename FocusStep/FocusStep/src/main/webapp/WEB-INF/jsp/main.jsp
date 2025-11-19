<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>
</head>
<body>
<a href="Logout">ログアウト</a>
<a href="">今月の予定</a>
<a href="">今までの振り返り</a>


<p><c:out value="${fullName}" />さん、おはようございます🌞</p>
<p>ひとこと</p>

<a href="postTask.jsp">タスク追加</a>
<a href="">タスク編集</a>
<a href="">今日の振り返り</a>
<a href="">明日の準備</a>

<p>
<% if(errorMsg != null){ %>
<p><%= errorMsg%></p>
<% } %>
<% for (Task task : taskList){ %>
<p><%= task.getPriority()%><%= task.getTitle()%></p>
<% } %>

今やること

タイマー
</p>

</body>
</html>