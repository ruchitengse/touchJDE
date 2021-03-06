<!-- References: http://code.tutsplus.com/tutorials/creating-a-keyboard-with-css-and-jquery--net-5774 -->
<!-- Stored in the repository: https://github.com/jaggi-sg/touchJDE -->
<!-- 
**
*Main page where user enters the code in the code area
**
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String username = (String) session.getAttribute("username");
	if(username == null || username.equals(0)){
		request.getRequestDispatcher("login.jsp").forward(request, response);
}
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/keyboard.js"></script>
<script type="text/javascript" src="js/typefeatures.js"></script>
<link rel="stylesheet" type="text/css" href="css/keypad.css">
<link rel="stylesheet" type="text/css" href="css/indexStyle.css">
<link rel="stylesheet" type="text/css" href="css/login_reg_style.css">
<title>TouchJDE New Class</title>
</head>
<body>
	<div id="header">
		<p>
			<a href="index.jsp">touch<b>JDE</b></a>
		</p>
		<hr>
	</div>
	<jsp:include page="keypad.jsp"></jsp:include>
	<%
		String projectName = (String) request.getSession().getAttribute("projectName");
		String packageName = (String) request.getSession().getAttribute("packageName");
		String className = (String) request.getSession().getAttribute("cls");
		String user = (String) request.getSession().getAttribute("username");
	%>
	<center>
		<form action="insert.action" method="POST" name="codeForm"
			id="codeForm">
			<input type="hidden" value=<%=user %> name="username"/>
			<input type="hidden" value=<%=projectName %> name="projectName"/>
			<input type="hidden" value=<%=packageName %> name="packageName"/>
			<input type="hidden" value="<%=className%>" name="className" />
			<div id="container">
				<div class="canvas">Your Coding Canvas:</div>
				<textarea id="write" class="codebox" name="create">${create}</textarea>
				<ul id="keyboard">
				<li class="numpad">Num Pad</li>
				<li class="numsyms">Math Symbols</li>
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
					<li id="semicol"><span class="off">;</span></li>
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
					<li class="delete lastitem">delete</li>
					<li class="finish">Finish</li>
					<li class="comm">Comment</li>
					<li class="newline">New Line</li>
					<li class="main">Main<br/>(PSVM)</li>
					<li class="decl">Var Decl</li>
					<li class="coll">Coll Decl</li>
					<li class="methdecl">Func Decl</li>
					<li class="conds1 lastitem">Decision<br />Conds
					</li>
					<li class="conds2">Looping<br />Conds
					</li>
					<li class="syso">Print<br />(syso)
					</li>
					<li class="varstore">Store in Var</li>
					<li class="types">int</li>
					<li class="types">float</li>
					<li class="types">char</li>
					<li class="types">double</li>
					<li class="colltypes">int array</li>
					<li class="colltypes">float array</li>
					<li class="colltypes">char array</li>
					<li class="colltypes">double</li>
					<li class="colltypes">map</li>
					<li class="colltypes">list</li>
					<li class="accMod">Acc Mod</li>
					<li class="metAccMod">public</li>
					<li class="metAccMod">private</li>
					<li class="metAccMod">protected</li>
					<li class="retType">Return Type</li>
					<li class="metRet">void</li>
					<li class="metRet">int</li>
					<li class="metRet">float</li>
					<li class="metCreate">Create Func</li>
					<li class="metEnd">End Func</li>
					<li class="condDec" id="condDecif">if</li>
					<li class="condDec" id="condDecElsIf">else if</li>
					<li class="condDec" id="condDecEls">else</li>
					<li class="condDec" id="condDecswitch">switch</li>
					<li class="condLoop" id="condLoopIni">Loop Init</li>
					<li class="condLoop" id="condLoopWhi">while</li>
					<li class="condLoop" id="condLoopDoWhi">do while</li>
					<li class="condLoop" id="condLoopFor">for</li>
					<li class="condDec" id="swiCase">case</li>
					<li class="condDec" id="swiBreak">break</li>
					<li class="condDec" id="swiDefault">default</li>
					<li class="condLoop" id="forIni">for Init</li>
					<li class="condLoop" id="forTer">for Term</li>
					<li class="condLoop" id="forInc">for Inc</li>
				</ul>
			</div>
			<br/><br/><br/> <br> <input type="button" class="btn-3"
				name="submitbutton" id="submitbutton" value="Compile and Run Code" />
			<input type="submit" class="btn-3" value="Save Class" /> <input
				type="hidden" name="cls" value="${cls}" />
		</form>
		<div class="varDisplay">
			<p>Variables Used:</p>
			<ul id="keyboard" class="varDisp">
			</ul>
		</div>
		<div class="funcDisplay">
			<p>Functions Used:</p>
			<ul id="keyboard" class="funcDisp">
			</ul>
		</div>
		<div id="compileResult" class="compileRes"></div>
	</center>
	<a class="back" href="/touchJDE/logout.action">Logout <%=user%></a>
</body>
</html>