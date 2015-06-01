<%@page import="hl.AngularJS.formFieldCheckBox"%>
<%@page import="hl.AngularJS.formFieldSelect"%>
<%@page import="hl.AngularJS.formFieldText"%>
<%@page import="hl.AngularJS.formButton"%>
<%@page import="hl.AngularJS.form"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet">
        <script src="js/angular.min.js" type="text/javascript"></script>

        <%
            form formulario = new form();
            formulario.setTitulo("Contas a Receber");
            formulario.addFieldObject(new formFieldText("Data:", "reg_data_recebimento"));
            formulario.addFieldObject(new formFieldSelect("Conta:", "reg_conta", "contas", "id", "nome"));
            formulario.addFieldObject(new formFieldText("Valor:", "reg_valor"));
            formulario.addFieldObject(new formFieldText("Observação:", "reg_observacao"));
            formulario.addFieldObject(new formFieldCheckBox("Confirmado:", "reg_confirmacao", "Confirmação se o recebimento foi efetuado.", "1"));
            formulario.addButtonSubmit("Gravar");
            formulario.addButtonReset("Voltar");
            formulario.addButtonObject(new formButton("Teste", "teste()"));
            formulario.addButtonObject(new formButton("Teste 2", "teste2()"));
        %>
        <script>
            var mainApp = angular.module("mainApp", []);

            mainApp.controller('studentController', function ($scope, $http) {
                $scope.lista = [];
                $scope.contas = [];

                $http.post("/hl/testeResultSetJson.jsp").success(function (data, status) {
                    $scope.lista = data;
                });


                $http.post("/hl/json/contas.jsp").success(function (data, status) {
                    $scope.contas = data;
                    $scope.contaForm = $scope.contas[1];
                });
                
                $scope.teste2 = function () {
                    $scope.reg_conta = $scope.contas[0];
                };
                

                $scope.teste = function () {
                <%
                    out.print(formulario.getJsonData("$scope.lista2"));
                %>
                };

           });

            //testex();
        </script>
    </head>
    <body>
        <section id="tables">
            <div class="page-header">
            </div>
            <div ng-app="mainApp" ng-controller="studentController" >
                <div class="row">
                    <div class="col-lg-8">
                        {{lista2}}<br>
                        {{reg_data_recebimento}}
                        <%
                            formulario.buildForm(out);
                        %>
                    </div>
                </div>

            </div>

        </section>

    </body>
</html>
