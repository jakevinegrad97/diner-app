<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Header</title>
<style><%@include file="/WEB-INF/css/styles.css"%></style>
</head>
<body>
<div class="topnav">
<sec:authorize access="isAnonymous()">
<a id="floatRight" href="/hello">Login</a>
<a id="floatRight" href="/register">Register</a>
</sec:authorize>
<a href="/all">Menu</a>
<a href="/home">Home</a>
<a href="/bookATable">Book a Table</a>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="addFood">Add Menu Item</a>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
	<a href="/allBookings">View Bookings</a>
	<a id="floatRight" href="/logout">Logout</a>
	<a id="floatRight" href="/myAccount">My Account</a>
</sec:authorize>
</div>
</body>
</html>