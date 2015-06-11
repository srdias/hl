/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hl.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.ServicosHttp;

/**
 *
 * @author AdrianoNB
 */
public class Tabela {

    private ArrayList<Coluna> colunas;
    private ArrayList<Fk> fks;
    private String nome;
    public JSONObject post;

    public Tabela(String nome) {
        this.nome = nome;
        colunas = new ArrayList<>();
        fks = new ArrayList<>();
    }

    public void addColuna(Coluna coluna) {
        colunas.add(coluna);
    }

    public Coluna findColuna(String nomeColuna) {
        Coluna colunaRetorno = null;
        for (Coluna coluna : colunas) {
            if (coluna.getNome().equals(nomeColuna)) {
                colunaRetorno = coluna;
                break;
            }
        }
        return colunaRetorno;
    }

    public void setNulo(String nomeColuna, boolean nulo) {
        Coluna coluna = findColuna(nomeColuna);
        if (coluna != null) {
            coluna.setNulo(nulo);
        }
    }

    public void setValor(String nomeColuna, String valor) {
        Coluna coluna = findColuna(nomeColuna);
        if (coluna != null) {
            coluna.setValor(valor);
        }

    }

    public String getValor(String nomeColuna) {
        String valor = "";
        Coluna coluna = findColuna(nomeColuna);
        if (coluna != null) {
            valor = coluna.getValor();
        }
        return valor;
    }

    public void setValorInicial(String nomeColuna, String valor) {
        Coluna coluna = findColuna(nomeColuna);
        if (coluna != null) {
            coluna.setValorInicial(valor);
        }

    }

    public void parseJsonRequest(HttpServletRequest request) {
        post = ServicosHttp.getPostJson(request);
        this.parseJson(post);
    }

    public void parseJson(JSONObject json) {
        for (Coluna coluna : colunas) {
            try {
                Object obj = json.get(coluna.getNome());
                if (obj instanceof JSONObject) {
                    JSONObject jsonObj = (JSONObject) obj;
                    setValor(coluna.getNome(), jsonObj.get("id").toString());
                } else {
                    setValor(coluna.getNome(), obj.toString());
                }
            } catch (JSONException ex) {
                setValor(coluna.getNome(), "");
                System.err.println("Erro lendo coluna: " + coluna.getNome());
            }
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
                if (coluna.getValor() != null) {
                    if (sbWhere.length() != 0) {
                        sbWhere.append(", ");
                    }
                    sbWhere.append(coluna.getNome());
                    sbWhere.append("=");
                    sbWhere.append(coluna.getValorSQL());
                }
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

        if (sbWhere.length() != 0) {
            sb.append(" WHERE ");
            sb.append(sbWhere.toString());
        }

        return sb.toString();
    }

    public String getCmdSelectNovaChave() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbColunas = new StringBuilder();

        for (Coluna coluna : colunas) {
            if (coluna.isPk()) {
                sbColunas.append("max(");
                sbColunas.append(coluna.getNome());
                sbColunas.append(") as maior");
            }
        }

        sb.append("SELECT ");
        sb.append(sbColunas.toString());
        sb.append(" FROM ");
        sb.append(getNome());

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

    public void addFk(Fk fk) {
        fks.add(fk);
    }

    public void revolveFk(Connection conn, JSONArray jsonData) {
        System.out.println("fks tamanho: " + fks.size());
        for (Fk fk : fks) {
            fk.putDataFk(conn, jsonData);
        }
    }

    public int getProximoID(Connection con) {

        int retorno = 0;

        try {
            ResultSet rs = getResultSetBySQL(con, this.getCmdSelectNovaChave());

            if (rs.next()) {
                retorno = rs.getInt("maior");
            }
            rs.close();

        } catch (SQLException ex) {
        }

        retorno++;

        return retorno;
    }

    public ResultSet getResultSetBySQL(Connection con, String sql) {

        ResultSet rs = null;

        try {
            java.sql.Statement s;
            s = con.createStatement();

            rs = s.executeQuery(sql);

        } catch (SQLException ex) {
        }

        return rs;
    }

    public String update() {

        String retorno = "";
        String acao;

        try {
            Connection con = util.Conexao.conexao();
            java.sql.Statement s;
            java.sql.ResultSet rs;
            s = con.createStatement();

            String existeRegistro = this.getCmdSelect();

            System.out.println(existeRegistro);
            rs = s.executeQuery(existeRegistro);
            String comando;
            if (rs.next()) {
                acao = "Exite";
                comando = this.getCmdUpdate();
            } else {
                acao = "Nao existe";
                System.out.println("acao:" + acao);

                if (this.getValor("id").equals("0")) {
                    this.setValor("id", Integer.toString(this.getProximoID(con)));
                }
                comando = this.getCmdInsert();
                System.out.println("comando:" + comando);
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

    public String getCmdCreateTable() {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE ");
        sb.append(getNome());
        sb.append("(\n");
        int conta = 0;
        for (Coluna coluna : colunas) {
            if (conta != 0) {
                sb.append(",\n");
            }
            conta++;
            sb.append("\t");
            sb.append(coluna.getDefinicaoCreateTable());
        }
        sb.append("\n)");

        return sb.toString();
    }

    public String retornoGravacao() {
        JSONObject obj = new JSONObject();
        obj.put("comando", this.update());
        this.post.put("id", getValor("id"));
        obj.put("rec", this.post);
        return obj.toString();
    }
}
