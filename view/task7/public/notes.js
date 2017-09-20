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

    $scope.add = function(){
        var note = {text : $scope.text};
        $http.post("/notes", note)
            .then(function(){
                $scope.text = "";
                update();
            });
    };

    $scope.remove = function(id){
        $http.delete("/notes", {params:{id:id}})
            .then(function(notes){
                update();
            });
    };

});