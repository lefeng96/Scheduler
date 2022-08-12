  var app = angular.module('myApp', ['ngSanitize']);
  var cin = getCookie("cin");
  var urlStudent = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Students/cin/" + cin;
  app.controller('sideCtrl', function($scope, $http) {
    $http.get(urlStudent).then(function(response) {
      $scope.students = response.data;
    });
  });

  function ifNotLogged() {
    if (getCookie("cin") == "undefined" || getCookie("cin") == 0) {
      location.href = "../Registration/index.html";
    }
  }
  ifNotLogged();
