package hl.AngularJS;

import java.util.ArrayList;

public class form {

    ArrayList<formField> fields;
    ArrayList<String> botoes;
    String titulo;

    public form() {
        fields = new ArrayList<>();
        botoes = new ArrayList<>();
        titulo = "";
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    ;
    
    public void addButtonSubmit(String label) {
        String texto = "<button type=\"submit\" class=\"btn btn-primary\">" + label + "</button>\n";
        botoes.add(texto);
    }

    public void addButtonReset(String label) {
        String texto = "<button class=\"btn\" ng-click=\"alert('a')\">" + label + "</button>\n";
        botoes.add(texto);
    }

    public void addButtonObject(formObject obj) {
        botoes.add(obj.toString());
    }

    public void addFieldObject(formField obj) {
        fields.add(obj);
    }

    public formField findFieldObject(String nome) {
        formField objRetorno = null;
        for (formField field : fields) {
            if (field.getDBName().equals(nome)) {
                objRetorno = field;
                break;
            }
        }
        return objRetorno;
    }

    public void fieldObjectAddProp(String nomeObj, String nomeProp, String valor) {
        formObject obj = this.findFieldObject(nomeObj);
        if (obj != null) {
            obj.putProp(nomeProp, valor);
        }else{
            System.out.println("Objeto nao encontrado: "+nomeObj);
        }
    }

    public void fielSetVisible(String nomeObj, boolean visivel) {
        formObject obj = this.findFieldObject(nomeObj);
        if (obj != null) {
            obj.setVisivel(visivel);
        }else{
            System.out.println("Objeto nao encontrado: "+nomeObj);
        }
    }

    public String getJsonData(String variavel) {

        String identa = "\t\t\t\t\t\t";
        StringBuilder sb = new StringBuilder();

        fields.stream().forEach((field) -> {
            if (sb.length() != 0) {
                sb.append(", ").append("\n").append(identa);
            }
            sb.append("\"").append(field.getDBName()).append("\": ").append(field.getDataToDB());
        });

        sb.insert(0, "\n\t\t\t\t\t" + variavel + " = {\n" + identa);
        sb.append("\n\t\t\t\t\t}");

        return sb.toString();

    }

    public String buildForm() {

        StringBuilder sb = new StringBuilder();

        sb.append("<form class=\"form-horizontal well\">\n");
        sb.append("        <fieldset>");
        sb.append("<legend>").append(titulo).append("</legend>");

        for (int i = 0; i < fields.size(); i++) {
            sb.append(fields.get(i).toString());
            sb.append("\n");
        }

        sb.append("         <hr>\n"
                + "          <div class=\"form-actions\">\n");

        for (String botoe : botoes) {
            sb.append(botoe);
            sb.append("\n");
        }

        sb.append(""
                + "          </div>\n"
                + "        </fieldset>\n"
                + "</form>"
        );
        return sb.toString();
    }
    /*
     public String functionEditar() {
     StringBuilder sb = new StringBuilder();

     sb.append("\n\t\t\t\t");
     sb.append("$scope.Editar = function(rec) {").append("\n");

     for (formField field : fields) {
     sb.append("\t\t\t\t\t");
     sb.append(field.getEdicaoAtribuicao());
     sb.append(";\n");
     }

     sb.append("\t\t\t\t");
     sb.append("\t$scope.editando = true;").append("\n");
     sb.append("\t\t\t\t}\n");

     return sb.toString();

     }

     public String functionNovo() {
     StringBuilder sb = new StringBuilder();

     sb.append("\n\t\t\t\t");
     sb.append("$scope.NovoRegistro = function(rec) {").append("\n");

     for (formField field : fields) {
     sb.append("\t\t\t\t\t");
     sb.append(field.getNovoInicializacao());
     sb.append(";\n");
     }

     sb.append("\t\t\t\t");
     sb.append("\t$scope.editando = true;").append("\n");
     sb.append("\t\t\t\t}\n");

     return sb.toString();

     }

     public String functionCarregaJson(String url, String variavelLista) {

     StringBuilder sb = new StringBuilder();

     sb.append("\t$http.post(\"").append(url).append("\").success(function (data, status) {\n");
     sb.append("\t\t\t\t\t");
     sb.append(variavelLista);
     sb.append("= data;\n");
     sb.append("                });\n\n");

     return sb.toString();
     }

     public String functionGravar(String url) {

     StringBuilder sb = new StringBuilder();

     sb.append("$scope.Gravar = function () {");
     sb.append(this.getJsonData("$scope.listaGravar"));
     sb.append(";\n");
     sb.append("$scope.editando = false;\n");

     sb.append("$http.post(\"");
     sb.append(url);
     sb.append("\", $scope.listaGravar)");
     sb.append(".success(function(data, status) {");
     sb.append("$scope.resultado = data;");
     sb.append("});\n");
     sb.append("};\n");

     return sb.toString();
     }

     public String functionVoltar() {

     StringBuilder sb = new StringBuilder();

     sb.append("\t\t$scope.Voltar = function () {");
     sb.append("\t\t\t$scope.editando = false;\n");
     sb.append("\t\t};\n");

     return sb.toString();
     }
     */
}
