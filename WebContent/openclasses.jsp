<!-- ***Displays the list of classes owned by a user*** -->
<%@page import="cse6324.uta.edu.properties.ReadProperties"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/application.js"></script>
<script type="text/javascript" src="js/keyboard.js"></script>
<script type="text/javascript" src="js/typefeatures.js"></script>
<link rel="stylesheet" type="text/css" href="css/keypad.css">
<link rel="stylesheet" type="text/css" href="css/indexStyle.css">
<link rel="stylesheet" type="text/css" href="css/login_reg_style.css">
<title>Classes</title>
</head>
<%
	String server = ReadProperties.getInstance().getValue("DATABASE_SERVER");
	String username = ReadProperties.getInstance().getValue("DATABASE_USERNAME");
	String password = ReadProperties.getInstance().getValue("DATABASE_PASSWORD");
	String user = (String) session.getAttribute("username");
%>
<body>
	<p>Classes</p>
	<!-- <a name="openClass" href="javascript:void(0);">Sample class</a> -->

	<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
		url="<%=server%>" user="<%=username%>" password="<%=password%>" />
	<sql:query dataSource="${snapshot}" var="result">
SELECT * from classstore;
	</sql:query>
	<%-- <jsp:useBean id="map" class="java.util.HashMap" scope="request"/> --%>
<c:forEach var="row" items="${result.rows}">
<form method="post" action="save.action?classname=${row.classData}">
	<%-- <input type="submit" value="${row.className}"/><c:out value="${row.className}"/> --%>
  <a href="save.action?classname=${row.classData}" id="saveLink"><c:out value="${row.className}"/></a>
<%-- <c:set target="${map}" property="${row.login}" value="${row.login}"/>  --%>
</form>
</c:forEach>
<a class="back"
			href="/touchJDE/logout.action">Logout <%=user%></a>
</body>
</html>