var app = angular.module("Kinopoisk", [])

app.controller("KinopoiskController", function($scope, $http) {

    $scope.movies = [];

    $http.get('http://localhost:8490/kinopoisk/top').then(function(response){
        $scope.movies = response.data;
    });

});

app.controller("KinopoiskFilterController", function($scope, $http) {

   $scope.movies = [];
   $scope.selectedYear = null;
   $scope.selectedAmount = 0;

   $scope.executeSearch = function(){
        var year = $scope.selectedYear;
        var amount = $scope.selectedAmount;

        $http.get('http://localhost:8490/kinopoisk/top/' + amount + '/of' + year).then(function(response){
            $scope.movies = response.data;
        });
   };
});


app.controller("KinopoiskHomeController", function($scope){

    $scope.redirectToTop = function(){

    };
});