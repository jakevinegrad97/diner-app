<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compose your email</title>
</head>
<body>
<form:form path="sendMail" method="post" modelAttribute="emailForm">
<form:input  style="height:200px; font-size:14pt;"  path="description"/>
<input type="submit" value="Send">
</form:form>
</body>
</html>