<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Done</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Your booking has been successfully made!</h1>
<h3>When?</h3>
<p>${booking.date.dayOfMonth}/${booking.date.monthValue}/${booking.date.year} at ${booking.time}</p>
<h3>How many people?</h3>
<p>${booking.numberOfPeople}</p>
<h3>Special Requirements?</h3>
<p>${booking.specialRequirements}</p>
<script>
        var timer = setTimeout(function() {
            window.location='/allBookings'
        }, 2000);
    </script>
<%@ include file="footer.jsp" %>
</body>
</html>