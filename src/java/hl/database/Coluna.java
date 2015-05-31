/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hl.database;

/**
 *
 * @author AdrianoNB
 */
public class Coluna {
    private int tipo;
    private String nome;
    private boolean pk;
    private String valor;

    public Coluna(String nome, int tipo, boolean pk) {
        this.tipo = tipo;
        this.nome = nome;
        this.pk = pk;
    }

    public Coluna(String nome, int tipo) {
        this.tipo = tipo;
        this.nome = nome;
        this.pk = false;
    }

    public int getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public boolean isPk() {
        return pk;
    }

    public void setPk(boolean pk) {
        this.pk = pk;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public String getValorSQL() {
        return "'"+this.valor+"'";
    }
}
