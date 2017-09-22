module.controller("LoginController", function($scope, $location, $timeout, $route, UserService){
    $scope.loggedIn = UserService.loggedIn;
    $scope.login = function() {
        UserService.login($scope.username, $scope.password);
    }

    $scope.login = function() {
        UserService.login($scope.username, $scope.password)
            .then(
                function() {
                    $scope.loggedIn = true;
                    $location.path("/");
                    $route.reload();
                },
                function() {
                    $scope.wrongPassword = true;
                    $timeout(function() {
                        $scope.wrongPassword = false;
                    }, 1000);
                }
            );
    }

    $scope.logout = function() {
        UserService.logout().then(function() {
            $scope.loggedIn = false;
            $location.path("/");
            $route.reload();
        });
    }

});