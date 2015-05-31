<%@page import="hl.AngularJS.TableColuna"%>
<%@page import="hl.AngularJS.Table"%>
<%@page import="hl.AngularJS.formFieldCheckBox"%>
<%@page import="hl.AngularJS.formFieldSelect"%>
<%@page import="hl.AngularJS.formFieldText"%>
<%@page import="hl.AngularJS.formButton"%>
<%@page import="hl.AngularJS.form"%>

<html>
    <head>
        <%
            form formulario = new form();

            formulario.htmlHeader(out, "Cadastro de contas");

            formulario.setTitulo("Contas");
            formulario.addFieldObject(new formFieldText("Codigo:", "id"));
            formulario.addFieldObject(new formFieldText("Nome:", "nome"));
            formulario.addButtonObject(new formButton("Gravar", "Gravar()"));
            formulario.addButtonObject(new formButton("Voltar", "Voltar()"));

            Table tabela = new Table("lista");
            tabela.addColuna(new TableColuna("id", "Código"));
            tabela.addColuna(new TableColuna("nome", "Nome da Conta"));
        %>
        <script>
            var mainApp = angular.module("mainApp", []);

            mainApp.controller('cadcontas', function ($scope, $http) {
                $scope.lista = [];
                $scope.editando = false;

            <%
                out.print(formulario.functionCarregaJson("/hl04/json/contas.jsp", "$scope.lista"));
                out.print(formulario.functionVoltar());
                out.print(formulario.functionEditar());
                out.print(formulario.functionGravar("/hl04/cadContasGravar.jsp"));
            %>

            });

        </script>
    </head>
    <body>
        <section id="tables">

            <div ng-app="mainApp" ng-controller="cadcontas" >
                <div class="container">

                    


                    {{lista2}} - {{resultado}}
                    <div class="row" ng-hide="!editando">
                        <div class="col-lg-8">
                            <%
                                formulario.buildForm(out);
                            %>
                        </div>
                    </div>

                    <div class="row" ng-hide="editando">
                        <div class="col-lg-8">
                            <%
                                tabela.buildTable(out);
                            %>
                        </div>
                    </div>
                </div>
            </div>

        </section>

    </body>
</html>
