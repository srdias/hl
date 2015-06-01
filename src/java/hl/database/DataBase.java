package hl.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBase {

    public void createDataBase() {

        createTable(getClientes());
        createTable(getContas());
        createTable(getContasReceber());
        createTable(getContasReceberDetalhamento());
    }

    public void createTable(Tabela tabela) {

//        try {
//            Connection con = util.Conexao.conexao();
//            java.sql.Statement s;
//            java.sql.ResultSet rs;
//            s = con.createStatement();
//
//            s.executeUpdate(tabela.getCmdCreateTable());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }

    public static Tabela getClientes() {
        Tabela tabela = new Tabela("clientes");
        tabela.addColuna(new Coluna("id", java.sql.Types.INTEGER, true));
        tabela.addColuna(new Coluna("nome", java.sql.Types.CHAR, 60));
        tabela.addColuna(new Coluna("endereco", java.sql.Types.CHAR, 60));
        tabela.addColuna(new Coluna("cidade_id", java.sql.Types.INTEGER));
        tabela.addColuna(new Coluna("preco", java.sql.Types.DECIMAL));
        tabela.addColuna(new Coluna("taxas", java.sql.Types.DECIMAL));
        return tabela;
    }

    public static Tabela getContas() {
        Tabela tabela = new Tabela("contas");
        tabela.addColuna(new Coluna("id", java.sql.Types.INTEGER, true));
        tabela.addColuna(new Coluna("nome", java.sql.Types.CHAR, 60));
        return tabela;
    }

    public static Tabela getContasReceber() {

        Tabela tabela = new Tabela("contasReceber2");
        tabela.addColuna(new Coluna("id", java.sql.Types.INTEGER, true));
        tabela.addColuna(new Coluna("id_clientes", java.sql.Types.INTEGER));
        tabela.addColuna(new Coluna("dt_emissao", java.sql.Types.DATE));
        tabela.addColuna(new Coluna("dt_recebimento", java.sql.Types.DATE));
        tabela.addColuna(new Coluna("valor_desconto", java.sql.Types.DECIMAL));
        tabela.addColuna(new Coluna("valor_acrescimo", java.sql.Types.DECIMAL));
        tabela.addColuna(new Coluna("valor_total", java.sql.Types.DECIMAL));
        tabela.addColuna(new Coluna("observacao", java.sql.Types.CHAR, 1024));

        tabela.setNulo("dt_recebimento", true);

        return tabela;
    }

    public static Tabela getContasReceberDetalhamento() {

        Tabela tabela = new Tabela("contasReceberDetalhamento");
        tabela.addColuna(new Coluna("id", java.sql.Types.INTEGER, true));
        tabela.addColuna(new Coluna("id_contasReceber", java.sql.Types.INTEGER));
        tabela.addColuna(new Coluna("dt_bomPara", java.sql.Types.DATE));
        tabela.addColuna(new Coluna("numeroCheque", java.sql.Types.CHAR, 15));
        tabela.addColuna(new Coluna("valor", java.sql.Types.DECIMAL));
        tabela.addColuna(new Coluna("observacao", java.sql.Types.CHAR, 60));

        tabela.setNulo("numeroCheque", true);
        tabela.setNulo("observacao", true);

        return tabela;
    }

}
