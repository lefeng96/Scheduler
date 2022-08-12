$('#sidebarCollapse').on('click', function() {
	$('#sidebar').toggleClass('active');
	$(this).toggleClass('active');
});

$(document).ready(function() {
	function setCookie(cname, cvalue, exdays) {
		var d = new Date();
		d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
		var expires = "expires=" + d.toGMTString();
		document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";

	}

	function logout() {
		setCookie("cin", 0, 30);
		setCookie("password", 0, 30);
		location.href = "../Registration/index.html";
	}
});
