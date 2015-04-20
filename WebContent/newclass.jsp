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
				<li class="letter">q</li>
				<li class="letter">w</li>
				<li class="letter">e</li>
				<li class="letter">r</li>
				<li class="letter">t</li>
				<li class="letter">y</li>
				<li class="letter">u</li>
				<li class="letter">i</li>
				<li class="letter">o</li>
				<li class="letter">p</li>
				<li class="letter">a</li>
				<li class="letter">s</li>
				<li class="letter">d</li>
				<li class="letter">f</li>
				<li class="letter">g</li>
				<li class="letter">h</li>
				<li class="letter">j</li>
				<li class="letter">k</li>
				<li class="letter">l</li>
				<li class="letter">z</li>
				<li class="letter">x</li>
				<li class="letter">c</li>
				<li class="letter">v</li>
				<li class="letter">b</li>
				<li class="letter">n</li>
				<li class="letter">m</li>
				<li class="symbol"><span class="off">[</span><span class="on">{</span></li>
				<li class="symbol"><span class="off">]</span><span class="on">}</span></li>
				<li class="symbol lastitem"><span class="off">\</span><span
					class="on">|</span></li>
				<li class="symbol"><span class="off">;</span><span class="on">:</span></li>
				<li class="symbol"><span class="off">'</span><span class="on">&quot;</span></li>
				<li class="symbol"><span class="off">,</span><span class="on">&lt;</span></li>
				<li class="symbol"><span class="off">.</span><span class="on">&gt;</span></li>
				<li class="symbol"><span class="off">/</span><span class="on">?</span></li>
				<li class="capslock">caps lock</li>
				<li class="return lastitem">return</li>
				<li class="left-shift">shift</li>
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
			<li class="symbol"><span class="off">1</span><span class="on">!</span></li>
			<li class="symbol"><span class="off">2</span><span class="on">@</span></li>
			<li class="symbol"><span class="off">3</span><span class="on">#</span></li>
			<li class="symbol"><span class="off">4</span><span class="on">$</span></li>
			<li class="symbol"><span class="off">5</span><span class="on">%</span></li>
			<li class="symbol"><span class="off">6</span><span class="on">^</span></li>
			<li class="symbol"><span class="off">7</span><span class="on">&amp;</span></li>
			<li class="symbol"><span class="off">8</span><span class="on">*</span></li>
			<li class="symbol"><span class="off">9</span><span class="on">(</span></li>
			<li class="symbol"><span class="off">0</span><span class="on">)</span></li>
			<li class="symbol" id="semicol"><span class="off">;</span></li>
			<li class="delete lastitem">delete</li>
			<li class="finish">Finish</li>
			<li class="newline">New Line</li>
			<li class="main">Main</li>
			<li class="decl">Var Decl</li>
			<li class="coll">Coll Decl</li>
			<li class="methdecl">Func Decl</li>
			<li class="syso">Print</li>
			<li class="sym">+</li>
			<li class="sym">-</li>
			<li class="sym">*</li>
			<li class="sym">/</li>
			<li class="sym">%</li>
			<li class="sym">^</li>
			<li class="sym">!</li>
			<li class="sym">=</li>
			<li class="sym">&#60;</li>
			<li class="sym" id="gt">&#62;</li>
			<li class="varstore">Store in Var</li>
			<li class="types">int </li>
			<li class="types">float </li>
			<li class="types">char </li>
			<li class="types">double </li>
			<li class="colltypes">int array</li>
			<li class="colltypes">float array</li>
			<li class="colltypes">char array</li>
			<li class="colltypes">double</li>
			<li class="colltypes">map</li>
			<li class="colltypes">list</li>
			<li class="accMod">Acc Mod</li>
			<li class="metAccMod">public </li>
			<li class="metAccMod">private </li>
			<li class="metAccMod">protected </li>
			<li class="retType">Return Type</li>
			<li class="metRet">void </li>
			<li class="metRet">int </li>
			<li class="metRet">float </li>
			<li class="metCreate">Create Func</li>
			<li class="metEnd">End Func</li>
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