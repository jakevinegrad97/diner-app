<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update</title>
</head>
<body>
<form:form action="${item.itemId}" method="post" modelAttribute="updateFoodForm">
	<table>
		<tr>
			<td>Name</td>
			<td><form:input path="name" value="${item.name}"/></td>
		</tr>
	<!-- 	<tr>
			<td>Menu</td>
			<td>
			<form:select path="menu">
				<form:option value="NONE" label="${item.menu}" />
     			<form:options items="${menus}" />
			</form:select>
			</td>
		</tr> -->
		<tr>
			<td>Price</td>
			<td><form:input path="price" value="${item.price}"/></td>
		</tr>
		<tr>
			<td>Description</td>
			<td><form:input path="description" value="${item.description}"/></td>
		</tr>
		<tr>
			<td>Image</td>
			<td><form:input path="image" value="${item.image}"/></td>
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