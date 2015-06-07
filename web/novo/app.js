
var sampleApp = angular.module('sampleApp', ["components"]);
sampleApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
                when('/home', {
                    templateUrl: 'home.html',
                    controller: 'AddOrderController'
                }).
                when('/AddNewOrder', {
                    templateUrl: 'add_order.html',
                    controller: 'AddOrderController'
                }).
                when('/ShowOrders', {
                    templateUrl: 'show_orders.html',
                    controller: 'ShowOrdersController'
                }).
                when('/cadContas', {
                    templateUrl: 'templates/cadContas.jsp',
                    controller: 'cadContas'
                }).
                when('/cadClientes', {
                    templateUrl: 'templates/cadClientes.jsp',
                    controller: 'cadClientes'
                }).
                otherwise({
                    redirectTo: '/AddNewOrder'
                });
    }]);

angular.module("components", [])
        .directive("edicao", function () {
            return {
                restrict: "E",
                transclude: false,
                scope: {
                    label: "@label",
                    coluna: "@coluna",
                    campo: "=campo"
                },
                controller: function ($scope, $element) {
                },
                templateUrl: "./componentes/edicao.html"
            };
        });

sampleApp.controller('AddOrderController', function ($scope) {
});

sampleApp.service('DataBase', function () {

    this.contas = function () {
        return [
            {id: 1, nome: "Operação"},
            {id: 2, nome: "Planejamento"},
            {id: 3, nome: "Resultado"}
        ];
    };
});

sampleApp.service('serviceCadasrto', function ($http) {

    var page = {
        urlGravar: "",
        editar: false,
        registro: {},
        uid: 1,
        actionNovo: function () {
            page.recEdit = {id: 0};
            page.editar = true;
        },
        actionEditar: function (id) {
            page.recEdit = angular.copy(this.actionGet(id));
            page.editar = true;
        },
        actionGet: function (id) {
            for (var i in page.registro) {
                if (page.registro[i].id === id) {
                    return page.registro[i];
                }
            }
        },
        calcMaxId: function () {
            var maior = 0;
            for (var i in page.registro) {
                if (maior < page.registro[i].id) {
                    maior = page.registro[i].id;
                }
            }
            this.uid = maior + 1;
        },
        actionSave: function () {
            page.saveDB(page.urlGravar);
            if (page.recEdit.id === "0" || page.recEdit.id === 0) {
                page.recEdit.id = page.uid++;
                page.recEdit.id = 0;
                page.registro.push(page.recEdit);
            } else {
                for (var i in page.registro) {
                    if (page.registro[i].id === page.recEdit.id) {
                        page.registro[i] = page.recEdit;
                    }
                }
            }
            page.recEdit = {};
            this.editar = false;
        },
        actionVoltar: function () {
            page.recEdit = {};
            this.editar = false;
        },
        actionDelete: function (id) {

            for (var i in page.registro) {
                if (page.registro[i].id === id) {
                    page.registro.splice(i, 1);
                }
            }
            if (page.recEdit.id === id) {
                page.recEdit = {};
            }
        },
        saveDB: function (url) {
            var idAntes = page.recEdit.id;
            $http.post(url, page.recEdit).success(function (data, status) {
                page.resultado = data;

                for (var i in page.registro) {
                    if (page.registro[i].id === idAntes) {
                        page.registro[i] = data.rec;
                    }
                }
            });
        },
        retrieve: function (url) {
            $http.post(url).success(function (data, status) {
                page.registro = data;
                page.calcMaxId();
            });
        }
    };
    this.setData = function (rec) {
        page.registro = rec;
        page.calcMaxId();
    };

    this.getPage = function () {
        return page;
    };

});

sampleApp.controller('cadContas', function ($scope, DataBase, serviceCadasrto, $http) {
    $scope.page = serviceCadasrto.getPage();
    $scope.page.urlGravar = "/hl/cadContasGravar.jsp";
    $scope.page.retrieve("/hl/json/contas.jsp");
});

sampleApp.controller('cadClientes', function ($scope, DataBase, serviceCadasrto, $http) {
    $scope.page = serviceCadasrto.getPage();
    $scope.page.urlGravar = "/hl/cadClientesGravar.jsp";
    $scope.page.retrieve("/hl/json/clientes.jsp");
});

sampleApp.controller('ShowOrdersController', function ($scope) {

    $scope.message = 'This is Show orders screen';
});
