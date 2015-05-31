package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import hl.AngularJS.formFieldCheckBox;
import hl.AngularJS.formFieldSelect;
import hl.AngularJS.formFieldText;
import hl.AngularJS.formButton;
import hl.AngularJS.form;

public final class cadastro_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<!--\n");
      out.write("To change this license header, choose License Headers in Project Properties.\n");
      out.write("To change this template file, choose Tools | Templates\n");
      out.write("and open the template in the editor.\n");
      out.write("-->\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>TODO supply a title</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"css/styles.css\" rel=\"stylesheet\">\n");
      out.write("        <script src=\"js/angular.min.js\" type=\"text/javascript\"></script>\n");
      out.write("\n");
      out.write("        ");

            form formulario = new form();
            formulario.setTitulo("Contas a Receber");
            formulario.addFieldObject(new formFieldText("Data:", "reg_data_recebimento"));
            formulario.addFieldObject(new formFieldSelect("Conta:", "reg_conta", "conta.nome for conta in contas"));
            formulario.addFieldObject(new formFieldText("Valor:", "reg_valor"));
            formulario.addFieldObject(new formFieldText("Observação:", "reg_observacao"));
            formulario.addFieldObject(new formFieldCheckBox("Confirmado:", "reg_confirmacao", "Confirmação se o recebimento foi efetuado.", "1"));
            formulario.addButtonSubmit("Gravar");
            formulario.addButtonReset("Voltar");
            formulario.addButtonObject(new formButton("Teste", "teste()"));
        
      out.write("\n");
      out.write("        <script>\n");
      out.write("            var mainApp = angular.module(\"mainApp\", []);\n");
      out.write("\n");
      out.write("            mainApp.controller('studentController', function ($scope, $http) {\n");
      out.write("                $scope.lista = [];\n");
      out.write("                $scope.contas = [];\n");
      out.write("\n");
      out.write("                $http.post(\"/hl04/testeResultSetJson.jsp\").success(function (data, status) {\n");
      out.write("                    $scope.lista = data;\n");
      out.write("                });\n");
      out.write("\n");
      out.write("\n");
      out.write("                $http.post(\"/hl04/json/contas.jsp\").success(function (data, status) {\n");
      out.write("                    $scope.contas = data;\n");
      out.write("                    $scope.contaForm = $scope.contas[1];\n");
      out.write("                });\n");
      out.write("                \n");
      out.write("                \n");
      out.write("\n");
      out.write("                $scope.teste = function () {\n");
      out.write("                ");

                    out.print(formulario.getJsonData("$scope.lista2"));
                
      out.write("\n");
      out.write("                };\n");
      out.write("\n");
      out.write("           });\n");
      out.write("\n");
      out.write("            //testex();\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <section id=\"tables\">\n");
      out.write("            <div class=\"page-header\">\n");
      out.write("            </div>\n");
      out.write("            <div ng-app=\"mainApp\" ng-controller=\"studentController\" >\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-lg-8\">\n");
      out.write("                        {{lista2}}<br>\n");
      out.write("                        {{reg_data_recebimento}}\n");
      out.write("                        ");

                            formulario.buildForm(out);
                        
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </section>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
