
hidroLiveApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
                when('/home', {
                    templateUrl: 'home.html',
                    controller: 'ctrlHome'
                }).
                when('/cadContas', {
                    templateUrl: 'templates/cadContas.jsp',
                    controller: 'cadContas'
                }).
                when('/movContas', {
                    templateUrl: 'templates/movContas.jsp',
                    controller: 'movContas'
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
                when('/relContasRP', {
                    templateUrl: 'templates/relContasRP.html',
                    controller: 'relContasRP'
                }).
                otherwise({
                    redirectTo: '/home'
                });
    }]);