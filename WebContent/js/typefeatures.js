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
	$('.conds1').click(function() {
		$('.condDec').show();
		$('#condDecElsIf').hide();
		$('#condDecEls').hide();
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
	
	//if condition
	$('#condDecif').click(function() {
		var ifclicks = $(this).data('ifclicks');
		if (ifclicks) {
			$('#write').append(')	{');
		} else {
			$('#write').append('\nif(');
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
		$('#write').append('else	{');
		$('#condDecEls').hide();
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
	/*
	 * for (var i = 0; i < sessionStorage.length; i++) {
	 * console.log(sessionStorage.key(i)) };
	 */
	$('.alpBtn').click(function() {
		var options = {
			direction : 'left'
		};
		var duration = 500;
		$('#chrDiv').toggle(options, duration);
	});
});