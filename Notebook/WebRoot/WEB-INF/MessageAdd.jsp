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
<font size="5"><a href="/Notebook/gotoMessageBoard.action">查看信息</a></font>
<font size="5"><a href="/Notebook/login.action?flag=logout">退出系统</a></font>
welcom user ${user.username}
<br/>
<h3>信息发送:</h3>
	<form action="/Notebook/message.action?flag=addMessage" method="post">
		<table border="1" cellspacing="0" cellpadding="0">
			<tr><td>接收人:</td>
				<td><select name="add_receiverId">
					<c:forEach items="${users }" var="u">
						<option value="${u.id }">${u.username }</option>
					</c:forEach>
				</select></td></tr>
			<tr><td>信息:</td>
				<td><input type="text" name="add_content"/></td></tr>
			<tr><td><input type="submit" value="submit"/><input type="reset" value="reset"/></td></tr>
		</table>
	</form>
</body>
</html>