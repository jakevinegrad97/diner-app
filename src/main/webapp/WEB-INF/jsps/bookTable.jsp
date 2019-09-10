<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book a Table</title>
<style>
.error {
	color: #ff0000;
}
#comic {
	font-family: Comic Sans MS;
}
</style>
</head>
<body>
<%@ include file="header.jsp" %>
<h1 id="comic">Book a Table</h1>
<sec:authorize access="isAnonymous()">
<h3>In order to book a table, you must <a href="hello">login</a> or <a href="register">register</a> for an account.</h3>
</sec:authorize>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
<form:form action="bookATable" method="post" modelAttribute="bookingForm">
	<table>
		<tr>
			<td id="comic">Date</td>
			<td><form:input id="comic" type="date" path="date"/></td>
			<td><form:errors path="date"/></td>
			<td id="comic">${dateError}</td>
		</tr>
		<tr>
			<td id="comic">Time</td>
			<td><form:input id="comic" type="time" path="time"/></td>
			<td><form:errors path="time"/></td>
			<td id="comic">${timeError}</td>
		</tr>
		<tr>
			<td id="comic">Number of People</td>
			<td><form:input id="comic" path="numberOfPeople"/></td>
			<td><form:errors path="numberOfPeople" cssClass="error"/></td>
			<td id="comic">${numberError}</td>
		</tr>	
		<tr>
			<td id="comic">Special Requirements (Optional)</td>
			<td><form:input id="comic" path="specialRequirements"/></td>
		</tr>
		<tr>
			<td><input id="comic" type="submit" value="Submit"/></td>
		</tr>
		<tr>
			<td><input id="comic" type="reset" value="Reset"/></td>
		</tr>
	</table>
</form:form>
</sec:authorize>
<%@ include file="footer.jsp" %>
</body>
</html>