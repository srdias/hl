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
import org.json.JSONException;
import org.json.JSONObject;
import static util.ResultSetConverter.formatDate;
import static util.ResultSetConverter.formatDecimal;
import util.ServicosHttp;

/**
 *
 * @author AdrianoNB
 */
public class Tabela {

    private Connection con;

    private ArrayList<Coluna> colunas;
    private String nome;
    private JSONObject post;

    public Tabela(String nome) {
        this.nome = nome;
        colunas = new ArrayList<>();
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

    public Integer getValorInteger(String nomeColuna) {
        int valor = 0;
        Coluna coluna = findColuna(nomeColuna);
        if (coluna != null) {
            valor = coluna.getValorInteger();
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
                    String data = obj.toString();
                    switch (coluna.getTipo()) {
                        case java.sql.Types.DATE: {
                            data = data.substring(6, 10) + "-"
                                    + data.substring(3, 5) + "-"
                                    + data.substring(0, 2);
                            break;
                        }
                        case java.sql.Types.INTEGER:
                        case java.sql.Types.DECIMAL: {
                            data = data.replaceAll("\\.", "");
                            data = data.replaceAll(",", ".");
                            break;
                        }
                    }
                    setValor(coluna.getNome(), data);
                }
            } catch (JSONException ex) {
                setValor(coluna.getNome(), "");
                System.err.println("Erro lendo coluna: " + coluna.getNome());
            }
        }
    }

    public JSONObject getJson() {
        JSONObject json = new JSONObject();
        for (Coluna coluna : colunas) {
            try {
                if (coluna.getTipo() == java.sql.Types.DATE) {
                    String lsData = formatDate(coluna.getValor());
                    json.put(coluna.getNome(), lsData);
//                    json.put(coluna.getNome(), Double.parseDouble(coluna.getValorSQL()));
                } else if (coluna.getTipo() == java.sql.Types.DECIMAL) {
//                    json.put(coluna.getNome(), Double.parseDouble(coluna.getValorSQL()));
                    String lsData = formatDecimal(Double.valueOf(coluna.getValor()));
                    json.put(coluna.getNome(), lsData);

                } else if (coluna.getTipo() == java.sql.Types.INTEGER) {
//                    json.put(coluna.getNome(), coluna.getValorSQL());
                    String lsData = formatDecimal(Integer.valueOf(coluna.getValor()));
                    json.put(coluna.getNome(), lsData);

                } else {
                    json.put(coluna.getNome(), coluna.getValor());
                }

            } catch (JSONException ex) {
                setValor(coluna.getNome(), "");
                System.err.println("Erro lendo coluna: " + coluna.getNome());
            }
        }

        JSONObject jsonRec = new JSONObject();
        jsonRec.put("rec", json);

        return jsonRec;
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

//    public void addFk(Fk fk) {
//        fks.add(fk);
//    }
//
//    public void revolveFk(JSONArray jsonData) {
//        System.out.println("fks tamanho: " + fks.size());
//        for (Fk fk : fks) {
//            fk.putDataFk(con, jsonData);
//        }
//    }
    public int getProximoID() {

        int retorno = 0;

        try {
            ResultSet rs = getResultSetBySQL(this.getCmdSelectNovaChave());

            if (rs.next()) {
                retorno = rs.getInt("maior");
            }
            rs.close();

        } catch (SQLException ex) {
        }

        retorno++;

        return retorno;
    }

    public ResultSet getResultSetBySQL(String sql) {

        ResultSet rs = null;

        try {
            java.sql.Statement s;
            s = con.createStatement();

            System.out.println("comando:" + sql);
            rs = s.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }

        return rs;
    }

    public ResultSet executarComando(String sql) {

        ResultSet rs = null;

        try {
            java.sql.Statement s;
            s = con.createStatement();

            System.out.println("comando:" + sql);
            s.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println("Erro:" + ex.getMessage());
        }

        return rs;
    }

    public void conectar() {
        if (con == null) {
            con = util.Conexao.conexao();
        }
    }

    public String update() {

        String retorno = "";
        String acao;

        try {
            conectar();
            java.sql.ResultSet rs;

            String existeRegistro = this.getCmdSelect();

            System.out.println(existeRegistro);
            rs = getResultSetBySQL(existeRegistro);
            String comando;
            if (rs.next()) {
                acao = "Exite";
                comando = this.getCmdUpdate();
            } else {
                acao = "Nao existe";

                if (this.getValor("id").equals("0")) {
                    this.setValor("id", Integer.toString(this.getProximoID()));
                }
                comando = this.getCmdInsert();
            }

            executarComando(comando);

            JSONObject obj = new JSONObject();
            obj.put("Resultato", acao);
            obj.put("Comando", comando);

            retorno = obj.toString();

        } catch (SQLException ex) {
            Logger.getLogger(Tabela.class.getName()).log(Level.SEVERE, null, ex);
        }

        Triggers triggers = new Triggers(this);
        triggers.trigger();

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

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public JSONObject getPost() {
        return post;
    }

    public void setPost(JSONObject post) {
        this.post = post;
    }
}
