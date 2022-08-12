var cin = getCookie("cin");
var urlStudent = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Students/cin/" + cin;

$('.open-popup-link').magnificPopup({
  type: 'inline',
  midClick: true,
  mainClass: 'mfp-fade'
});

$('#home-btn').click(function() {
	location.reload();
	$('modify-field').load('adminPage.html');
	$('#home-field').css('display', 'block');
	$('#search-field').css('display', 'none');
	$('#modify-field').css('display', 'none');
	$('#modify-field').css('display', 'none');
});
$('#modify-btn').on('click', function() {
	$('modify-field').load('adminPage.html');
	$('#home-field').css('display', 'none');
	$('#delete-field').css('display', 'none');
	$('#search-field').css('display', 'none');
	$('#modify-field').css('display', 'block');
});
$('#delete-btn').on('click', function() {	
	$('delete-field').load('adminPage.html');
	$('#home-field').css('display', 'none');
	$('#modify-field').css('display', 'none');
	$('#search-field').css('display', 'none');
	$('#delete-field').css('display', 'block');			
});
$('#search-btn').on('click', function() {
	$('search-field').load('adminPage.html');
	$('#home-field').css('display', 'none');
	$('#delete-field').css('display', 'none');
	$('#modify-field').css('display', 'none');
	$('#search-field').css('display', 'block');
});

function logout() {
	setCookie("cin", 0, 30);
	setCookie("password", 0, 30);
	location.href = "../Registration/index.html";
}

var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
    acc[i].addEventListener("click", function() {
        this.classList.toggle("active");
        var panel = this.nextElementSibling;
        if (panel.style.display === "block") {
            panel.style.display = "none";
        } else {
            panel.style.display = "block";
        }
    });
}