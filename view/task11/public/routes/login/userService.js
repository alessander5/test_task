module.factory("UserService", function($http, $rootScope, $timeout, $q) {
    var service = {};
    service.userName = "";
    service.loggedIn = false;

    service.login = function(login, password) {
        var deferred = $q.defer();
        $http.post("/login", {login:login, password:password})
            .then(function(res) {
                res=res.data;
                if (res) {
                    service.loggedIn = true;
                    service.userName = login;
                    deferred.resolve("logged in");
                } else {
                    deferred.reject("wrong username/password");
                    $rootScope.wrongPassword = true;
                    $timeout(function() {
                            $rootScope.wrongPassword = false;
                        }, 1000);
                }
            });
        return deferred.promise;
    }

    service.logout = function() {
        return $http.get("/logout");
    }


    return service;
});