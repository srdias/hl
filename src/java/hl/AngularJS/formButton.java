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
public class formButton extends formObject {

    String nome;
    String label;
    String acao;

    public formButton(String label, String acao) {
        this.label = label;
        this.acao = acao;
    }

    @Override
    public String toString() {

        String texto = "<button type=\"submit\" "
                + getProp("ng-click", this.acao)
                + " class=\"btn btn-primary\">"
                + label
                + "</button>\n";
        return texto;
    }

}
