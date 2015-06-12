
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
                when('/cadCidades', {
                    templateUrl: 'templates/cadCidades.jsp',
                    controller: 'cadCidades'
                }).
                when('/cadVendas', {
                    templateUrl: 'templates/cadVendas.jsp',
                    controller: 'cadVendas'
                }).
                when('/cadContasReceberPagar', {
                    templateUrl: 'templates/cadContasReceberPagar.jsp',
                    controller: 'cadContasReceberPagar'
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
        recDefault: {id: 0},
        actionNovo: function () {
            page.recEdit = angular.copy(page.recDefault);
            page.editar = true;
        },
        actionEditar: function (id) {
            page.recEdit = angular.copy(this.actionGet(id));
            page.ajustarReferenciasRegistro(page.recEdit);
            page.editar = true;
        },
        actionGet: function (id) {
            for (var i in page.registro) {
                if (page.registro[i].id === id) {
                    return page.registro[i];
                }
            }
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
            });
        },
        retrieveCidades: function () {
            $http.post('./json/cidades.jsp').success(function (data, status) {
                page.registro = data;
            });
        },
        setRefTipoMovimento: function (rec) {
            if (rec.tipo_movimento_id > 0 || rec.tipo_movimento_id.id > 0) {
                for (var j in page.tipo_movimento) {
                    if (page.tipo_movimento[j].id === rec.tipo_movimento_id || page.tipo_movimento[j].id === rec.tipo_movimento_id.id) {
                        rec.tipo_movimento_id = page.tipo_movimento[j];
                        break;
                    }
                }
            }
        },
        setRefCidades: function (rec) {
            if (rec.cidade_id > 0 || rec.cidade_id.id > 0) {
                for (var j in page.cidades) {
                    if (page.cidades[j].id === rec.cidade_id || page.cidades[j].id === rec.cidade_id.id) {
                        rec.cidade_id = page.cidades[j];
                        break;
                    }
                }
            }
        },
        setRefClientes: function (rec) {
            if (rec.empresa_id > 0 || rec.empresa_id.id > 0) {
                for (var j in page.clientes) {
                    if (page.clientes[j].id === rec.empresa_id || page.clientes[j].id === rec.empresa_id.id) {
                        rec.empresa_id = page.clientes[j];
                        break;
                    }
                }
            }
        },
        ajustarReferenciasRegistro: function (rec) {
        },
        ajustarReferenciasTodas: function () {
            for (var i in page.registro) {
                page.ajustarReferenciasRegistro(page.registro[i]);
            }
        },
        retrieveClientes: function () {
            $http.post('./json/clientes.jsp').success(function (data, status) {
                page.cidades = data.cidades;
                page.registro = data.clientes;
                page.ajustarReferenciasTodas();
            });
        },
        retrieveContasRP: function () {
            $http.post('./json/ContasReceberPagar.jsp').success(function (data, status) {
                page.clientes = data.clientes;
                page.registro = data.contasReceberPagar;
                page.ajustarReferenciasTodas();
            });
        },
        retrieveVendas: function () {
            $http.post('./json/vendas.jsp').success(function (data, status) {
                page.clientes = data.clientes;
                page.registro = data.vendas;
                page.ajustarReferenciasTodas();
            });
        }
    };
    this.setData = function (rec) {
        page.registro = rec;
    };

    this.getPage = function () {
        return page;
    }
    ;
});

sampleApp.controller('cadContas', function ($scope, DataBase, serviceCadasrto, $http) {
    $scope.page = serviceCadasrto.getPage();
    $scope.page.ajustarReferenciasRegistro = function (rec) {
    };
    $scope.page.urlGravar = "./Gravar/cadContasGravar.jsp";
    $scope.page.retrieve("./json/contas.jsp");
});

sampleApp.controller('cadClientes', function ($scope, DataBase, serviceCadasrto, $http) {
    $scope.page = serviceCadasrto.getPage();
    $scope.page.ajustarReferenciasRegistro = function (rec) {
        $scope.page.setRefCidades(rec);
    };
    $scope.page.urlGravar = "./Gravar/cadClientesGravar.jsp";
    $scope.page.retrieveClientes();
});

sampleApp.controller('cadContasReceberPagar', function ($scope, DataBase, serviceCadasrto, $http) {
    $scope.page = serviceCadasrto.getPage();
    $scope.page.tipo_movimento = [
        {id: 1, "nome": "Contas a Receber"},
        {id: 2, "nome": "Contas a Pagar"}
    ];
    $scope.page.recDefault = {
        id: 0, 
        dt_emissao: '10/06/2015',
        valor_desconto: 0, 
        valor_acrescimo: 0, 
        valor_total: 0, 
        observacao: ''
    };
    $scope.page.ajustarReferenciasRegistro = function (rec) {
        $scope.page.setRefClientes(rec);
        $scope.page.setRefTipoMovimento(rec);
    };
    $scope.page.urlGravar = "./Gravar/ContasReceberPagarGravar.jsp";
    $scope.page.retrieveContasRP();
});

sampleApp.controller('cadVendas', function ($scope, DataBase, serviceCadasrto, $http) {
    $scope.page = serviceCadasrto.getPage();
    $scope.page.recDefault = {
        id: 0, 
        dt_venda: '10/06/2015',
        contasPagarReceber_id: 0, 
        taxas: 0, 
        observacao: ''
    };
    $scope.page.ajustarReferenciasRegistro = function (rec) {
        $scope.page.setRefClientes(rec);
    };
    $scope.page.urlGravar = "./Gravar/vendasGravar.jsp";
    $scope.page.retrieveVendas();
    
    $scope.chageCliente = function(){
        $scope.page.recEdit.valor_unidade = $scope.page.recEdit.empresa_id.preco;
        $scope.chageQtde();
    }
    
    $scope.chageQtde = function(){
        var total = $scope.page.recEdit.valor_unidade * $scope.page.recEdit.quantidade;
        $scope.page.recEdit.taxas = (total * $scope.page.recEdit.empresa_id.taxas) / 100.0;
        $scope.page.recEdit.total = total - $scope.page.recEdit.taxas;
    }
    
});

sampleApp.controller('cadCidades', function ($scope, DataBase, serviceCadasrto, $http) {
    $scope.page = serviceCadasrto.getPage();
    $scope.page.ajustarReferenciasRegistro = function (rec) {
    };
    $scope.page.urlGravar = "./Gravar/cadCidadesGravar.jsp";
    $scope.page.retrieveCidades();
});

sampleApp.controller('ShowOrdersController', function ($scope) {

    $scope.message = 'This is Show orders screen';
});
