<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<% String err="";%>
<body>
	<form action="/Struts/login.action" >
		user:<input type="text" name="username"/><br/>
		code:<input type="password" name="password"/><br/>
		<input type="submit" value="submit"/><br/>
	</form>
	<% err=(String)request.getAttribute("loginErr"); %>
	<% if (err != null) { %>
		<p><font color="red"><%=err %></font></p>
	<% } %>
</body>
</html>