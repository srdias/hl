package hl.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.jsp.JspWriter;

public class DataBase {

    public void createDataBase(JspWriter out) throws IOException {

        createTable(out, getCidades());
        createTable(out, getClientes());
        createTable(out, getContasReceberPagar());
        createTable(out, getVendas());
        createTable(out, getContas());
        createTable(out, getMovContas());
        
    }
    
    public static Tabela getTabelaByName(String name){
        switch(name){
            case "vendas": return DataBase.getVendas();
            case "clientes": return DataBase.getClientes();
            case "contas": return DataBase.getContas();
            case "cidades": return DataBase.getCidades();
            case "contasReceberPagar": return DataBase.getContasReceberPagar();
            case "movContas": return DataBase.getMovContas();
        }
        
        return (Tabela)null;
    }

    public void createTable(JspWriter out, Tabela tabela) throws IOException {

        StringBuilder sb = new StringBuilder();

        sb.append("<li>Criando tabela '");
        sb.append(tabela.getNome());
        sb.append("' - ");
        try {
            Connection con = util.Conexao.conexao();
            java.sql.Statement s;
            s = con.createStatement();

            s.executeUpdate(tabela.getCmdCreateTable());

            sb.append("OK");
        } catch (SQLException e) {
            sb.append("ERRO: ").append(e.getMessage());
            e.printStackTrace();
        }

        out.println(sb.toString());
    }

    public static Tabela getClientes() {
        Tabela tabela = new Tabela("clientes");
        tabela.addColuna(new Coluna("id", java.sql.Types.INTEGER, true));
        tabela.addColuna(new Coluna("nome", java.sql.Types.CHAR, 60));
        tabela.addColuna(new Coluna("endereco", java.sql.Types.CHAR, 60));
        tabela.addColuna(new Coluna("cidade_id", java.sql.Types.INTEGER));
        tabela.addColuna(new Coluna("preco", java.sql.Types.DECIMAL));
        tabela.addColuna(new Coluna("taxas", java.sql.Types.DECIMAL));
        
//        Fk fk = new Fk();
//        fk.setTabelaPrincipal(tabela);
//        fk.setTabelaReferenciada(getCidades());
//        fk.setColunaTabelaPrincipal("cidade_id");
//        fk.setColunaTabelaReferenciada("id");
//        
//        tabela.addFk(fk);
        
        return tabela;
    }

    public static Tabela getContas() {
        Tabela tabela = new Tabela("contas");
        tabela.addColuna(new Coluna("id", java.sql.Types.INTEGER, true));
        tabela.addColuna(new Coluna("nome", java.sql.Types.CHAR, 60));
        
        tabela.setValorInicial("id","");
        tabela.setValorInicial("nome","");
        
        return tabela;
    }

    public static Tabela getCidades() {
        Tabela tabela = new Tabela("cidades");
        tabela.addColuna(new Coluna("id", java.sql.Types.INTEGER, true));
        tabela.addColuna(new Coluna("nome", java.sql.Types.CHAR, 60));
        return tabela;
    }

    public static Tabela getContasReceberPagar() {

        Tabela tabela = new Tabela("contasReceberPagar");
        tabela.addColuna(new Coluna("id", java.sql.Types.INTEGER, true));
        tabela.addColuna(new Coluna("empresa_id", java.sql.Types.INTEGER));
        tabela.addColuna(new Coluna("dt_emissao", java.sql.Types.DATE));
        tabela.addColuna(new Coluna("tipo_movimento_id", java.sql.Types.INTEGER));
        tabela.addColuna(new Coluna("conta_id", java.sql.Types.INTEGER));
        tabela.addColuna(new Coluna("valor_desconto", java.sql.Types.DECIMAL));
        tabela.addColuna(new Coluna("valor_acrescimo", java.sql.Types.DECIMAL));
        tabela.addColuna(new Coluna("valor_total", java.sql.Types.DECIMAL));
        tabela.addColuna(new Coluna("observacao", java.sql.Types.CHAR, 1024));
        tabela.addColuna(new Coluna("movcontas_id", java.sql.Types.INTEGER));
        
        tabela.setValorInicial("conta_id","0");
        tabela.setValorInicial("movcontas_id","0");

        return tabela;
    }


    public static Tabela getVendas() {

        Tabela tabela = new Tabela("vendas");
        tabela.addColuna(new Coluna("id", java.sql.Types.INTEGER, true));
        tabela.addColuna(new Coluna("empresa_id", java.sql.Types.INTEGER));
        tabela.addColuna(new Coluna("dt_venda", java.sql.Types.DATE));
        tabela.addColuna(new Coluna("valor_unidade", java.sql.Types.DECIMAL));
        tabela.addColuna(new Coluna("quantidade", java.sql.Types.INTEGER));
        tabela.addColuna(new Coluna("taxas", java.sql.Types.DECIMAL));
        tabela.addColuna(new Coluna("total", java.sql.Types.DECIMAL));
        tabela.addColuna(new Coluna("observacao", java.sql.Types.CHAR, 1024));
        tabela.addColuna(new Coluna("contasPagarReceber_id", java.sql.Types.INTEGER));
        
        return tabela;
    }

    public static Tabela getMovContas() {

        Tabela tabela = new Tabela("movContas");
        tabela.addColuna(new Coluna("id", java.sql.Types.INTEGER, true));
        tabela.addColuna(new Coluna("dt_movto", java.sql.Types.DATE));
        tabela.addColuna(new Coluna("conta_debito_id", java.sql.Types.INTEGER));
        tabela.addColuna(new Coluna("conta_credito_id", java.sql.Types.INTEGER));
        tabela.addColuna(new Coluna("valor", java.sql.Types.DECIMAL));
        tabela.addColuna(new Coluna("observacao", java.sql.Types.CHAR, 60));

        return tabela;
    }
}
