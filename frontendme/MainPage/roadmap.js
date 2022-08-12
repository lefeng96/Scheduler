var classes = [];
var algrithm = [];
var currentdate = new Date();
var years = currentdate.getFullYear();
//var cin = 123456789;
var cin = getCookie("cin");
var urlStudent = "http://138.197.211.227:8080/RESTfulProject/REST/WebService/Students/cin/" + cin;
var url;
var sidebarcontrol = false;
var editing;
var app = angular.module('myApp', ['ngSanitize']);
var ready = false;


app.controller('sideCtrl', function($scope, $http) {
	$http.get(urlStudent).then(function(response) {
		$scope.students = response.data;
	});
});


Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val) return i;
	}
	return -1;
};

Array.prototype.remove = function(val) {
        var index = this.indexOf(val);
            if (index > -1) {
                this.splice(index, 1);
        }
};

function loading() {
	document.getElementById("loader").style.display = "block"; 
}

function stoploading() {
	document.getElementById("loader").style.display = "none"; 
}

//sidebar control
function toggleSidebar(){
	document.getElementById("class-sidebar").classList.toggle('active');
};

function loadClass() {
	var classlist = document.getElementById("ul");
	var childs = classlist.childNodes;
	for(var i = childs.length - 1; i >= 0; i--) {
	classlist.removeChild(childs[i]);
	}
	for (var i = 0; i < algrithm.length; i++){
		if (algrithm[i].semesterCode == editing){
			classes = algrithm[i].availableCourses;
		}
	}
	for (var i = 0; i < classes.length; i++) {
		var node = document.createElement("li");
		node.setAttribute("title", classes[i]);
		node.setAttribute("id", classes[i]);
		node.setAttribute("draggable", "true");
        node.setAttribute("ondragstart", "drag(event)");
		if(classes[i].includes("CS")){
					node.setAttribute("class", "CS");
				}
				else if(classes[i].includes("PHYS")){
					node.setAttribute("class", "PHYS");
				}
				else if(classes[i].includes("MATH")){
					node.setAttribute("class", "MATH");
				}
				else{
					node.setAttribute("class", "GE");
				}
		var textnode = document.createTextNode(classes[i]);
		node.appendChild(textnode);
		document.getElementById("ul").appendChild(node);
	}
};

function edit(){

};

$(function() {

    var $sidebar   = $("#sidebar"), 
	    $sidebar2  = $("#sidebar-container"),
        $window    = $(window),
        offset     = $sidebar.offset(),
        topPadding = 15;

    $window.scroll(function() {
        if ($window.scrollTop() > offset.top) {
            $sidebar.stop().animate({
                marginTop: $window.scrollTop() - offset.top + topPadding
            });
			$sidebar2.stop().animate({
                marginTop: $window.scrollTop() - offset.top + topPadding
            });
        } else {
            $sidebar.stop().animate({
                marginTop: 0
            });
			$sidebar2.stop().animate({
                marginTop: 0
            });
        }
    });
    
});

//Class tables control


