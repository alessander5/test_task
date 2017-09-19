var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
    $scope.greeting = "";
    $scope.update = function(){
        if($scope.name){
            $http.get("/greeting",
                {params:
                    {name : $scope.name}
                })
                .then(function(res){
                    $scope.greeting = res.data;
                });
        }
    }

    $scope.$watch("name", function(){
        $scope.update();
    });

    // $interval($scope.update, 200);

});