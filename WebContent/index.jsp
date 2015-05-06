<!-- References: http://code.tutsplus.com/tutorials/creating-a-keyboard-with-css-and-jquery--net-5774 -->
<!-- Team 4: CSE6324 -->
<!-- Stored in the repository: https://github.com/jaggi-sg/touchJDE -->
<%@page import="cse6324.uta.edu.properties.ReadProperties"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String user = (String) session.getAttribute("username");
	if(user == null || user.equals(0)){
		request.getRequestDispatcher("login.jsp").forward(request, response);
}
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TouchJDE</title>
</head>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/keyboard.js"></script>
<script type="text/javascript" src="js/typefeatures.js"></script>
<link rel="stylesheet" type="text/css" href="css/keypad.css">
<link rel="stylesheet" type="text/css" href="css/indexStyle.css">
<link rel="stylesheet" type="text/css" href="css/login_reg_style.css">
<body>
	<%
		String server = ReadProperties.getInstance().getValue(
				"DATABASE_SERVER");
		String username = ReadProperties.getInstance().getValue(
				"DATABASE_USERNAME");
		String password = ReadProperties.getInstance().getValue(
				"DATABASE_PASSWORD");
	%>
	<div id="header">
		touch<b>JDE</b>
		<hr>
	</div>
	<div id="content">
		<br />
		<p>This is a web-based IDE for JAVA which helps the beginners to
			develop and understand simple classes</p>
		<!-- <button class="btn btn-1" style="width: 120px"
			onclick="alert('Open');">
			Open<br />created classes
		</button> -->
		<a class="btn btn-1" href="openclassesfromproject.jsp"> Open<br />created
			classes
		</a> <a class="btn" href="#openModal"> Create<br />class
		</a>
		<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
			url="<%=server%>" user="<%=username%>" password="<%=password%>" />
		<sql:query dataSource="${snapshot}" var="resultProj">
			SELECT * from jde_projects;
		</sql:query>
		<sql:query dataSource="${snapshot}" var="resultPkg">
			SELECT * from jde_packages;
		</sql:query>
		<div id="openModal" class="modalDialog">
			<div>
				<!-- Form to enter class details on creation -->
				<a href="#close" title="Close" class="close">X</a>
				<form action="create.action" method="post" id="formData">
					<br />
					<button class="btn-2" id="newProj">Create new project</button>
					<button class="btn-2" id="oldProj">Add to existing project</button>
					<h3>
						<span id="projNameLabel"><label>Enter Project Name:</label><br /></span>
					</h3>
					<input type="text" size="30" id="projName" name="projName" /> <br />
					<select id="projSelect" name="projSelect">
						<c:forEach var="row" items="${resultProj.rows}">
							<option value="${row.proj_name}">${row.proj_name}</option>
						</c:forEach>
					</select><br />
					<button class="btn-2" id="newPkg">Create new Package</button>
					<button class="btn-2" id="oldPkg">Add to existing Package</button>
					<h3>
						<span id="pkgNameLabel"><label>Enter Package Name:</label><br />
							<h5>(Example: com.package.example)</h5></span>
					</h3>
					<input type="text" size="30" id="pkgName" name="pkgName" /><br />
					<select id="pkgSelect" name="pkgSelect">
						<c:forEach var="row" items="${resultPkg.rows}">
							<option value="${row.pkg_name}">${row.pkg_name}</option>
						</c:forEach>
					</select><br />
					<h3>
						<label>Enter Class Name:</label>
					</h3>
					<h5>(Example: TestClassExample)</h5>
					<input type="text" id="clsName" size="50" name="clsName" required
						pattern="^[A-Z]([a-z]*[A-Z]?[A-Za-z]*)"
						title="Enter correct format" /><br /> <br /> <input
						type="submit" name="createCls" value="Create" class="btn btn-2" />
				</form>
			</div>
		</div>
	</div>
	<div id="footer">TouchJDE - 2015 Developed @ UTA</div>
	<a class="back" href="/touchJDE/logout.action">Logout <%=user%></a>
</body>
<script type="text/javascript">
	$(function() {
		$("#newProj").click(function() {
			$("#projNameLabel").show();
			$("#projName").show();
			$("#projSelect").hide();
		});
	});
	$(function() {
		$("#oldProj").click(function() {
			$("#projSelect").show();
			$("#projNameLabel").hide();
			$("#projName").hide();
		});
	});
	$(function() {
		$("#newPkg").click(function() {
			$("#pkgNameLabel").show();
			$("#pkgName").show();
			$("#pkgSelect").hide();
		});
	});
	$(function() {
		$("#oldPkg").click(function() {
			$("#pkgSelect").show();
			$("#pkgNameLabel").hide();
			$("#pkgName").hide();
		});
	});
	$("form :input").change(function() {
		console.log($("form").serialize());
	});
</script>

</html>