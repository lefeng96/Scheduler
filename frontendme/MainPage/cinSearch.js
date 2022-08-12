var app = angular.module('adminPage');

app.controller('cinSearch', function($scope, $http) {
	var date = new Date();
	$scope.currentYear = date.getFullYear();

	var url = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Students";
	$http.get(url).then(function(response) {
		$scope.studentList = angular.fromJson(response.data);
		console.log("got data from .get");
	}, function(response) {
		console.log("something is wrong");
	});

	$scope.showInfoSidebar = function(cin) {
		$('#studentInfoBar').css('display', 'block');

		url = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Students/cin/" + cin;
		$http.get(url).then(function(response) {
			$scope.studentInfo = angular.fromJson(response.data);
			console.log("got data from .get");
		}, function(response) {
			console.log("something is  wrong");
		});

		url = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Paths/" + cin;
		$http.get(url).then(function(response) {
			$scope.studentClassInfo = angular.fromJson(response.data);

			$scope.finalYear = $scope.studentClassInfo[$scope.studentClassInfo.length - 1].year;
				//console.log($scope.finalYear);
			console.log("got data from .get " + $scope.studentClassInfo[0].classCode);
		}, function(response) {
			console.log("something is  wrong");
		});
	}
	
	$http.get(urlStudent).then(function(response) {
        $scope.students = response.data;
    });
	$scope.loadData = function () {
	    url = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Algorithm/"+cin;
		$http.get(url).then(function (response) {
			algrithm = response.data[0];
			stoploading();
		})
		.finally(function() {
				showallyears();
		});
	};
	$scope.loadData();
	$scope.addClass = function (semester,year,classCode) {
		var link = 'http://138.197.211.227:8080/RESTfulProject/REST/WebService/Algorithm/'+cin+'/'+semester+'/'+year+'/'+classCode;
		$http.get(link).then(function (response) {
			algrithm = response.data[0];
			stoploading();
		})
		.finally(function() {
			loadClass();
			changemode();
		});
	};
	$scope.anotherroadmap = function (){
		url = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Algorithm/"+cin;
		$http.get(url).then(function (response) {
			var min = Math.ceil(0);
            var max = Math.floor(7);
            var ran = Math.floor(Math.random() * (max - min)) + min;
			console.log(ran);
			algrithm = response.data[ran];
		})
		.finally(function() {
				showallyears();
		});
	};
});

function searchCIN() {
	var ul, li, a, i;
	var cin = document.getElementById("cinInput").value;
	
	ul = document.getElementById('students');
	li = ul.getElementsByTagName('li');
	for (i = 0; i < li.length; i++) {
		a = li[i].getElementsByTagName('a')[0];
		if (a.innerHTML.indexOf(cin) > -1) {		
			li[i].style.display = '';
		} else {	
			li[i].style.display = 'none';
			
		}
	}
}
