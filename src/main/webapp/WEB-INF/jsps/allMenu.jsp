<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${menu}</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>${menu}</h1>
<hr style="border: 0;clear:both;display:block;width: 100%;background-color:#FFFF00;height: 1px;"/>
<c:forEach items="${items}" var="item">
<hr />
<h3>${item.name}</h3>
<p>${item.description}</p>
<p>£<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.price}"/></p>
<img src="${item.image}" width="200" height="200"/>
<sec:authorize access="hasRole('ROLE_ADMIN')">
<div style="margin-top: 5px;">
<a href="delete/${item.itemId}">Delete</a>
<a href="update/${item.itemId}">Edit</a>
</div>
</sec:authorize>
<hr style="border: 0;clear:both;display:block;width: 100%;background-color:#FFFF00;height: 1px;"/>
</c:forEach>
<div style="margin-top: 100px;background-color: SandyBrown;">
<%@ include file="footer.jsp" %>
</div>
</body>
</html>