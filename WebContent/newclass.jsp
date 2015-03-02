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
<title>TouchJDE New Class</title>
</head>
<body>
	<div id="header">
		<p>
			touch<b>JDE</b>
		</p>
	</div>
	<div id="content"></div>
	<%-- <textarea readonly id="area" cols="50" rows="1" style="border: none">public static void main(String args[]) {</textarea>
	<br />
	<textarea id="area2" cols="50" rows="10" onfocus="btn(this)"
		style="border: none"></textarea>
	<br />
	<textarea readonly id="area" cols="50" rows="1" style="border: none">}</textarea>
	<br />

	<button id="btnimp" onclick="btnimp(this)" value="'<br/>'">import</button> --%>

	<div id="container">
		<textarea id="write" rows="6" cols="60"></textarea>
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
			<li class="methdecl">Func Decl</li>
			<li class="decl">Var Decl</li>
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
			<li class="coll">Coll</li>
			<li class="colltypes">int array</li>
			<li class="colltypes">float array</li>
			<li class="colltypes">char array</li>
			<li class="colltypes">double</li>
			<li class="colltypes">map</li>
			<li class="colltypes">list</li>
			<li class="metAccMod">public</li>
			<li class="metAccMod">private</li>
			<li class="metAccMod">protected</li>
			<li class="metRet">void</li>
			<li class="metRet">int</li>
			<li class="metRet">float</li>
		</ul>
	</div>
	<div id="btnsplace"></div>
</body>
</html>