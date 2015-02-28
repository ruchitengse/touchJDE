<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TouchJDE</title>
</head>
<style>
html {
	height: 100%;
}

body, area{
	font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
	height: 100%;
}

.btn {
	font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
	display: block;
	color: rgb(255, 255, 255);
	text-decoration: none;
	text-align: center;
	padding: 10px;
	margin: 15px 0px 0px 15px;
	font-size: 18px;
	text-transform: capitalize;
	background: #ED5E2F;
	color: #FFF;
	border: 0px none;
	outline: 0px none;
	width: 100px;
}

.btn:hover {
	background: #74A599;
	cursor: pointer;
}

.btn-1 {
	background: #308919;
}

#header {
	font-size: large;
	color: #5C9CF7;
	position: absolute;
	right: 0;
	top: 0;
	margin: auto;
	height: 30px;
}

#body {
	height: 30px;
}

#content {
	height: auto;
}
</style>
<script>
	function btn(x) {
		/* x.style.background = "yellow"; */
		for (var i = 1; i < 10; i++) {
			var btnsp = '<button onclick=return '+i+' value="'+i+'" id="btngen'+i+'">' + i
					+ '</button>';
			document.getElementById("btnsplace").innerHTML += btnsp;
			document.getElementById("btnsplace").innerHTML += i;
		}
		/* document.getElementById("btnsplace").innerHTML = btnsp; */
	}
	function btnimp(x) {
		/* x.style.background = "yellow"; */
		/*  alert("imp button"); */
		var btns = '<button id="btngen">new button</button>';
		document.getElementById("btnsplace").innerHTML = btns;
		document.getElementById("btnsplace").innerHTML += x.value;
	}
	function printtext(x) {
		/* x.style.background = "yellow"; */
		/*  alert("imp button"); */
		alert(x.value);
		/* document.getElementById("btnsplace").innerHTML = btns; */
	}
</script>
<body>
	<div id="header">
		<p>
			touch<b>JDE</b>
		</p>
	</div>
	<div id="body">
		<p>This is a web-based IDE for JAVA which helps the beginners to
			develop and understand simple classes</p>
	</div>
	<div id="content">
		<button class="btn btn-1" style="width: 120px"
			onclick="alert('Open');">
			Open<br />created classes
		</button>
		<button class="btn" onclick="printtext(this)" value="Create">
			Create<br />class
		</button>
	</div>
	<textarea readonly id="area" cols="50" rows="1" style="border:none">public static void main(String args[]) {</textarea>
	<br />
	<textarea id="area" cols="50" rows="10" onfocus="btn(this)" style="border:none"></textarea><br/>
	<textarea readonly id="area" cols="50" rows="1" style="border:none">}</textarea>
	<br />

	<button id="btnimp" onclick="btnimp(this)" value="'<br/>'">import</button>
	<div id="btnsplace"></div>
</body>
</html>