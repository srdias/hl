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
    private int align;
    
    public static int ALIGN_LEFT = 1;
    public static int ALIGN_CENTER = 2;
    public static int ALIGN_RIGTH = 3;

    public TableColuna(String nome, String label) {
        this.nome = nome;
        this.label = label;
        this.align = ALIGN_LEFT;
    }

    public TableColuna(String nome, String label, int align) {
        this.nome = nome;
        this.label = label;
        this.align = align;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return this.nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAlign() {
        return align;
    }

    public void setAlign(int align) {
        this.align = align;
    }
    
    
}
