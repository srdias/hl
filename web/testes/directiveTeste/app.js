
var contactsApp = new angular.module("contactsApp", ["components"]);

angular.module("components", [])
        .directive("editcontent", function () {
            return {
                restrict: "E",
                transclude: false,
                scope: {
                    registro: "=contato",
                    saveAction: "=save",
                    snome: "=sobrenome",
                    cidades: "=cidades",
                    edicao: "=edicao"
                },
                controller: function ($scope, $element) {
                    $scope.EditarEste=false;
                    $scope.save = function () {
                        $scope.registro.firstName = 'Diretiva'
                        $scope.saveAction($scope.registro);
                    };
                    $scope.Editar = function () {
                        $scope.EditarEste=!$scope.EditarEste;
                    };
                },
                templateUrl: "./modelo.html"
            };
        });

contactsApp.controller("contactsController",
        function ($scope) {

            $scope.edicao = true;

            $scope.funcaoControllerHtml = function (rec) {
                rec.firstName = rec.firstName + ' Controle';
            };


            $scope.cidades =
                    [
                        {Cidade: "Criciuma", Estado: "SC"},
                        {Cidade: "Porto Alegre", Estado: "RS"},
                        {Cidade: "Florianopolis", Estado: "SC"},
                        {Cidade: "Curitiba", Estado: "PR"},
                        {Cidade: "Florianopolis", Estado: "SC"},
                        {Cidade: "São Paulo", Estado: "SP"}
                    ];

            $scope.contacts =
                    [
                        {
                            firstName: "John",
                            lastName: "Lennon",
                            address: "123 Strawberry Fields",
                            city: "Forever",
                            state: "UK",
                            zip: 12344,
                            homePhone: 2121112221,
                            cellPhone: 2121112222,
                            workPhone: 2121112223,
                            cidade: $scope.cidades[1]
                        },
                        {
                            firstName: "Paul",
                            lastName: "McCartney",
                            address: "456 Penny Lane",
                            city: "London",
                            state: "UK",
                            zip: 55423,
                            homePhone: 2122222221,
                            cellPhone: 2122222222,
                            workPhone: 2122222223,
                            cidade: $scope.cidades[2]
                        },
                        {
                            firstName: "George",
                            lastName: "Harrison",
                            address: "141 Something",
                            city: "London",
                            state: "UK",
                            zip: 55627,
                            homePhone: 2123332221,
                            cellPhone: 2123332222,
                            workPhone: 2123332223,
                            cidade: $scope.cidades[3]
                        },
                        {
                            firstName: "Ringo",
                            lastName: "Starr",
                            address: "1669 Octopus' Garden",
                            city: "New York",
                            state: "NY",
                            zip: 12345,
                            homePhone: 2124442221,
                            cellPhone: 2124442222,
                            workPhone: 2124442223,
                            cidade: $scope.cidades[4]
                        }
                    ];
        }
);
