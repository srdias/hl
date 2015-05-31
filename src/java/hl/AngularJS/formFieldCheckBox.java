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
public class formFieldCheckBox extends formField {
    
    String texto;
    String value;

    public formFieldCheckBox(String label, String name, String texto, String value) {
        super(label, name);
        this.texto = texto;
        this.value = value;
    }

    @Override
    public String toString() {
        String campo = ""
                + "<div class=\"control-group\">\n"
                + "            <label class=\"control-label\" for=\"" + getName() + "\">" + getLabel() + "</label>\n"
                + "            <div class=\"controls\">\n"
                + "              <label class=\"checkbox\">\n"
                + "                <input type=\"checkbox\" "+getProp("ng-model", getName())+" "+getProp("id", getName())+" value=\"" + value + "\">\n"
                + "                " + texto + "\n"
                + "              </label>\n"
                + "            </div>\n"
                + "          </div>";
        return campo;
    }

}
