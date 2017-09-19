var module = angular.module('myapp', []);
module.controller('NotesController', function($scope, $http) {
    $scope.notes = [];

    var update = function(){
        $http.get("/notes")
            .then(function(notes){
                $scope.notes = notes.data;
            })

    }
    update();

});