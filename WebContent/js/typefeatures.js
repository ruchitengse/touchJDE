/*References: http://code.tutsplus.com/tutorials/creating-a-keyboard-with-css-and-jquery--net-5774
 */
/*----Javascript file to handle the button events from the on-screen keyboard----*/
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

	$('.metAccMod').click(function() {
		$('#write').append("\n" + $(this).html() + " ");
	});

	$('.metRet').click(function() {
		$('#write').append($(this).html() + " ");
	});
	$('.comm').click(function() {
		$('#write').append("//");
	});
	$('.numpad').click(function() {
		var numpadclicks = $(this).data('numpadclicks');
		if (numpadclicks) {
			$('.symbol').hide();
		} else {
			$('.symbol').show();
		}
		$(this).data("numpadclicks", !numpadclicks);
	});
	$('.numsyms').click(function() {
		var numsympadclicks = $(this).data('numsympadclicks');
		if (numsympadclicks) {
			$('.sym').hide();
		} else {
			$('.sym').show();
		}
		$(this).data("numsympadclicks", !numsympadclicks);
	});
	$('.syso').after('<br/><br/><br/>');
	$('.newline').after('<br/><br/><br/>');
	$('#gt').after('<br/><br/><br/>');
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
	// Decision Conditions click
	$('.conds1').click(function() {
		var conclicks = $(this).data('conclicks');
		if (conclicks) {
			$('.condDec').hide();
		} else {
			$('.condDec').show();
			$('#condDecElsIf').hide();
			$('#condDecEls').hide();
			$('#swiCase').hide();
			$('#swiBreak').hide();
		}
		$(this).data("conclicks", !conclicks);
	});
	// Looping Conditions click
	$('.conds2').click(function() {
		var condclicks = $(this).data('condclicks');
		if (condclicks) {
			$('.condLoop').hide();
		} else {
			$('.condLoop').show();
			$('#forIni').hide();
			$('#forTer').hide();
			$('#forInc').hide();
		}
		$(this).data("condclicks", !condclicks);
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
			$('#write').append('System.out.println(');
		}
		$(this).data("clicks", !clicks);
	});

	// Display stored variables for further usage
	$('.varDisp').on("click", "li", function() {
		var varName = $(this).html();
		$('#write').append(varName);
	});

	// Display stored function names for further usage
	$('.funcDisp').on("click", "li", function() {
		var funcName = $(this).html();
		$('#write').append(funcName + "();");
	});

	// if condition
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

	// else if condition
	$('#condDecElsIf').click(function() {
		var elsifclicks = $(this).data('elsifclicks');
		if (elsifclicks) {
			$('#write').append(')	{\n');
		} else {
			$('#write').append('\nelse if (');
		}
		$(this).data("elsifclicks", !elsifclicks);
	});

	// else condition
	$('#condDecEls').click(function() {
		$('#write').append('\nelse	{');
		$('#condDecEls').hide();
	});

	// while loop
	$('#condLoopWhi').click(function() {
		var whiclicks = $(this).data('whiclicks');
		if (whiclicks) {
			$('#write').append(') {');
		} else {
			$('#write').append('\nwhile (');
		}
		$(this).data("whiclicks", !whiclicks);
	});

	// do while loop
	$('#condLoopDoWhi').click(function() {
		var dowhiclicks = $(this).data('dowhiclicks');
		if (dowhiclicks) {
			$('#write').append('\n} while(');
		} else {
			$('#write').append('\ndo {');
		}
		$(this).data("dowhiclicks", !dowhiclicks);
	});
	/* Switch Case start */
	$('#condDecswitch').click(function() {
		$('#swiCase').show();
		$('#swiBreak').show();
		$('#swiDefault').show();
		var switchclicks = $(this).data('switchclicks');
		if (switchclicks) {
			$('#write').append(') {');
		} else {
			$('#write').append('switch(');
		}
		$(this).data("switchclicks", !switchclicks);
	});
	/*
	 * $('#condDecswitch').click(function() { var switchclicks =
	 * $(this).data('switchclicks'); if (caseclicks) { $('#write').append(')
	 * {'); } else { $('#write').append('\nswitch( '); }
	 * $(this).data("switchclicks", !switchclicks); });
	 */
	$('#swiCase').click(function() {
		var caseclicks = $(this).data('caseclicks');
		if (caseclicks) {
			$('#write').append(' :\n');
		} else {
			$('#write').append('\ncase ');
		}
		$(this).data("caseclicks", !caseclicks);
	});
	$('#swiBreak').click(function() {
		$('#write').append('	break;\n');
	});
	$('#swiDefault').click(function() {
		$('#write').append('	default:\n');
	});
	/* Switch Case end */

	/* for Start */
	$('#condLoopFor').click(function() {
		$('#forIni').show();
		$('#forTer').show();
		$('#forInc').show();
	});
	$('#forIni').click(function() {
		var forIniclicks = $(this).data('forIniclicks');
		if (forIniclicks) {
			$('#write').append(';');
		} else {
			$('#write').append('for(');
		}
		$(this).data("forIniclicks", !forIniclicks);
	});
	$('#forTer').click(function() {
		$('#write').append(';');
	});
	$('#forInc').click(function() {
		$('#write').append(') {');
	});
	/* for end */
	$('#save').click(function() {
		if (window.sessionStorage) {
			sessionStorage.setItem("saveClass", $('#write').val());
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
	// Alphabets screenpad display
	$('.alpBtn').click(function() {
		var options = {
			direction : 'left',
		};
		var duration = 500;
		$('#chrDiv').toggle(options, duration);
	});
});