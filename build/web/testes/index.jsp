<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet">
        <script src="js/angular.min.js" type="text/javascript"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <title> titulo </title>
    </head>
    <body  ng-app="HidroLive">
        <div class="container">
            <div class="row">
                <section id="tables">
                    <div class="page-header">
                        <nav class="navbar" role="navigation">
                            <!-- Brand and toggle get grouped for better mobile display -->
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                                <a class="navbar-brand" href="#">Title 1</a>
                            </div>

                            <!-- Collect the nav links, forms, and other content for toggling -->
                            <div class="collapse navbar-collapse navbar-ex1-collapse">
                                <ul class="nav navbar-nav">
                                    <li class="active"><a href="#">Home</a></li>
                                    <li><a href="#">Link</a></li>
                                    <li><a href="#">Link</a></li>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cadastros <b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="#CadContas">Contas</a></li>
                                            <li><a href="cadClientes.jsp">Clientes</a></li>
                                        </ul>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Relatorios<b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="cadContas.jsp">Contas</a></li>
                                            <li><a href="cadClientes.jsp">Clientes</a></li>
                                        </ul>
                                    </li>
                                </ul>
                                <ul class="nav navbar-nav navbar-right">
                                    <li><a href="#">Link</a></li>
                                </ul>
                            </div><!-- /.navbar-collapse -->
                        </nav>
                    </div>
                    <div class="col-md-9">
                        <div ng-view></div>
                    </div>
                </section>

            </div>

        </div><!-- /.container -->

        <script>

            var Aplicacao = angular.module('HidroLive', []);

            Aplicacao.config(['$routeProvider',
                function ($routeProvider) {
                    $routeProvider.
                            when('/CadContas', {
                                templateUrl: 'templates/cadContas.jsp',
                                controller: 'cadcontas'
                            }).
                            when('/ShowOrders', {
                                templateUrl: 'templates/show_orders.html',
                                controller: 'ShowOrdersController'
                            }).
                            otherwise({
                                redirectTo: '/AddNewOrder'
                            });
                }]);


            Aplicacao.controller('AddOrderController', function ($scope) {

                $scope.message = 'This is Add new order screen';

            });


            Aplicacao.controller('ShowOrdersController', function ($scope) {

                $scope.message = 'This is Show orders screen';

            });
            
            Aplicacao.controller('cadcontas', function ($scope, $http) {

            });
            
        </script>

    </body>
</html>
