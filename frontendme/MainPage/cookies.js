function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  var expires = "expires=" + d.toGMTString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";

}

function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for (var i = 0; i < ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function ifNotLogged() {
  if (getCookie("cin") == "undefined" || getCookie("cin") == 0) {
    location.href = "../Registration/index.html";
  }
}

function LoginCheck() {
  if (getCookie("cin") != "undefined" && getCookie("password") != "undefined" && getCookie("cin") != 0 && getCookie("password") != 0) {
    location.href = "../MainPage/MainPage.html";
    console.log('aa');
  }
}
