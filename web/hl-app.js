
var hidroLiveApp = angular.module('hidroLiveApp', ["components"]);


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

hidroLiveApp.service('DataBase', function () {

    this.contas = function () {
        return [
            {id: 1, nome: "Operação"},
            {id: 2, nome: "Planejamento"},
            {id: 3, nome: "Resultado"}
        ];
    };
});


hidroLiveApp.controller('cadContas', function ($scope, DataBase, serviceCadasrto, $http) {
    $scope.page = serviceCadasrto.getPage();
    $scope.page.ajustarReferenciasRegistro = function (rec) {
    };
    $scope.page.urlGravar = "./Gravar/cadContasGravar.jsp";
    $scope.page.retrieve("./json/contas.jsp");
});

hidroLiveApp.controller('cadClientes', function ($scope, DataBase, serviceCadasrto, $http) {
    $scope.page = serviceCadasrto.getPage();
    $scope.page.ajustarReferenciasRegistro = function (rec) {
        $scope.page.setRefCidades(rec);
    };
    $scope.page.urlGravar = "./Gravar/cadClientesGravar.jsp";
    $scope.page.retrieveClientes();
});

hidroLiveApp.controller('movContas', function ($scope, serviceCadasrto, $http) {
    $scope.page = serviceCadasrto.getPage();
    $scope.page.recDefault = {
        id: 0,
        dt_movto: '2015-06-15',
        valor: 0,
        observacao: ''
    };
    $scope.page.ajustarReferenciasRegistro = function (rec) {
        $scope.page.setRefContasDebitoCredito(rec);
    };
    $scope.page.urlGravar = "./Gravar/movContasGravar.jsp";
    $scope.page.retrieveMovContas();
});

hidroLiveApp.controller('cadContasReceberPagar', function ($scope, DataBase, serviceCadasrto, $http) {
    $scope.page = serviceCadasrto.getPage();
    $scope.page.recDefault = {
        id: 0,
        dt_emissao: '2015-06-15',
        valor_desconto: 0,
        valor_acrescimo: 0,
        valor_total: 0,
        conta_id: 0,
        movcontas_id: 0,
        observacao: ''
    };
    $scope.page.ajustarReferenciasRegistro = function (rec) {
        $scope.page.setRefClientes(rec);
        $scope.page.setRefTipoMovimento(rec);
        $scope.page.setRefContas(rec);
    };
    $scope.page.urlGravar = "./Gravar/ContasReceberPagarGravar.jsp";
    $scope.page.retrieveContasRP();
});

hidroLiveApp.controller('cadVendas', function ($scope, DataBase, serviceCadasrto, $http) {
    $scope.page = serviceCadasrto.getPage();
    $scope.page.recDefault = {
        id: 0,
        dt_venda: $scope.page.currentDate(),
        contasPagarReceber_id: 0,
        vlr_recebido: "0",
        taxas: 0,
        observacao: ''
    };

    $scope.page.ajustarReferenciasRegistro = function (rec) {
        $scope.page.setRefClientes(rec);
    };
    $scope.page.urlGravar = "./Gravar/gravar.jsp?tabela=vendas";
    $scope.page.retrieveVendas();

    $scope.changeDataVenda = function () {
        $scope.page.recDefault.dt_venda = $scope.page.recEdit.dt_venda;
    };

    $scope.chageCliente = function () {
        $scope.page.recEdit.valor_unidade = $scope.page.recEdit.empresa_id.preco;
        $scope.chageQtde();
    };

    $scope.chageQtde = function () {
        var unidade = $scope.page.tirarFormatacaoNumerica($scope.page.recEdit.valor_unidade);
        var quantidade = $scope.page.tirarFormatacaoNumerica($scope.page.recEdit.quantidade);
        var taxas = $scope.page.tirarFormatacaoNumerica($scope.page.recEdit.empresa_id.taxas);
        var taxas_calc;
        var total = unidade * quantidade;
        taxas_calc = (total * taxas) / 100.0;
        total = total - taxas_calc;
        $scope.calculo = (unidade + ' * ' + quantidade + ' - ' + taxas_calc + ' = ' + total);
        $scope.page.recEdit.taxas = $scope.page.poeFormatacaoNumerica(taxas_calc);
        $scope.page.recEdit.total = $scope.page.poeFormatacaoNumerica(total);
    };

});

hidroLiveApp.controller('cadCidades', function ($scope, DataBase, serviceCadasrto, $http) {
    $scope.page = serviceCadasrto.getPage();
    $scope.page.ajustarReferenciasRegistro = function (rec) {
    };
    $scope.page.urlGravar = "./Gravar/cadCidadesGravar.jsp";
    $scope.page.retrieveCidades();
});

hidroLiveApp.controller('relContasRP', function ($scope, serviceCadasrto, $http) {
    $scope.dados = {
        "dtInicio": "2015-06-01",
        "dtFim": "2015-06-30",
        "selecaoCliente": false,
        "empresa_id": 0
    };

    $scope.atualizaRelatorio = function () {
        $scope.page.retrieveExtratoRP($scope.dados);
    };

    $scope.page = serviceCadasrto.getPage();
    $scope.atualizaRelatorio();
});

hidroLiveApp.controller('ctrlHome', function ($scope) {

});
