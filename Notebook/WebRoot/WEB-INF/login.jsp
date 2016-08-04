<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>User Login</h1>
	<form action="/Notebook/login.action?flag=login" method="post">
		<table border="1" cellspacing="0">
			<tr><td>username:</td><td><input type="text"     name="username" style="width:100px"></td></tr>
			<tr><td>password:</td><td><input type="password" name="password" style="width:100px"></td></tr>
			<tr><td><input type="submit" value="submit"></td><td><input type="reset" value="reset"></td></tr>
		</table>
	</form>
</body>
</html>