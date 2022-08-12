var app = angular.module('myApp', ['ngSanitize']);
var cin = getCookie("cin");
var m = 0;
var classCode, className, unit, description;
var urlStudent = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Students/cin/" + cin;
var urlPath = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Paths/" + cin;
var urlClass;

function ifNotLogged(){
  if(getCookie("cin")=="undefined" || getCookie("cin")==0 ){
    location.href = "../Registration/index.html";
  }
}
ifNotLogged();
app.controller('sideCtrl', function($scope, $http) {
  $http.get(urlStudent).then(function(response) {
    $scope.students = response.data;
  });
});
app.controller('semInfo', function($scope, $http) {
	var date = new Date();
	$scope.year = date.getFullYear();
	$scope.month = date.getMonth() + 1;

	$scope.checkTerm = function(month) {
		if ($scope.month >= 1 && $scope.month <= 6) {
			$scope.term = "Spring";
		}
		else if ($scope.month >= 8 && $scope.month <= 12) {
			$scope.term = "Fall";
		}
	}

	$scope.checkTerm($scope.month);
	urlPath = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Paths/" + cin + "/" + $scope.term + "/" + $scope.year;

	$scope.previousSem = function() {
		if ($scope.term == "Spring") {
			$scope.term = "Fall";
			$scope.year--;
		}
		else if ($scope.term == "Fall") {
			$scope.term = "Spring";
		}
		urlPath = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Paths/" + cin + "/" + $scope.term + "/" + $scope.year;
		$scope.getClasses();
	}

	$scope.nextSem = function() {
		if ($scope.term == "Spring") {
			$scope.term = "Fall";
		}
		else if ($scope.term == "Fall") {
			$scope.term = "Spring";
			$scope.year++;
		}
		urlPath = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Paths/" + cin + "/" + $scope.term + "/" + $scope.year;
		$scope.getClasses();
	}

	$scope.currentSem = function() {
		$scope.year = date.getFullYear();;
		$scope.month =  date.getMonth() + 1;
		$scope.checkTerm($scope.month);
		urlPath = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Paths/" + cin + "/" + $scope.term + "/" + $scope.year;
		$scope.getClasses();
	}

	$scope.getClasses = function() {
		$scope.courses = [];
		$http.get(urlPath).then(function(response) {
			$scope.classes = angular.fromJson(response.data);
			for (n in $scope.classes) {
				var urlClass = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Classes/classCode/" + $scope.classes[n].classCode;
				$http.get(urlClass).then(function(response) {
					$scope.class = angular.fromJson(response.data);
					$scope.courses.push($scope.class[0]);
				});
			}
		});
	}
});

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
