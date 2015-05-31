package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import hl.AngularJS.TableColuna;
import hl.AngularJS.Table;
import hl.AngularJS.formFieldCheckBox;
import hl.AngularJS.formFieldSelect;
import hl.AngularJS.formFieldText;
import hl.AngularJS.formButton;
import hl.AngularJS.form;

public final class cadClientes_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        ");

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
        
      out.write("\n");
      out.write("        <script>\n");
      out.write("            var mainApp = angular.module(\"mainApp\", []);\n");
      out.write("\n");
      out.write("            mainApp.controller('studentController', function ($scope, $http) {\n");
      out.write("                $scope.lista = [];\n");
      out.write("                $scope.editando = false;\n");
      out.write("\n");
      out.write("                $http.post(\"/hl04/json/clientes.jsp\").success(function (data, status) {\n");
      out.write("                    $scope.lista = data;\n");
      out.write("                });\n");
      out.write("\n");
      out.write("                $http.post(\"/hl04/json/cidades.jsp\").success(function (data, status) {\n");
      out.write("                    $scope.cidades = data;\n");
      out.write("                });\n");
      out.write("\n");
      out.write("                $scope.Voltar = function () {\n");
      out.write("                    $scope.editando = false;\n");
      out.write("                };\n");
      out.write("\n");
      out.write("            ");

                out.print(formulario.functionEditar());
            
      out.write("\n");
      out.write("\n");
      out.write("                $scope.Gravar = function () {\n");
      out.write("            ");

                out.print(formulario.getJsonData("$scope.lista2"));
            
      out.write("\n");
      out.write("                    $scope.editando = false;\n");
      out.write("\n");
      out.write("                    $http.post(\"/hl04/cadContasGravar.jsp\", $scope.lista2).success(function (data, status) {\n");
      out.write("                        $scope.resultado = data;\n");
      out.write("                    });\n");
      out.write("\n");
      out.write("                };\n");
      out.write("\n");
      out.write("            });\n");
      out.write("\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <section id=\"tables\">\n");
      out.write("            <div class=\"page-header\">\n");
      out.write("            </div>\n");
      out.write("            <div ng-app=\"mainApp\" ng-controller=\"studentController\" >\n");
      out.write("                <div class=\"container\">\n");
      out.write("                    Gravar: {{lista2}}\n");
      out.write("                    <li> {{resultado}}\n");
      out.write("                        <div class=\"row\" ng-hide=\"!editando\">\n");
      out.write("                            <div class=\"col-lg-8\">\n");
      out.write("                                ");
 formulario.buildForm(out); 
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"row\" ng-hide=\"editando\">\n");
      out.write("                            <div class=\"col-lg-8\">\n");
      out.write("                                ");
 tabela.buildTable(out);
      out.write("\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                </div>\n");
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
