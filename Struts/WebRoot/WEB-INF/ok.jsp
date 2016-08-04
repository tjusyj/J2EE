<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Successful!</title>
</head>
<body>

<h3>Struts.</h3>
 <%=request.getAttribute("username") %>
<p>Your Login information: <s:property value="username" /> 
	<s:property value="password"/> </p>
 
<p><a href="<s:url action='index' />" >Return to home page</a>.</p>
</body>
</html>