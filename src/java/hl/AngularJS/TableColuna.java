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
public class TableColuna {
    private String nome;
    private String label;

    public TableColuna(String nome, String label) {
        this.nome = nome;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return this.nome;
    }
    
    
}
