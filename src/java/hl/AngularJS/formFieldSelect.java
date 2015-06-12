package hl.AngularJS;

public class formFieldSelect extends formField {

    String listaJson;
    String id;
    String display;

    public formFieldSelect(String label, String name, String listaJson, String id, String display) {
        super(label, name);
        this.listaJson = listaJson;
        this.id = id;
        this.display = display;
    }

    @Override
    public String getEdicaoAtribuicao() {
        StringBuilder sb = new StringBuilder();

        sb.append("for(var i in $scope.").append(listaJson).append("){");
        sb.append(" if($scope." + listaJson + "[i]." + id + "===rec." + getDBName() + "){");
        sb.append("  $scope.").append(getName()).append(" = $scope.").append(listaJson).append("[i];");
        sb.append("  break;");
        sb.append(" }");
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String getDataToDB() {
        return "$scope." + this.getName() + "." + id;
    }

    @Override
    public String toString() {

        String optionByJson = "item." + display + " for item in " + listaJson;

        System.out.println(getName() + ".getProperties(): " + getProperties());
        String campo = ""
                + "                            <div class=\"control-group\">\n"
                + "                                <label class=\"control-label\" for=\"" + getName() + "\">" + getLabel() + "</label>\n"
                + "                                <div class=\"controls\">\n"
                + "                                    <select  \n"
                + "                                        class=\"form-control\"  \n"
                + "                                        " + getProp("ng-model", getName()) + " \n"
                + "                                        " + getProp("ng-options", optionByJson) + " \n"
                + "                                        " + getProperties() + ">\n"
                + "                                    </select>\n"
                + "                                </div>\n"
                + "                            </div>\n";
        return campo;
    }

}
