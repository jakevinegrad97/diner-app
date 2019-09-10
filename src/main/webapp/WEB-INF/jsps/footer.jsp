<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Footer</title>
<style><%@include file="/WEB-INF/css/styles.css"%></style>
<script>
function popUpPrivacy() {
  var popup = document.getElementById("privacy");
  popup.classList.toggle("show");
}

function popUpMap() {
	  var popup = document.getElementById("map");
	  popup.classList.toggle("show");
}
function popUpContact() {
	  var popup = document.getElementById("contact");
	  popup.classList.toggle("show");
}
</script>
</head>
<body>
<div class="footer">
	<a href="/contactPage" class="popup" onmouseover="popUpContact()">Contact Us
  		<span class="popuptext" id="contact">07123143312</span>
	</a>
	<a href="/findUs" class="popup" onmouseover="popUpMap()">Find Us
  		<span class="popuptext" id="map"><img style="width: 1000px;" src="https://d36pgh4m67wnlt.cloudfront.net/staticMapPhotoForProperty/226908.JPG"/></span>
	</a>
	<a id="floatRight" href="http://www.facebook.com"><img width="25" height="25" src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Facebook_logo_%28square%29.png/600px-Facebook_logo_%28square%29.png"/></a>
	<a class="popup" onmouseover="popUpPrivacy()">Privacy Policy
  		<span class="popuptext" id="privacy">We sell all of your information to third parties. Thank you for understanding.</span>
	</a>
</div>
</body>
</html>