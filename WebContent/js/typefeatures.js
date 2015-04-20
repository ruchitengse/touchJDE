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
	$('.finish').click(function() {
		/* $('#write').append("\n}"); */
	});
	$('.syso').click(function() {
		var clicks = $(this).data('clicks');
		if (clicks) {
			$('#write').append(');');
		} else {
			$('#write').append('System.out.println(');
		}
		$(this).data("clicks", !clicks);
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

		// Set the effect type
		//var effect = 'slide';
		// Set the options for the effect type chosen
		var options = {
			direction : 'left'
		};
		// Set the duration (default: 400 milliseconds)
		var duration = 500;
		$('#chrDiv').toggle(options, duration);
	});
});