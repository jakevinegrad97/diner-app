<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bookings</title>
</head>
<body>
<%@ include file="header.jsp" %>
<c:forEach items="${bookings}" var="booking">
<h1>${booking.date.dayOfMonth}/${booking.date.monthValue}/${booking.date.year} at ${booking.time}</h1>
<h3>Table for ${booking.numberOfPeople}</h3>
<p>Special Requirements: <br /> ${booking.specialRequirements}</p>
<a href="delete/${booking.bookingId}">Cancel</a>
<a href="update/${booking.bookingId}">Update</a>
<hr />
</c:forEach>
<%@ include file="footer.jsp" %>
</body>
</html>