function btn(x) {
	/* x.style.background = "yellow"; */
	for (var i = 1; i < 10; i++) {
		var btnsp = '<button value="' + i + '" id="btngen' + i + '">' + i
				+ '</button>';
		document.getElementById("btnsplace").innerHTML += btnsp;
		document.getElementById("btnsplace").innerHTML += i;
		document.getElementById("area2").value = i;
	}
	/* document.getElementById("btnsplace").innerHTML = btnsp; */
}
function btnimp(x) {
	/* x.style.background = "yellow"; */
	/* alert("imp button"); */
	var btns = '<button id="btngen">new button</button>';
	document.getElementById("btnsplace").innerHTML = btns;
	document.getElementById("btnsplace").innerHTML += x.value;
}
function printtext(x) {
	/* x.style.background = "yellow"; */
	/* alert("imp button"); */
	alert(x.value);
	/* document.getElementById("btnsplace").innerHTML = btns; */
}
$(function(){
    $(document).on("click", "#createBtn", function(event){
        alert( "GO" ); 
    }); 
});