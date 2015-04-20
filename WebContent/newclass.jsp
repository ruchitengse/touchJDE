<!-- References: http://code.tutsplus.com/tutorials/creating-a-keyboard-with-css-and-jquery--net-5774 -->
<!-- Stored in the repository: https://github.com/jaggi-sg/touchJDE -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/application.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript" src="js/keyboard.js"></script>
<script type="text/javascript" src="js/typefeatures.js"></script>
<link rel="stylesheet" type="text/css" href="css/keypad.css">
<link rel="stylesheet" type="text/css" href="css/indexStyle.css">
<title>TouchJDE New Class</title>
</head>
<body>
	<div id="header">
		<p>
			<a href="index.jsp">touch<b>JDE</b></a>
		</p>
		<hr>
	</div>
	<div id="content">
		<div id="chrDiv">
			<ul id="keyboard">
				<li class="letter keyStyle">q</li>
				<li class="letter keyStyle">w</li>
				<li class="letter keyStyle">e</li>
				<li class="letter keyStyle">r</li>
				<li class="letter keyStyle">t</li>
				<li class="letter keyStyle">y</li>
				<li class="letter keyStyle">u</li>
				<li class="letter keyStyle">i</li>
				<li class="letter keyStyle">o</li>
				<li class="letter keyStyle">p</li>
				<li class="letter keyStyle">a</li>
				<li class="letter keyStyle">s</li>
				<li class="letter keyStyle">d</li>
				<li class="letter keyStyle">f</li>
				<li class="letter keyStyle">g</li>
				<li class="letter keyStyle">h</li>
				<li class="letter keyStyle">j</li>
				<li class="letter keyStyle">k</li>
				<li class="letter keyStyle">l</li>
				<li class="letter keyStyle">z</li>
				<li class="letter keyStyle">x</li>
				<li class="letter keyStyle">c</li>
				<li class="letter keyStyle">v</li>
				<li class="letter keyStyle">b</li>
				<li class="letter keyStyle">n</li>
				<li class="letter keyStyle">m</li>
				<li class="symbol keyStyle"><span class="off">[</span><span class="on">{</span></li>
				<li class="symbol keyStyle"><span class="off">]</span><span class="on">}</span></li>
				<li class="symbol keyStyle lastitem"><span class="off">\</span><span
					class="on">|</span></li>
				<li class="symbol keyStyle"><span class="off">;</span><span class="on">:</span></li>
				<li class="symbol keyStyle"><span class="off">'</span><span class="on">&quot;</span></li>
				<li class="symbol keyStyle"><span class="off">,</span><span class="on">&lt;</span></li>
				<li class="symbol keyStyle"><span class="off">.</span><span class="on">&gt;</span></li>
				<li class="symbol keyStyle"><span class="off">/</span><span class="on">?</span></li>
				<li class="capslock keyStyle">caps lock</li>
				<li class="return lastitem keyStyle">return</li>
				<li class="left-shift keyStyle">shift</li>
			</ul>
		</div>
		<div id="body">
			<p>
				<button id="button" class="alpBtn">Type</button>
			</p>
		</div>
	</div>
	<%
		String className = (String) request.getAttribute("cls");
		if(className == null){
			className = "Main";
		}
		%>
		<center>
<form action="insert.action" method="POST" name="codeForm" id="codeForm">
	<input type="hidden" value="<%=className %>" name="className"/>
	<div id="container">
		<div class="canvas">Your Coding Canvas:</div>
		<textarea id="write" class="codebox" name="create">${create}</textarea>
		<ul id="keyboard">
			<li class="symbol keyStyle"><span class="off">1</span><span class="on">!</span></li>
			<li class="symbol keyStyle"><span class="off">2</span><span class="on">@</span></li>
			<li class="symbol keyStyle"><span class="off">3</span><span class="on">#</span></li>
			<li class="symbol keyStyle"><span class="off">4</span><span class="on">$</span></li>
			<li class="symbol keyStyle"><span class="off">5</span><span class="on">%</span></li>
			<li class="symbol keyStyle"><span class="off">6</span><span class="on">^</span></li>
			<li class="symbol keyStyle"><span class="off">7</span><span class="on">&amp;</span></li>
			<li class="symbol keyStyle"><span class="off">8</span><span class="on">*</span></li>
			<li class="symbol keyStyle"><span class="off">9</span><span class="on">(</span></li>
			<li class="symbol keyStyle"><span class="off">0</span><span class="on">)</span></li>
			<li class="symbol keyStyle" id="semicol"><span class="off">;</span></li>
			<li class="delete lastitem keyStyle">delete</li>
			<li class="finish keyStyle">Finish</li>
			<li class="newline keyStyle">New Line</li>
			<li class="main keyStyle">Main</li>
			<li class="decl keyStyle">Var Decl</li>
			<li class="coll keyStyle">Coll Decl</li>
			<li class="methdecl keyStyle">Func Decl</li>
			<li class="syso keyStyle">Print</li>
			<li class="sym keyStyle">+</li>
			<li class="sym keyStyle">-</li>
			<li class="sym keyStyle">*</li>
			<li class="sym keyStyle">/</li>
			<li class="sym keyStyle">%</li>
			<li class="sym keyStyle">^</li>
			<li class="sym keyStyle">!</li>
			<li class="sym keyStyle">=</li>
			<li class="sym keyStyle">&#60;</li>
			<li class="sym keyStyle" id="gt">&#62;</li>
			<li class="varstore keyStyle">Store in Var</li>
			<li class="types keyStyle">int </li>
			<li class="types keyStyle">float </li>
			<li class="types keyStyle">char </li>
			<li class="types keyStyle">double </li>
			<li class="colltypes keyStyle">int array</li>
			<li class="colltypes keyStyle">float array</li>
			<li class="colltypes keyStyle">char array</li>
			<li class="colltypes keyStyle">double</li>
			<li class="colltypes keyStyle">map</li>
			<li class="colltypes keyStyle">list</li>
			<li class="accMod keyStyle">Acc Mod</li>
			<li class="metAccMod keyStyle">public </li>
			<li class="metAccMod keyStyle">private </li>
			<li class="metAccMod keyStyle">protected </li>
			<li class="retType keyStyle">Return Type</li>
			<li class="metRet keyStyle">void </li>
			<li class="metRet keyStyle">int </li>
			<li class="metRet keyStyle">float </li>
			<li class="metCreate keyStyle">Create Func</li>
			<li class="metEnd keyStyle">End Func</li>
		</ul>
		<ul id="loopsKeypad">
		<li class=""></li>
		<li class=""></li>
		<li class=""></li>
		</ul>
	</div>
	<br>
	<br>
	<input type="button" class="button" name="submitbutton" id="submitbutton" value="Compile and Run Code"/>
	<input type="submit" class="button" value="Save Class" />
	<input type="hidden" name="cls" value="${cls}"/>
	</form>
	<div id="compileResult" class="compileRes"></div>
	</center>
</body>
</html>