<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body onload='document.f.j_username focus();'>
	<c:if test="${not empty error}">
		<div class="errorblock">
			Login failed!!! <br /> Caused by:
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>

	<form action="j_spring_security_check" name="f" method="post">
		<table>
			<tr>
				<td>User</td>
				<td><input type="text" name="j_username" placeholder="username"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="j_password"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="Submit"
					value="Submit"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="reset" name="Reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>