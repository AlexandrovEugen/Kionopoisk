var app = angular.module("Kinopoisk", [])

app.controller("KinopoiskController", function($scope, $http) {

    $scope.movies = [];

    $http.get('http://localhost:8490/kinopoisk/top').then(function(response){
        $scope.movies = response.data;
    });

});