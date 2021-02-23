var home;

(function () {
    
    home = angular.module('home', []);
    home.controller('CalendarioCtrl', function ($scope, $http) {
        

        $scope.pesquisa = function (contato) {

            $http({
                url: '/Calendario/pesquisaParametro',
                method: "POST",
                data: { 'formaPesq': contato.valor, 'textPesq': contato.textopesq }
            })
            .then(function (response) {
                $scope.contatos = [{}];
                
                //$scope.contatos.push(response);
                $scope.contatos = response.data;

                console.log(response)
                console.log($scope.contatos)

				delete $scope.response;
            },
            function (response) { // optional
                
            });
            

        };
    });
    

})();
