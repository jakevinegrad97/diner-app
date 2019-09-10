<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<style><%@include file="/WEB-INF/css/styles.css"%></style>
<script><%@include file="/WEB-INF/js/main.js"%></script>
</head>
<body>
<script>
	var timer = setTimeout(function() {
	window.location='/'
	}, 5000);
</script>
<%@ include file="header.jsp" %>
<div class="header" id="header">
Fine Dining
</div>
<a href="${babel}" onClick='alert("You are being taken to ${babel}")'>Babel</a>
	<c:if test="${seeMenu != 0}">
		<c:forEach items="${menus}" var="menu">
			<div class="vertical-menu">
			<a href="find/${menu}">${menu}</a>
			</div>
		</c:forEach>
	</c:if>
<h3  style="text-align: center;"><sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
	Hello, <sec:authentication property="name"/>.
</sec:authorize></h3>
<img style="margin-bottom:5px;" class="center" src="${homeImage}"/>
<p style="text-align:center; font-family: sans-serif; font-size: 1.5em;">${myMessage}</p>
<%@ include file="footer.jsp" %>
</body>
</html>