<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<font size="5"><a href="/Notebook/">发布信息</a></font>
<font size="5"><a href="/Notebook/">退出系统</a></font>
welcom user ${user.username}
<br/>
<h3>留言信息:</h3>
	<table border="1" cellspacing="0">
		<tr><td>Sender</td><td>Time</td><td>Receiver</td><td>Content</td></tr>
		<c:forEach items="${messages }" var="m">
			<tr><td>${m.sender.username }</td><td>${m.time }</td><td>${m.receiver.username }</td><td>${m.content }</td></tr>
		</c:forEach>
	</table>

</body>
</html>