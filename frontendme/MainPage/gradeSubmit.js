  var app = angular.module('myApp', ['ngSanitize']);
  var cin = getCookie("cin");
  var urlStudent = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Students/cin/" + cin;
  var urlPath = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Paths/" + cin;
  var urlClass = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Classes/";
  
  function upperCaseF(a){
    setTimeout(function(){
        a.value = a.value.toUpperCase();
    }, 1);
}
  app.controller('sideCtrl', function($scope, $http) {
    $http.get(urlStudent).then(function(response) {
      $scope.students = response.data;
    });
  });
  app.controller('inputCtrl', ['$scope', '$http', '$rootScope', function($scope, $http, $rootScope) {
   $http.get(urlClass).then(function(response) {
      $scope.allClasses = angular.fromJson(response.data);
    });
	 
    angular.element("#submit").click(function(e) {
      e.preventDefault();
      var semester = angular.element("#semester").val();
      var year = angular.element("#year").val();
      var grades = angular.element("#grades").val();
      console.log(year);
      var classCode = angular.element("#subject").val();
      var insert = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Paths/" + cin + "/" + classCode + "/" + semester + "/" + year + "/" + grades;
      console.log(insert);
      $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
      $http({
          url: insert,
          method: "POST",
          data: {
            'sid': "cin"
          }
        })
        .then(function(response) {
            angular.element("#gradesForm")[0].reset();
            $rootScope.$emit("callGetPaths", {});
          },
          function(response) {
            // alert("Unsuccessful");
          });

    });
        angular.element("#edit").click(function(e) {
      e.preventDefault();
      var semester = angular.element("#semester").val();
      var year = angular.element("#year").val();
      var grades = angular.element("#grades").val();
      console.log(year);
      var classCode = angular.element("#subject").val();
      var insert = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Paths/" + cin + "/" + classCode + "/" + semester + "/" + year + "/" + grades;
      console.log(insert);
      $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
      $http({
          url: insert,
          method: "PUT",
          data: {
            'sid': "cin"
          }
        })
        .then(function(response) {
            angular.element("#gradesForm")[0].reset();
            $rootScope.$emit("callGetPaths", {});
          },
          function(response) {
            // alert("Edit Success.");
            $rootScope.$emit("callGetPaths", {});
          });

    });
  }]);

app.controller('getPaths', ['$scope', '$http' ,'$rootScope', function($scope, $http, $rootScope) {
        $rootScope.$on("callGetPaths", function(){
           $scope.getPaths();
        });
        $rootScope.$on("callDeletePath", function(){
           $scope.getPaths();
        });

  $scope.getPaths = function() {
    $http.get(urlPath).then(function(response) {
      $scope.classes = angular.fromJson(response.data);
    });
  }
	  $scope.deletePath = function(classCode, semester, year) {
      var url = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Paths/" + cin + "/" + classCode + "/" + semester + "/" + year;
      console.log(url);
      $http.delete(url).then(function(response) {
        $scope.getPaths();
      }, function(response) {
        $scope.getPaths();
      });
    }
}]);
