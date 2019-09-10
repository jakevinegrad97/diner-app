<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><sec:authentication property="name"/> is cool</title>
</head>
<body>
<h1>HIIIIIIIIIIIIIIII</h1>
<a href="/">HOME AGAINNNNNNNN</a>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="admin">Admin</a>
</sec:authorize>

</body>
</html>