<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>TouchJDE - Your Java Web Environment</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="description"
	content="Expand, contract, animate forms with jQuery wihtout leaving the page" />
<meta name="keywords"
	content="expand, form, css3, jquery, animate, width, height, adapt, unobtrusive javascript" />
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
				<form class="register active" action="register.action" method="post">
					<h3>Register</h3>
					<div class="column">
						<div>
							<label>Username:</label> <input type="text" name="username" />
						</div>
						<div>
							<label>Email:</label> <input type="text" name="email" />
						</div>
						<div>
							<label>Password:</label> <input type="password" name="password" />
							<%
								if (((String) request.getAttribute("regerror")) != null) {
							%>
							<span class="error">Username already exists! Try Again</span>
							<%
								}
							%>
						</div>
					</div>
					<div class="bottom">
						<input type="submit" value="Register" /> <a href="login.jsp"
							rel="login" class="linkform">Existing User? Log in here</a>
						<div class="clear"></div>
					</div>
				</form>
				<form class="login" action="login.action" method="post">
					<h3>Login</h3>
					<div>
						<label>Username:</label> <input type="text" />
					</div>
					<div>
						<label>Password: </label> <input type="password" />
					</div>
					<div class="bottom">
						<input type="submit" value="Login"></input> <a href="register.jsp"
							rel="register" class="linkform">You don't have an account
							yet? Register here</a>
						<div class="clear"></div>
					</div>
				</form>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div id="footer">TouchJDE - 2015 Developed @ UTA</div>
	<!-- Register page handler -->
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