<%@page import="cse6324.uta.edu.persistent.DbConnection"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Open project</title>
<%
	String username = (String) session.getAttribute("username");
	if(username == null || username.equals(0)){
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
%>
</head>
<body>
<form method="POST" name="openclass" id="openclass">
<select name="projects" id="projects">
<%
	String projectId = request.getParameter("projects");
	String packageId = request.getParameter("packages");
	Map<Integer, String> userProjectsMap = DbConnection.getData(username, "getprojects");
	for(Integer Id: userProjectsMap.keySet()){
		if(projectId != null && Id.equals(Integer.parseInt(projectId))){
	%>
	<option value=<%=Id%> selected><%=userProjectsMap.get(Id)%></option>
	<% } else {
		%>
		<option value=<%=Id%>><%=userProjectsMap.get(Id)%></option>
		<%
	}
	}
%>
</select>
<input type="submit" name="getpackages" id="getpackages" value="Get Packages"/>
<%
if(request.getParameter("getpackages") != null){
	Map<Integer, String> projectPackageMap = DbConnection.getData(projectId, "getpackages");
	%>
	<select name="packages" id="packages">
	<% for(Integer Id: projectPackageMap.keySet()){	
	if(projectId != null && Id.equals(Integer.parseInt(projectId))){
	%>
	<option value=<%=Id%> selected><%=projectPackageMap.get(Id)%></option>
	<% } else {
		%>
		<option value=<%=Id%>><%=projectPackageMap.get(Id)%></option>
		<%
	} 
	}
	%>
	</select>
	<input type="submit" name="getclasses" id="getclasses" value="Get Classes"/>
	<%
}
%>
<% if(request.getParameter("getclasses") != null) { 
	Map<String, String> classesMap = DbConnection.getData(packageId, "getclasses");
%>
<select name="classname" id="classes">
<% for(String classId: classesMap.keySet()){ %>
<option value="<%=classId%>"><%=classesMap.get(classId)%></option>
<% } %>
</select>
<input type="button" value="Open Class" onclick="openclassinnewpage()"/>
<% } %>
</form>
</body>
<script type="text/javascript">
function openclassinnewpage(){
	document.forms[0].method="POST";
	var classId = document.getElementById('classes').value;
	document.forms[0].action="save.action?classId="+classId;
	document.forms[0].submit();
}
</script>
</html>