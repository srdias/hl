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

            formulario.setTitulo("Clientes");
            formulario.addFieldObject(new formFieldText("Codigo:", "id"));
            formulario.addFieldObject(new formFieldText("Nome:", "nome"));
            formulario.addFieldObject(new formFieldText("Endereço:", "endereco"));
            formulario.addFieldObject(new formFieldSelect("Cidade:", "cidade_id", "cidades", "id", "nome"));
            formulario.addFieldObject(new formFieldText("Preço:", "preco"));
            formulario.addFieldObject(new formFieldText("Taxas:", "taxas"));
            formulario.addButtonObject(new formButton("Gravar", "Gravar()"));
            formulario.addButtonObject(new formButton("Voltar", "Voltar()"));

            Table tabela = new Table("lista");
            tabela.addColuna(new TableColuna("id", "Código"));
            tabela.addColuna(new TableColuna("nome", "Nome"));
            tabela.addColuna(new TableColuna("endereco", "Endereço"));
            tabela.addColuna(new TableColuna("nomeCidade", "Cidade"));
            tabela.addColuna(new TableColuna("preco", "Preços"));
            tabela.addColuna(new TableColuna("taxas", "Taxas"));
        %>

        <script>
            var mainApp = angular.module("mainApp", []);

            mainApp.controller('cadclientes', function ($scope, $http) {
                $scope.lista = [];
                $scope.editando = false;

            <%
                out.print(formulario.functionCarregaJson("/hl/json/clientes.jsp", "$scope.lista"));
                out.print(formulario.functionCarregaJson("/hl/json/cidades.jsp", "$scope.cidades"));
                out.print(formulario.functionVoltar());
                out.print(formulario.functionEditar());
                out.print(formulario.functionGravar("/hl/cadClientesGravar.jsp"));
            %>

            });

        </script>
    </head>
    <body>
        <section id="tables">
            <div class="page-header">
                <jsp:include page="menu.html" />
            </div>
            <div ng-app="mainApp" ng-controller="cadclientes" >
                <div class="container">
                    <div id="includedContent"></div>
                    Gravar: {{lista2}} - {{resultado}}
                    <div class="row" ng-hide="!editando">
                        <div class="col-lg-8">
                            <% formulario.buildForm(out); %>
                        </div>
                    </div>

                    <div class="row" ng-hide="editando">
                        <div class="col-lg-8">
                            <% tabela.buildTable(out);%>
                        </div>
                    </div>
                </div>
            </div>

        </section>

    </body>
</html>
