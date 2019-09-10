<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${titleSending}</title>
<style><%@include file="/WEB-INF/css/styles.css"%></style>
</head>
<body>
<%@ include file="header.jsp" %>
<script>
	var timer = setTimeout(function() {
	window.location='/email'
	}, 3000);
</script>
<h1>${titleSending}</h1>
<img style="height:300px;" src="${sending}" class="center"/>
<%@ include file="footer.jsp" %>
</body>
</html>