app.controller("readjson", function($scope, $http) {
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

function changemode(){
	var x = document.getElementById("changemode").value;
	if(x == "1"){
		showallyears();
	}
	else if(x == "2"){
		showoneyear();
	}
}

function showoneyear(){
	var classlist = document.getElementById("wrap");
	var childs = classlist.childNodes;
	for(var i = childs.length - 1; i >= 0; i--) {
		if(childs[i].id != "changemode"&&childs[i].id != "random-btn"){
			classlist.removeChild(childs[i]);
		}
	}
	for (var i = 0; i < algrithm.length; i++){
		if (algrithm[i].semesterCode.includes(years))
		{
			var divnode = document.createElement("div");
			divnode.setAttribute("id", algrithm[i].semesterCode + " div");
			divnode.setAttribute("ondrop","drop(event)");
			divnode.setAttribute("ondragover","allowDrop(event)");
			divnode.setAttribute("class", "semesterDiv");

			var tablenode = document.createElement("table");
			tablenode.setAttribute("align", "center");
			tablenode.setAttribute("border", "6");
			tablenode.setAttribute("id", algrithm[i].semesterCode + " table");

			var trnode = document.createElement("tr");
	        var thnode = document.createElement("th");
	        var textnode1 = document.createTextNode(algrithm[i].semesterCode);
	        thnode.setAttribute("onclick", "edittable(event);loadClass()");
	        thnode.setAttribute("id", algrithm[i].semesterCode);
			thnode.setAttribute("class", "title");
			if(algrithm[i].semesterCode == editing){
				thnode.setAttribute("bgColor","red");
			}

		    thnode.appendChild(textnode1);
		    trnode.appendChild(thnode);
		    tablenode.appendChild(trnode);
			divnode.appendChild(tablenode);
			document.getElementById("wrap").appendChild(divnode);

			for (var j = 0; j < algrithm[i].courses.length; j++) {
				var node1 = document.createElement("tr");
				var node2 = document.createElement("td");
				node2.setAttribute("id", algrithm[i].courses[j]);
				node2.setAttribute("title", algrithm[i].courses[j]);
				node2.setAttribute("draggable", "true");
				node2.setAttribute("ondragstart", "drag(event)");
				if(algrithm[i].courses[j].includes("CS")){
					node2.setAttribute("class", "CS");
				}
				else if(algrithm[i].courses[j].includes("PHYS")){
					node2.setAttribute("class", "PHYS");
				}
				else if(algrithm[i].courses[j].includes("MATH")){
					node2.setAttribute("class", "MATH");
				}
				else{
					node2.setAttribute("class", "GE");
				}
				var textnode2 = document.createTextNode(algrithm[i].courses[j]);
				node2.appendChild(textnode2);
				node1.appendChild(node2);
				tablenode.appendChild(node1);
			}
		}
	}
		var previousnode = document.createElement("button");
		var nextnode = document.createElement("button");
		var textnode3 = document.createTextNode("Previous year");
		var textnode4 = document.createTextNode("Next year");

		previousnode.setAttribute("type", "button");
		previousnode.setAttribute("id", "previous");
		previousnode.setAttribute("onclick", "previous()");
		previousnode.setAttribute("ng-click", "loadData()");
		nextnode.setAttribute("type", "button");
		nextnode.setAttribute("id", "next");
		nextnode.setAttribute("onclick", "next()");
		nextnode.setAttribute("ng-click", "loadData()");
		previousnode.appendChild(textnode3);
		nextnode.appendChild(textnode4);
		document.getElementById("wrap").appendChild(previousnode);
		document.getElementById("wrap").appendChild(nextnode);
};

function showallyears(){
	var classlist = document.getElementById("wrap");
	var childs = classlist.childNodes;
	for(var i = childs.length - 1; i >= 0; i--) {
		if(childs[i].id != "changemode"&&childs[i].id != "random-btn"){
			classlist.removeChild(childs[i]);
		}
	}
	for (var i = 0; i < algrithm.length; i++){
			var divnode = document.createElement("div");
			divnode.setAttribute("id", algrithm[i].semesterCode + " div");
			divnode.setAttribute("ondrop","drop(event)");
			divnode.setAttribute("ondragover","allowDrop(event)");
			divnode.setAttribute("class", "semesterDiv");

			var tablenode = document.createElement("table");
			tablenode.setAttribute("align", "center");
			tablenode.setAttribute("border", "6");
			tablenode.setAttribute("id", algrithm[i].semesterCode + " table");

			var trnode = document.createElement("tr");
	        var thnode = document.createElement("th");
	        var textnode1 = document.createTextNode(algrithm[i].semesterCode);
	        thnode.setAttribute("onclick", "edittable(event);loadClass()");
	        thnode.setAttribute("id", algrithm[i].semesterCode);
			thnode.setAttribute("class", "title");
			if(algrithm[i].semesterCode == editing){
				thnode.setAttribute("bgColor","red");
			}

		    thnode.appendChild(textnode1);
		    trnode.appendChild(thnode);
		    tablenode.appendChild(trnode);
			divnode.appendChild(tablenode);
			document.getElementById("wrap").appendChild(divnode);

			for (var j = 0; j < algrithm[i].courses.length; j++) {
				var node1 = document.createElement("tr");
				var node2 = document.createElement("td");
				node2.setAttribute("id", algrithm[i].courses[j]);
				node2.setAttribute("title", algrithm[i].courses[j]);
				node2.setAttribute("draggable", "true");
				node2.setAttribute("ondragstart", "drag(event)");
				if(algrithm[i].courses[j].includes("CS")){
					node2.setAttribute("class", "CS");
				}
				else if(algrithm[i].courses[j].includes("PHYS")){
					node2.setAttribute("class", "PHYS");
				}
				else if(algrithm[i].courses[j].includes("MATH")){
					node2.setAttribute("class", "MATH");
				}
				else{
					node2.setAttribute("class", "GE");
				}
				var textnode2 = document.createTextNode(algrithm[i].courses[j]);
				node2.appendChild(textnode2);
				node1.appendChild(node2);
				tablenode.appendChild(node1);
			}
	}
};

function edittable(semester){
	for (var i = 0; i < document.getElementsByClassName("title").length; i++){
		document.getElementsByClassName("title")[i].bgColor = "transparent";
	}
	document.getElementById(semester.target.id).bgColor = "red";
	editing = semester.target.id;
};


//drag and drop control
function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
	ev.dataTransfer.setData("From", ev.target.parentNode.parentNode.id);
}
  
function drop(ev) {
	ev.preventDefault();
	var from = ev.dataTransfer.getData("From");
	if(ev.currentTarget.id == editing+" div" && from == "class-sidebar"){
		var data = ev.dataTransfer.getData("text");
		classes.remove(data);
		var res = editing.split(" ");
		var classCode = data;
		var semester = res[0];
		var year = res[1];
		loading();
//		var link = '192.241.199.28:8080/RESTfulProject/REST/WebService/Algorithm/'+cin+'/'+semester+'/'+year+'/'+classCode;
		angular.element(document.getElementById('wrap')).scope().addClass(semester,year,classCode);
//		for (var i = 0; i < algrithm.length; i++){
//			var semesterCode = algrithm[i].semesterCode;
//			if(semesterCode == editing){
//				algrithm[i].courses.push(data);
//				algrithm[i].courses.sort();
//			}
//		}
//		loadClass();
//		changemode();
	}
	else if(ev.currentTarget.id == "ul" && from == editing+" table"){
		var data = ev.dataTransfer.getData("text");
		var res = editing.split(" ");
		$.ajax({
			url: '192.241.199.28:8080/RESTfulProject/REST/WebService/Paths/'+cin+'/'+data+'/'+res[0]+'/'+res[1],
			type: 'DELETE',
			success: function(response) {
				alert('updated');
			}
		});
		for (var i = 0; i < algrithm.length; i++){
			var semesterCode = algrithm[i].semesterCode;
			if(semesterCode == editing){
				algrithm[i].courses.remove(data);
			}
		}
		classes.push(data);
		classes.sort();
		loadClass();
		changemode();
	}
}
 
 
//botton control
function previous(){
  years = years - 1;
  showoneyear();
}
 
function next(){
  years = years +1;
  showoneyear();
}