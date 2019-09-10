<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a menu item</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>New Menu Item</h1>
<form:form action="addFood" method="post" modelAttribute="foodForm">
	<table>
		<tr>
			<td>Name</td>
			<td><form:input path="name"/></td>
		</tr>
		<tr>
			<td>Menu</td>
			<td>
			<form:select path="menu">
				<form:option value="NONE" label="Select" />
     			<form:options items="${menus}" />
			</form:select>
			</td>
		</tr>
		<tr>
			<td>Price</td>
			<td><form:input path="price" placeholder=""/></td>
		</tr>
		<tr>
			<td>Description</td>
			<td><form:input path="description"/></td>
		</tr>
		<tr>
			<td>Image</td>
			<td><form:input path="image"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="Submit"/></td>
		</tr>
		<tr>
			<td><input type="reset" value="Reset"/></td>
		</tr>
	</table>
</form:form>
<%@ include file="footer.jsp" %>
</body>
</html>