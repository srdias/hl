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
    private Integer tamanho;
    private boolean nulo;

    public Coluna(String nome, int tipo, int tamanho) {
        this.tipo = tipo;
        this.nome = nome;
        this.tamanho = tamanho;
        this.pk = false;
        this.nulo = false;
    }

    public Coluna(String nome, int tipo, boolean pk) {
        this.tipo = tipo;
        this.nome = nome;
        this.tamanho = 0;
        this.pk = pk;
        this.nulo = false;
    }

    public Coluna(String nome, int tipo) {
        this.tipo = tipo;
        this.nome = nome;
        this.tamanho = 0;
        this.pk = false;
        this.nulo = false;
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
        return "'" + this.valor + "'";
    }

    public Integer getTamanho() {
        return tamanho;
    }

    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isNulo() {
        return nulo;
    }

    public void setNulo(boolean nulo) {
        this.nulo = nulo;
    }

    public String getDefinicaoCreateTable() {

        StringBuilder sb = new StringBuilder();

        sb.append(getNome());
        sb.append(" ");
        switch (getTipo()) {
            case java.sql.Types.CHAR:
                if (getTamanho() > 50) {
                    sb.append("VARCHAR(");
                } else {
                    sb.append("CHAR(");
                }
                sb.append(getTamanho());
                sb.append(")");
                break;
            case java.sql.Types.DECIMAL:
                sb.append("decimal(");
                sb.append("13,2");
                sb.append(")");
                break;
            case java.sql.Types.DATE:
                sb.append("DATE");
                break;
            default:
                sb.append("INTEGER");
        }

        if (isPk()) {
            sb.append(" primary key");
        }

        if (isNulo()) {
            sb.append(" NULL");
        } else {
            sb.append(" NOT NULL");

        }

        return sb.toString();
    }
}
