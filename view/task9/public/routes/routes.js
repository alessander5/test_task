module.config(
    function($routeProvider){
        $routeProvider
            .when('/:section?',{
                templateUrl: 'routes/notes/notes.html',
                controller: 'NotesController'
            })
            .when('/',{
                templateUrl: 'routes/notes/notes.html',
                controller: 'NotesController'
            })
            .when('/section/:name', {
                templateUrl: 'routes/viewSection/viewSection.html',
                controller: 'ViewSectionController'
            })
            .otherwise( {redirectTo: '/'} );
    }
);



module.config(['$locationProvider', function($locationProvider) {
  $locationProvider.hashPrefix('');
}]);
