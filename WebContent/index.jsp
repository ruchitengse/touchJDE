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
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/application.js"></script>
<script type="text/javascript" src="js/keyboard.js"></script>
<script type="text/javascript" src="js/typefeatures.js"></script>
<link rel="stylesheet" type="text/css" href="css/keypad.css">
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
		<a class="btn btn-1" href="openclasses.jsp"> Open<br />created
			classes
		</a> <a class="btn" href="#openModal"> Create<br />class
		</a>
		<div id="openModal" class="modalDialog">
			<div>
			<!-- <p>Enter Class details to create:</p> -->
				<a href="#close" title="Close" class="close">X</a>
				<form action="create.action" method="post" id="formData">
				<br/>
				<h3><label>Enter Package Name:</label></h3>
				<h5>(Example: com.package.example)</h5>
				<input type="text" id="pkgName" size="50" name="pkgName"/><br/><br/>
				<h3><labeL>Enter Class Name:</labeL></h3>
				<h5>(Example: TestClassExample)</h5>
				<input type="text" id="clsName" size="50" name="clsName"/><br/><br/>
				<input type="submit" name="createCls" value="Create" class="btn btn-2"/>
				</form>
			</div>
		</div>
	</div>
	<div id="footer">TouchJDE - 2015 Developed @ UTA</div>
</body>
</html>