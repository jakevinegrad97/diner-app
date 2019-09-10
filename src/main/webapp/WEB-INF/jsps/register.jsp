<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<style>
.error {
	color: #ff0000;
}
</style>

</head>
<body>

	<%@ include file="header.jsp"%>
	<h1>Register for an Account</h1>
	<form:form action="register" method="post" modelAttribute="userForm">
	<table>
		<tr>
			<td>Username</td>
			<td><form:input path="username"/></td>
			<td><form:errors path="username" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><form:input path="password" type="password"/></td>
			<td><form:errors path="password" cssClass="error"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit"/></td>
		</tr>
	</table>

	</form:form>
<%@ include file="footer.jsp" %>
</body>
</html>