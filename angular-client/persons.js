var personsApp = angular.module('personsApp', []);

personsApp.controller('personsController', function ($scope, $http) {

    $scope.action = "create";

    $scope.load = function () {
        $http({method: 'GET', url: 'http://localhost:9000/person'}).
        then(function (response) {
            $scope.persons = response.data;
        });
    }
    $scope.addPerson = function (firstName, lastName, age ) {
        if ($scope.action == "create") {
            var p = {firstName: firstName, lastName: lastName, age: age};
            $http.post('http://localhost:9000/person', p).then(function (response) {
                $scope.persons.push(p);
                $scope.firstName = "";
                $scope.lastName = "";
                $scope.age = "";
            })
        } else if ($scope.action == "edit") {
            for (var i=0; i<$scope.persons.length; i++) {
                if ($scope.persons[i].id == currentPersonId) {
                    $scope.persons[i].firstName = firstName;
                    $scope.persons[i].lastName = lastName;
                    $scope.persons[i].age = age;
                    $http.put('http://localhost:9000/person', $scope.persons[i]).then(function (response) {
                        $scope.firstName = "";
                        $scope.lastName = "";
                        $scope.age = "";
                        $scope.action = "create";
                    })
                    return;
                }
            }
        }
    }
    var currentPersonId;
    $scope.edit = function (id) {
        for (var i=0; i<$scope.persons.length; i++){
            if ($scope.persons[i].id == id){
                $scope.firstName = $scope.persons[i].firstName;
                $scope.lastName = $scope.persons[i].lastName;
                $scope.age = $scope.persons[i].age;
                $scope.action = "edit";
                currentPersonId = id;
                return;
            }
            $scope.action = "create";
        }
        $scope.action = "create";
    }

    $scope.delete = function (id) {
        for (var i=0; i<$scope.persons.length; i++){
            if ($scope.persons[i].id == id){
                $http.delete('http://localhost:9000/person/' + id).then(function (response) {
                    $scope.persons.splice(i, 1);
                })
               return;
            }
        }
    }
})