/*References: http://code.tutsplus.com/tutorials/creating-a-keyboard-with-css-and-jquery--net-5774
 */
/*----Javascript file to handle all On-screen keypad events----*/
$(function() {
	var $write = $('#write'), shift = false, capslock = false;
	if (!$('.openedWrite')) {
		$write.append("\npublic static void main(String[] args)\n");
		$write.append("{\n");
	}
	// console.log(pkgName, clsName, $write);
	$('#keyboard li')
			.click(
					function() {
						var $this = $(this), character = $this.html(); // If
																		// it's
																		// a
						// lowercase
						// letter, nothing
						// happens to this
						// variable
						// Shift keys
						if ($this.hasClass('left-shift')
								|| $this.hasClass('right-shift')) {
							$('.letter').toggleClass('uppercase');
							$('.symbol span').toggle();

							shift = (shift === true) ? false : true;
							capslock = false;
							return false;
						}

						// Caps lock
						if ($this.hasClass('capslock')) {
							$('.letter').toggleClass('uppercase');
							capslock = true;
							return false;
						}

						// Delete
						if ($this.hasClass('delete')) {
							var html = $write.html();
							$write.html(html.substr(0, html.length - 1));
							return false;
						}
						
						if($(this).attr('id') == 'semicol') {
							character = ';';
						}

						// Special characters
						if ($this.hasClass('symbol'))
							character = $('span:visible', $this).html();
						if ($this.hasClass('space'))
							character = ' ';
						if ($this.hasClass('tab'))
							character = "\t";
						if ($this.hasClass('return'))
							character = "\n";

						// Uppercase letter
						if ($this.hasClass('uppercase'))
							character = character.toUpperCase();

						// Remove shift once a key is clicked.
						if (shift === true) {
							$('.symbol span').toggle();
							if (capslock === false)
								$('.letter').toggleClass('uppercase');
							shift = false;
						}

						if ($this.hasClass('decl') || $this.hasClass('coll')
								|| $this.hasClass('methdecl')
								|| $this.hasClass('syso')
								|| $this.hasClass('accMod')
								|| $this.hasClass('retType')
								|| $this.hasClass('metCreate')
								|| $this.hasClass('metRet')
								|| $this.hasClass('metAccMod')
								|| $this.hasClass('conds1')
								|| $this.hasClass('conds2')
								|| $this.hasClass('condDec')
								|| $this.hasClass('condLoop')
								|| $this.hasClass('numpad')
								|| $this.hasClass('numsyms')
								|| $this.hasClass('comm')) {
							character = '';
						}

						if ($this.hasClass('metEnd')) {
							character = '	}';
						}
						if ($this.hasClass('main')) {
							character = '\npublic static void main(String[] args) {\n';
						}
						// Store variable & display as button when created
						if ($this.hasClass('varstore')) {
							var x = Math.floor((Math.random() * 100) + 1);
							varName = 'var' + x;
							varDecl = varName + "=";
							character = " " + varDecl;
							$('.varDisplay #keyboard').append(
									"<li class='varList'>" + varName + "</li>");
						}
						// Store method & display as button when created
						if ($this.hasClass('metCreate')) {
							var x = Math.floor((Math.random() * 10) + 1);
							var funcName = "func" + x;
							var open = funcName + "()" + "\n	{";
							character = open + "	";
							$('.funcDisplay #keyboard').append(
									"<li class='funcList'>" + funcName
											+ "</li>");
						}

						if ($this.hasClass('finish')) {
							character = '\n }';
						}

						// Add the character
						$write.html($write.html() + character);
					});
});
/* ----Handles the code in user input area for compilation & execution---- */
$(function() {
	$('#submitbutton').click(
			function() {
				dataString = $('#codeForm').serialize();
				$
						.ajax({
							type : "POST",
							url : "CompileProgram",
							data : dataString,
							dataType : "json",

							success : function(data, textStatus, jqXHR) {
								if (textStatus == "success") {
									$("#compileResult").html(data);
								} else {
									$("#compileResult").html(
											"Error while compiling code!"
													+ textStatus);
								}
							},

							error : function(jqXHR, textStatus, errorThrown) {
								console.log("Something really bad happened: "
										+ textStatus);
								$('#ajaxResponse').html(jqXHR.responseText);
							}
						});
			});
});