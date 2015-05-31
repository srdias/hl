/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hl.AngularJS;

/**
 *
 * @author AdrianoNB
 */
public abstract class formField extends formObject {

    private String label;
    private String name;

    public formField(String label, String name) {
        this.label = label;
        this.name = name;
    }

    public String getName() {
        return "reg_"+name;
    }

    public String getDBName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getEdicaoAtribuicao() {
        StringBuilder sb = new StringBuilder();
        sb.append("$scope.");
        sb.append(this.getName());
        sb.append(" = rec.");
        sb.append(this.getDBName());
        return sb.toString();
    }
    
    public String getDataToDB(){
        return "$scope."+this.getName();
    }
}
