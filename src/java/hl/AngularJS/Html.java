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
public class Html {

    public String getProp(String propName, String propValue) {
        String retorno = "";

        if (!propValue.equals("")) {
            retorno = propName + "=\"" + propValue + "\"";
        }

        return retorno;
    }

}
