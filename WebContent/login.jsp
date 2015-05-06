<!-- 
**
Login page of the application
 **
 -->
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>TouchJDE - Your Java Web Environment</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/login_reg_style.css" />
<link rel="stylesheet" type="text/css" href="css/indexStyle.css" />
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<h1>TouchJDE</h1>
		<h2>Your online Java Environment</h2>
		<div class="contentLeft">
			<p>
				This is touch JDE!! Your JAVA web environment. Everything in your
				native browser. No installation.<br /> Know JAVA?? Then start
				coding!!
			</p>
		</div>
		<div class="contentRight">
			<div id="form_wrapper" class="form_wrapper">
				<form class="register">
					<h3>Register</h3>
					<div class="column">
						<div>
							<label>Username:</label> <input type="text" /> 
						</div>
						<div>
							<label>Email:</label> <input type="text" /> 
						</div>
						<div>
							<label>Password:</label> <input type="password" /> 
						</div>
					</div>
					<div class="bottom">
						<!-- <div class="remember">
								<input type="checkbox" />
								<span>Send me updates</span>
							</div> -->
						<input type="submit" value="Register" /> <a href="login.jsp"
							rel="login" class="linkform">Existing User? Log in here</a>
						<div class="clear"></div>
					</div>
				</form>
				<form class="login active" action="login.action" method="post">
					<h3>Login</h3>
					<div>
						<label>Username:</label> <input type="text" name="lusername" />
					</div>
					<div>
						<label>Password: <!-- <a href="forgot_password.html" rel="forgot_password" class="forgot linkform">Forgot your password?</a>  -->
						</label> <input type="password" name="lpassword" />
						<%
							if (((String) request.getAttribute("errorplace")) != null) {
						%>
						<span class="error">Username/Password Incorrect</span>
						<%
							}
						%>
					</div>
					<div class="bottom">
						<input type="submit" value="Login"></input> <a href="register.jsp"
							rel="register" class="linkform">New User? Register here</a>
						<div class="clear"></div>
					</div>
				</form>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<script type="text/javascript">
		$("form :input").change(function() {
			console.log($("form").serialize());
		});
	</script>
	<div id="footer">TouchJDE - 2015 Developed @ UTA</div>
	<script type="text/javascript">
		$(function() {
			//the form wrapper (includes all forms)
			var $form_wrapper = $('#form_wrapper'),
			//the current form is the one with class active
			$currentForm = $form_wrapper.children('form.active'),
			//the change form links
			$linkform = $form_wrapper.find('.linkform');

			//get width and height of each form and store them for later						
			$form_wrapper.children('form').each(function(i) {
				var $theForm = $(this);
				//solve the inline display none problem when using fadeIn fadeOut
				if (!$theForm.hasClass('active'))
					$theForm.hide();
				$theForm.data({
					width : $theForm.width(),
					height : $theForm.height()
				});
			});

			//set width and height of wrapper (same of current form)
			setWrapperWidth();
			$linkform.bind('click', function(e) {
				var $link = $(this);
				var target = $link.attr('rel');
				$currentForm.fadeOut(200, function() {
					//remove class active from current form
					$currentForm.removeClass('active');
					//new current form
					$currentForm = $form_wrapper.children('form.' + target);
					//animate the wrapper
					$form_wrapper.stop().animate({
						width : $currentForm.data('width') + 'px',
						height : $currentForm.data('height') + 'px'
					}, 300, function() {
						//new form gets class active
						$currentForm.addClass('active');
						//show the new form
						$currentForm.fadeIn(200);
					});
				});
				e.preventDefault();
			});

			function setWrapperWidth() {
				$form_wrapper.css({
					width : $currentForm.data('width') + 'px',
					height : $currentForm.data('height') + 'px'
				});
			}
		});
	</script>
</body>
</html>