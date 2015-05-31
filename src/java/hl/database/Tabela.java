/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hl.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import util.ServicosHttp;

/**
 *
 * @author AdrianoNB
 */
public class Tabela {

    private ArrayList<Coluna> colunas;
    private String nome;

    public Tabela(String nome) {
        this.nome = nome;
        colunas = new ArrayList<>();
    }

    public void addColuna(Coluna coluna) {
        colunas.add(coluna);
    }

    public void setValor(String nomeColuna, String valor) {
        //System.out.println("Setando:"+nomeColuna+"="+valor);
        for (Coluna coluna : colunas) {
            if (coluna.getNome().equals(nomeColuna)) {
                coluna.setValor(valor);
                break;
            }
        }
    }

    public void parseJsonRequest(HttpServletRequest request) {
        JSONObject post = ServicosHttp.getPostJson(request);
        this.parseJson(post);
    }

    public void parseJson(JSONObject json) {
        for (Coluna coluna : colunas) {
            setValor(coluna.getNome(), json.get(coluna.getNome()).toString());
        }
    }

    public String getCmdUpdate() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbSet = new StringBuilder();
        StringBuilder sbWhere = new StringBuilder();

        sb.append("update ").append(getNome());

        for (Coluna coluna : colunas) {
            if (coluna.isPk()) {
                if (sbWhere.length() == 0) {
                    sbWhere.append(" WHERE ");
                } else {
                    sbWhere.append(", ");
                }
                sbWhere.append(coluna.getNome());
                sbWhere.append("=");
                sbWhere.append(coluna.getValorSQL());
            } else {
                if (sbSet.length() == 0) {
                    sbSet.append(" SET ");
                } else {
                    sbSet.append(", ");
                }
                sbSet.append(coluna.getNome());
                sbSet.append("=");
                sbSet.append(coluna.getValorSQL());
            }
        }

        sb.append(sbSet.toString());
        sb.append(sbWhere.toString());

        return sb.toString();
    }

    public String getCmdSelect() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbColunas = new StringBuilder();
        StringBuilder sbWhere = new StringBuilder();

        for (Coluna coluna : colunas) {
            if (coluna.isPk()) {
                if (sbWhere.length() != 0) {
                    sbWhere.append(", ");
                }
                sbWhere.append(coluna.getNome());
                sbWhere.append("=");
                sbWhere.append(coluna.getValorSQL());
            }
            if (sbColunas.length() != 0) {
                sbColunas.append(", ");
            }
            sbColunas.append(coluna.getNome());
        }

        sb.append("SELECT ");
        sb.append(sbColunas.toString());
        sb.append(" FROM ");
        sb.append(getNome());
        sb.append(" WHERE ");
        sb.append(sbWhere.toString());

        return sb.toString();
    }

    public String getCmdDelete() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbWhere = new StringBuilder();

        sb.append("delete ").append(getNome());

        for (Coluna coluna : colunas) {
            if (coluna.isPk()) {
                if (sbWhere.length() == 0) {
                    sbWhere.append(" WHERE ");
                } else {
                    sbWhere.append(", ");
                }
                sbWhere.append(coluna.getNome());
                sbWhere.append("=");
                sbWhere.append(coluna.getValorSQL());
            }
        }

        sb.append(sbWhere.toString());

        return sb.toString();
    }

    public String getCmdInsert() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbColuna = new StringBuilder();
        StringBuilder sbValores = new StringBuilder();

        sb.append("insert into ").append(getNome());

        for (Coluna coluna : colunas) {
            if (sbColuna.length() != 0) {
                sbColuna.append(", ");
                sbValores.append(", ");
            }
            sbColuna.append(coluna.getNome());
            sbValores.append(coluna.getValorSQL());
        }

        sb.append("(");
        sb.append(sbColuna.toString());
        sb.append(") values (");
        sb.append(sbValores.toString());
        sb.append(")");

        return sb.toString();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String update() {
        
        String retorno="";
        String acao="";
        
        try {
            Connection con = util.Conexao.conexao();
            java.sql.Statement s;
            java.sql.ResultSet rs;
            s = con.createStatement();

            String existeRegistro = this.getCmdSelect();

            rs = s.executeQuery(existeRegistro);
            String comando;
            if (rs.next()) {
                acao = "Exite";
                comando = this.getCmdUpdate();
            } else {
                acao = "Nao existe";
                comando = this.getCmdInsert();
            }

            s.executeUpdate(comando);

            JSONObject obj = new JSONObject();
            obj.put("Resultato", acao);
            obj.put("Comando", comando);

            retorno = obj.toString();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tabela.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
}
