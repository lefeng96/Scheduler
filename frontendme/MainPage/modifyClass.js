var app = angular.module('adminPage', []);

app.controller('modifyClass', ['$scope', '$http', '$rootScope', function($scope, $http, $rootScope) {
	$scope.loadClass = function() {
		var url = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Classes";
		$http.get(url).then(function(response) {
			$scope.classList = angular.fromJson(response.data);
			console.log("got data from .get");

		}, function(response) {
			console.log("something is  wrong");
		});
	};

	$scope.loadPrereq = function() {
		var url = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Pre";
		$http.get(url).then(function(response) {
			$scope.prereq = angular.fromJson(response.data);
			console.log("got prereq data from .get");

		}, function(response) {
			console.log("something is  wrong");
		});
	}

	$scope.loadCoreq = function() {
		var url = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Coreq";
		$http.get(url).then(function(response) {
			$scope.coreq = angular.fromJson(response.data);
			console.log("got coreq data from .get");
			console.log($scope.coreq);
		}, function(response) {
			console.log("something is  wrong");
		});
	}

	var subject, classCode, url, prereq, coreq;
	$scope.addClass = function() {
		subject = $('#select-subject').val();

		if (subject == "Computer Science") {
			classCode = 'CS ';
		} else if (subject == "Physics") {
			classCode = 'PHYS ';
		} else if (subject == "Math") {
			classCode = 'MATH ';
		} else if (subject == "Electrical Engineering") {
			classCode = 'EE ';
		}
		classCode += $scope.classNum;
		prereq = $('#prerequisite').val();
		coreq = $('#corequisite').val();
		

		url = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Classes/" +
			subject + '/' + classCode + '/' + $scope.className + '/' + $scope.classUnits + '/' + $scope.description;
		$http({
				url: url,
				method: "POST",
				data: {
					'subject': 'subject',
					'classCode': 'classCode',
					'className': '$scope.className',
					'unit': '$scope.classUnits',
					'description': '$scope.description'
				}
			})
			.then(function(response) {
				$rootScope.$emit("callGetClass", {});
					$("form")[0].reset();
					console.log("it works");
				},
				function(response) {
					console.log("failed");
					console.log(response);
				});
				
		if (prereq != 0) {
			url = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Pre/" +
			classCode + '/' + prereq;
			console.log(url);
			$http({
					url: url,
					method: "POST",
					data: {
						'course': 'classCode',
						'prerequisite': 'prereq'
					}
				})
				.then(function(response) {
					$rootScope.$emit("callGetClass", {});
						console.log("prereq works");
					},
					function(response) {
						console.log("failed");
						console.log(response);
			});
		}
		
		if (coreq != 0) {
			url = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Coreq/" +
			classCode + '/' + coreq;
			console.log(url);
			$http({
					url: url,
					method: "POST",
					data: {
						'course': 'classCode',
						'corequisite': 'coreq'
					}
				})
				.then(function(response) {
					$rootScope.$emit("callGetClass", {});
						console.log("coreq works");
					},
					function(response) {
						console.log("failed");
						console.log(response);
			});
		}
		
	}

	$scope.editClass = function() {
		subject = $('#select-subject').val();

		if (subject == "Computer Science") {
			classCode = 'CS ';
		} else if (subject == "Physics") {
			classCode = 'PHYS ';
		} else if (subject == "Math") {
			classCode = 'MATH ';
		} else if (subject == "Electrical Engineering") {
			classCode = 'EE ';
		}
		classCode += $scope.classNum;

		url = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Classes/" +
			subject + '/' + classCode + '/' + $scope.className + '/' + $scope.classUnits + '/' + $scope.description;
		$http({
				url: url,
				method: "PUT",
				data: {
					'subject': 'subject',
					'classCode': 'classCode',
					'className': '$scope.className',
					'unit': '$scope.classUnits',
					'description': '$scope.description'
				}
			})
			.then(function(response) {
					$rootScope.$emit("callGetClass", {});
					$("form")[0].reset();
					console.log("updated");
				},
				function(response) {
					console.log("update failed");
					console.log(response);
				});
	}

	$scope.selectClass = function() {
		var selected = $("#select-edit").val();
		var url = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Classes/classCode/" + selected;
		$http.get(url).then(function(response) {
			console.log("got coreq data from .get");
			$scope.course = angular.fromJson(response.data);

			$scope.subject = $scope.course[0].subject;
			classCode = $scope.course[0].classCode;
			$scope.classNum = classCode.substring(classCode.indexOf(" ") + 1);
			$scope.className = $scope.course[0].className;
			$scope.classUnits = $scope.course[0].unit;
			$scope.description = $scope.course[0].description;

		}, function(response) {
			console.log("something is wrong");
		});
	}
}]);

angular.module('adminPage').filter('unique', function() {
	return function(items, filterOn) {

		if (filterOn === false) {
			return items;
		}

		if ((filterOn || angular.isUndefined(filterOn)) && angular.isArray(items)) {
			var hashCheck = {},
				newItems = [];

			var extractValueToCompare = function(item) {
				if (angular.isObject(item) && angular.isString(filterOn)) {
					return item[filterOn];
				} else {
					return item;
				}
			};

			angular.forEach(items, function(item) {
				var valueToCheck, isDuplicate = false;

				for (var i = 0; i < newItems.length; i++) {
					if (angular.equals(extractValueToCompare(newItems[i]), extractValueToCompare(item))) {
						isDuplicate = true;
						break;
					}
				}
				if (!isDuplicate) {
					newItems.push(item);
				}

			});
			items = newItems;
		}
		return items;
	};
});

$("#clear-btn").click(function(e) {
	e.preventDefault();
	$("form")[0].reset();
});
