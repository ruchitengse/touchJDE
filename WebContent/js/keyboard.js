$(function() {
	var $write = $('#write'), shift = false, capslock = false;
	$write.append("public static void main(String[] args)\n");
	$write.append("{\n");
	$('#keyboard li').click(function() {
		var $this = $(this), character = $this.html(); // If it's a lowercase
		// letter, nothing
		// happens to this
		// variable
		// Shift keys
		if ($this.hasClass('left-shift') || $this.hasClass('right-shift')) {
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

		if ($this.hasClass('decl') || $this.hasClass('coll')|| $this.hasClass('methdecl')) {
			character = '';
		}
		
		if ($this.hasClass('newline')) {
			character = '\n';
		}
		
		if ($this.hasClass('varstore')) {
			var x = Math.floor((Math.random() * 100) + 1);
			character = 'var'+x+" =";
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
