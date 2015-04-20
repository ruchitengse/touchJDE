/*References: http://code.tutsplus.com/tutorials/creating-a-keyboard-with-css-and-jquery--net-5774
 */
//var clsName;
$(function() {
	//alert($('#clsName').val());
	/*$("#formData").submit(function(e) {
		//e.preventDefault();
	    clsName = $(this).find('input[id=clsName]').val();
	    console.log(clsName);
	    alert(clsName);
	});*/
	/*alert($('#clsName').html());
	var pkgName = $('#pkgName').val();
	$write.append("package "+pkgName+";\n");
	$write.append("public class "+clsName+" {\n");*/
	var $write = $('#write'), shift = false, capslock = false;
	if(!$('.openedWrite')) {
	$write.append("\npublic static void main(String[] args)\n");
	$write.append("{\n");
	}
	//console.log(pkgName, clsName, $write);
	$('#keyboard li').click(
			function() {
				var $this = $(this), character = $this.html(); // If it's a
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
						|| $this.hasClass('methdecl') || $this.hasClass('syso')
						|| $this.hasClass('accMod')
						|| $this.hasClass('retType')
						|| $this.hasClass('metCreate')) {
					character = '';
				}

				if ($this.hasClass('newline')) {
					character = '\n';
				}
				if ($this.hasClass('metEnd')) {
					character = '	}';
				}
				if ($this.hasClass('main')) {
					character = '\npublic static void main(String[] args) {\n';
				}
				/*
				 * if ($this.hasClass('syso')) { character =
				 * 'System.Out.Println('; }
				 */

				if ($this.hasClass('varstore')) {
					var x = Math.floor((Math.random() * 100) + 1);
					varName = 'var' + x;
					varDecl = varName + "=";
					/* $.session.set("sessVar", varName); */
					character = varDecl;
					/*alert(varName);*/
				}

				if ($this.hasClass('metCreate')) {
					var x = Math.floor((Math.random() * 10) + 1);
					var name = "func" + x;
					var open = name + "()" + "\n	{";
					character = open + "\n		";
				}

				if ($this.hasClass('finish')) {
					character = '\n }';
				}

				/*
				 * if ($this.hasClass('symbol')) { $('.on').show(); }
				 */
				// Add the character
				$write.html($write.html() + character);
			});
});

$(function(){
	$('#submitbutton').click(
			function(){
				dataString = $('#codeForm').serialize();
				$.ajax({
					type: "POST",
					url: "CompileProgram",
					data: dataString,
					dataType: "json",
					
					success: function(data, textStatus, jqXHR){
						if(textStatus == "success"){
							$("#compileResult").html(data);
						} else {
							$("#compileResult").html("Error while compiling code!" + textStatus);
						}
					},
					
					error: function(jqXHR, textStatus, errorThrown){
						console.log("Something really bad happened: " + textStatus);
						$('#ajaxResponse').html(jqXHR.responseText);
					}
				});
			});
});
