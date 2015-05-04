$(function() {
	$('.symbol').click(function() {
		$('.sym').show();
	});
	$('.decl').click(function() {
		$('.types').show();
	});
	$('#semicol').click(function() {
		$('#write').append('\n');
	});
	$('.coll').click(function() {
		$('.colltypes').show();
	});
	$('.types').click(function() {
		$('.varstore').show();
	});
	$('.varstore').click(function() {
		$('.varstore').show();
		/* $.session.get("sessVar"); */
	});
	$('.methdecl').click(function() {
		$('.accMod').show();
		$('.metAccMod').show();
		$('.metAccMod').click(function() {
			$('.accMod').hide();
			$('.retType').show();
			$('.metRet').show();
		});
		$('.metRet').click(function() {
			$('.retType').hide();
			$('.metRet').hide();
			$('.metRet').click(function() {
				$('.retType').hide();
				$('.metRet').hide();
			});
		});
	});

	$('.metRet').click(function() {
		$('.metCreate').show();
	});
	$('.metCreate').click(function() {
		$('.metEnd').show();
	});
	/*
	 * $('.syso').click(function() { $('#write').append('System.Out.Println(');
	 * });
	 */
	$('.syso').after('<br/><br/><br/>');
	$('.newline').after('<br/><br/><br/>');
	$('#gt').after('<br/><br/><br/>');
	if ($('.sym').not(':hidden')) {
		$('.sym').click(function() {
			$('.sym').hide();
		});
	}
	if ($('.types').not(':hidden')) {
		$('.types').click(function() {
			$('.types').hide();
		});
	}
	if ($('.metAccMod').not(':hidden')) {
		$('.metAccMod').click(function() {
			$('.metAccMod').hide();
			$('.metRet').show();
		});
	}
	//Decision Conditions click
	$('.conds1').click(function() {
		$('.condDec').show();
		$('#condDecElsIf').hide();
		$('#condDecEls').hide();
	});
	//Looping Conditions click
	$('.conds2').click(function() {
		$('.condLoop').show();
	});
	$('.finish').click(function() {
		/* $('#write').append("\n}"); */
	});
	/* System.out.println */
	$('.syso').click(function() {
		var clicks = $(this).data('clicks');
		if (clicks) {
			$('#write').append(');');
		} else {
			$('#write').append('\nSystem.out.println(');
		}
		$(this).data("clicks", !clicks);
	});
	
	//Display stored variables for further usage
	$('.varDisp').on("click", "li", function() {
	    var varName = $(this).html();
	    $('#write').append(varName);
	});
	
	$('.funcDisp').on("click", "li", function() {
	    var funcName = $(this).html();
	    $('#write').append(funcName);
	});
	
	//if condition
	$('#condDecif').click(function() {
		var ifclicks = $(this).data('ifclicks');
		if (ifclicks) {
			$('#write').append(') {');
		} else {
			$('#write').append('\nif (');
		}
		$(this).data("ifclicks", !ifclicks);
		$('#condDecElsIf').show();
		$('#condDecEls').show();
	});

	//else if condition
	$('#condDecElsIf').click(function() {
		var elsifclicks = $(this).data('elsifclicks');
		if (elsifclicks) {
			$('#write').append(')	{\n');
		} else {
			$('#write').append('\nelse if(');
		}
		$(this).data("elsifclicks", !elsifclicks);
	});
	
	//else condition
	$('#condDecEls').click(function() {
		$('#write').append('\nelse	{');
		$('#condDecEls').hide();
	});
	
	//while loop
	$('#condLoopWhi').click(function() {
		var whiclicks = $(this).data('whiclicks');
		if (whiclicks) {
			$('#write').append(') {');
		} else {
			$('#write').append('\nwhile (');
		}
		$(this).data("whiclicks", !whiclicks);
	});

	//do while loop
	$('#condLoopDoWhi').click(function() {
		var dowhiclicks = $(this).data('dowhiclicks');
		if (dowhiclicks) {
			$('#write').append('\n} while(');
		} else {
			$('#write').append('\ndo {');
		}
		$(this).data("dowhiclicks", !dowhiclicks);
	});
	/*
	 * $("#save").click(function() { var name =
	 * document.getElementById("write"); var s = name.value; arr.push(s);
	 * alert(arr); });
	 */
	$('#save').click(function() {

		if (window.sessionStorage) {
			sessionStorage.setItem("saveClass", $('#write').val());
			/*
			 * setTimeout(function() {
			 * //window.location.replace("http://jsfiddle.net/chauhangs/AfhGR/1/show"); },
			 * 500);
			 */
			alert(sessionStorage.getItem("saveClass"));
		}
	});
	$("a[name='openClass']").click(function() {
		// if (sessionStorage.getItem("saveClass") != "") {
		// e.preventDefault();
		alert(sessionStorage.getItem("saveClass"));
		window.location.href = 'openedclass.jsp';
		$('#write').text(sessionStorage.getItem("saveClass"));
		// }
	});
	$('.alpBtn').click(function() {
		var options = {
			direction : 'left',
		};
		var duration = 500;
		$('#chrDiv').toggle(options, duration);
	});
	/*
	 * for (var i = 0; i < sessionStorage.length; i++) {
	 * console.log(sessionStorage.key(i))  };
	 */
});