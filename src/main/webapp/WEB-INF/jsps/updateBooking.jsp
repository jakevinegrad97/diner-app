<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Your Booking</title>
</head>
<body>
<%@ include file="header.jsp" %>
<form:form action="${bookingUpdate.bookingId}" method="post" modelAttribute="updateBookingForm">
	<table>
		<tr>
			<td>Date</td>
			<td><form:input type="date" path="date" value="${bookingUpdate.date}"/></td>
			<td>${dateError}</td>
		</tr>
		<tr>
			<td>Time</td>
			<td><form:input type="time" path="time" value="${bookingUpdate.time}"/></td>
			<td>${timeError}</td>
		</tr>
		<tr>
			<td>Number of People</td>
			<td><form:input path="numberOfPeople" value="${bookingUpdate.numberOfPeople}"/></td>
			<td>${numberError}</td>
		</tr>	
		<tr>
			<td>Special Requirements (Optional)</td>
			<td><form:input path="specialRequirements" value="${bookingUpdate.specialRequirements}"/></td>
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