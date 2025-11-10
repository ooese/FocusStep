<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="registerUser" method="post">
  <label>姓：<input type="text" name="nameSei" required></label><br>
  <label>名：<input type="text" name="nameMei" required></label><br>
  <label>姓(かな)：<input type="text" name="nameSeiKana" required></label><br>
  <label>名(かな)：<input type="text" name="nameMeiKana" required></label><br>
  <label>生年月日(YYYY-MM-DD)：<input type="date" name="birth" required></label><br>
  <label>電話番号：<input type="text" name="phone" required></label><br>
  <label>メールアドレス：<input type="email" name="mail" required></label><br>
  <button type="submit">登録</button>
</form>
</body>
</html>