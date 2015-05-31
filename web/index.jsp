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
        %>

    </head>
    <body>
        <section id="tables">
            <div class="page-header">
                    <jsp:include page="menu.html" />
            </div>
            <div ng-app="mainApp" ng-controller="cadclientes" >
                <div class="container">
                </div>
            </div>

        </section>

    </body>
</html>
