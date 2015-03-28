<!-- References: http://code.tutsplus.com/tutorials/creating-a-keyboard-with-css-and-jquery--net-5774 -->
<!-- Team 4: CSE6324 -->
<!-- Stored in the repository: https://github.com/jaggi-sg/touchJDE -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TouchJDE</title>
</head>
<script type="text/javascript" src="js/application.js"></script>
<link rel="stylesheet" type="text/css" href="css/indexStyle.css">
<body>
	<div id="header">
		touch<b>JDE</b>
		<hr>
	</div>
	<div id="content">
		<p>This is a web-based IDE for JAVA which helps the beginners to
			develop and understand simple classes</p>
		<!-- <button class="btn btn-1" style="width: 120px"
			onclick="alert('Open');">
			Open<br />created classes
		</button> -->
		<a class="btn btn-1" href="openclasses.jsp"> Open<br />created classes
		</a>
		<a class="btn" href="newclass.jsp"> Create<br />class
		</a>
		<div id="openModal" class="modalDialog">
			<h4>Hi</h4>
		</div>
	</div>
	<div id="footer">TouchJDE - 2015 Developed @ UTA</div>
</body>
</html>