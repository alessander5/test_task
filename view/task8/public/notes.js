var module = angular.module('myapp', []);
module.controller('NotesController', function($scope, $http) {
    $scope.activeSection = null;

    var readSections = function(){
            $http.get("/sections")
                .then(function(sections){
                    $scope.sections = sections.data;
                    update();
                    if($scope.activeSection == null && $scope.sections.length>0){
                        $scope.activeSection = $scope.sections[0].title;
                    }

                });
        }
    readSections();

    $scope.showSection = function(section){
        $scope.activeSection = section.title;
        update();
    }

    var update = function(){
        var params = {params:{section:$scope.activeSection}};
        $http.get("/notes", params)
        .then(
            function(notes){
                $scope.notes = notes.data;
            }
        );
    }



////

    $scope.notes = [];
    $scope.order = "order";

    /*var update = function(){
        $http.get("/notes", {params:{order:$scope.order}})
            .then(function(notes){
                $scope.notes = notes.data;
            })

    }
    update();*/

    $scope.add = function(){
        var note = {text : $scope.text, date: new Date().getTime(), order: 0};
        $http.post("/notes", note)
            .then(function(){
                $scope.text = "";
                update();
            });
    };

    $scope.update = function(id, updateText){
        $http.put("/notes", {params:{id:id, text:updateText}})
            .then(function(notes){
                update();
            });
    }

    $scope.remove = function(id){
        $http.delete("/notes", {params:{id:id}})
            .then(function(notes){
                update();
            });
    };

    $scope.setOnTop = function(text){
        $http.get("/notes/setOnTop", {params:{text:text}})
            .then(function(notes){
                update();
            });
    };

    /*$scope.$watch("order", function(){
        update();
    });*/

    ////
});