var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope) {
    $scope.greeting = "";
    $scope.update = function(){
        if($scope.name){
            $scope.greeting = "Hello, " + $scope.name+"!";
        }
    }
});