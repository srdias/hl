
package hl.database;

public class DataBase {
    
    public static Tabela getClientes(){
        Tabela tabela = new Tabela("clientes");
        tabela.addColuna(new Coluna("id", java.sql.Types.INTEGER, true));
        tabela.addColuna(new Coluna("nome", java.sql.Types.CHAR));
        tabela.addColuna(new Coluna("endereco", java.sql.Types.CHAR));
        tabela.addColuna(new Coluna("cidade_id", java.sql.Types.INTEGER));
        tabela.addColuna(new Coluna("preco", java.sql.Types.DECIMAL));
        tabela.addColuna(new Coluna("taxas", java.sql.Types.DECIMAL));
        return tabela;
    }
    
    public static Tabela getContas(){
        Tabela tabela = new Tabela("contas");
        tabela.addColuna(new Coluna("id", java.sql.Types.INTEGER, true));
        tabela.addColuna(new Coluna("nome", java.sql.Types.CHAR));
        return tabela;
    }
    
}
