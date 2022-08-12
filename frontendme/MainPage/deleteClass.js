var app = angular.module("adminPage");

app.controller("deleteClass", ['$scope', '$http' ,'$rootScope', function($scope, $http, $rootScope) {
	var cc;
	$scope.getClass = function() {
		console.log("getting class");
		var url = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Classes";
		$http.get(url).then(function(response) {
			$scope.classList1 = [];
			$scope.classList2 = [];
			$scope.classList3 = [];
			$scope.classList4 = [];
			var classList = angular.fromJson(response.data);
			console.log("got data from .get");

			for (var i = 0; i < classList.length; i++) {
				cc = classList[i].classCode;
				if (cc.substring(cc.indexOf(" ") + 1) < 2000) {
					$scope.classList1.push(classList[i]);

				} else if (cc.substring(cc.indexOf(" ") + 1) < 3000) {
					$scope.classList2.push(classList[i]);

				} else if (cc.substring(cc.indexOf(" ") + 1) < 4000) {
					$scope.classList3.push(classList[i]);

				} else {
					$scope.classList4.push(classList[i]);

				}
			}

		}, function(response) {
			console.log("something is  wrong");
		});
	};

	    $rootScope.$on("callGetClass", function(){
           $scope.getClass();
        });

	$scope.delClass = function(classCode) {
			var url = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Classes/" + classCode;
			$http.delete(url).then(function(response) {
				$scope.getClass();
			}, function(response) {
				console.log("something is  wrong with delete");
			});
		};
	}]);
