package hl.database;

public class Triggers {

    private Tabela tabela;

    public Triggers(Tabela tabela) {
        this.tabela = tabela;
    }

    public void trigger() {
        if (tabela.getNome().equals("vendas")) {
            this.triggerVendas();
        }
        if (tabela.getNome().equals("contasReceberPagar")) {
            this.triggerContasReceberPagar();
        }
    }

    public void triggerVendas() {

        Tabela contasRP = DataBase.getContasReceberPagar();
        contasRP.setValor("id", tabela.getValor("contasPagarReceber_id"));
        contasRP.setValor("empresa_id", tabela.getValor("empresa_id"));
        contasRP.setValor("dt_emissao", tabela.getValor("dt_venda"));
        contasRP.setValor("valor_total", tabela.getValor("total"));
        contasRP.setValor("tipo_movimento_id", "1");
        contasRP.setValor("conta_id", "0");
        contasRP.setValor("movcontas_id", "0");
        contasRP.setValor("valor_desconto", "0");
        contasRP.setValor("valor_acrescimo", "0");
        contasRP.setValor("observacao", "Venda.");

        contasRP.setCon(tabela.getCon());
        contasRP.update();

        if (tabela.getValor("contasPagarReceber_id").equals("0")) {
            tabela.setValor("contasPagarReceber_id", contasRP.getValor("id"));
            tabela.update();
        }

        if (!tabela.getPost().getString("vlr_recebido").equals("0")) {

            contasRP = DataBase.getContasReceberPagar();
            contasRP.setValor("id", "0");
            contasRP.setValor("empresa_id", tabela.getValor("empresa_id"));
            contasRP.setValor("dt_emissao", tabela.getValor("dt_venda"));
            contasRP.setValor("valor_total", tabela.getPost().getString("vlr_recebido"));
            contasRP.setValor("tipo_movimento_id", "3");
            contasRP.setValor("conta_id", "1");
            contasRP.setValor("movcontas_id", "0");
            contasRP.setValor("valor_desconto", "0");
            contasRP.setValor("valor_acrescimo", "0");
            contasRP.setValor("observacao", "Recebimento lançado pela tela de vendas");

            contasRP.setCon(tabela.getCon());
            contasRP.update();
        }

    }

    public void triggerContasReceberPagar() {

        int idMovContas = tabela.getValorInteger("movcontas_id");
        int idContas = tabela.getValorInteger("conta_id");

        if ((idMovContas == 0) && (idContas == 0)) {
            return;
        }

        Tabela movContas = DataBase.getMovContas();
        movContas.setValor("id", tabela.getValor("movcontas_id"));
        movContas.setValor("empresa_id", tabela.getValor("empresa_id"));
        movContas.setValor("dt_movto", tabela.getValor("dt_emissao"));
        movContas.setValor("valor", tabela.getValor("valor_total"));
        movContas.setValor("conta_debito_id", "0");
        movContas.setValor("conta_credito_id", tabela.getValor("conta_id"));
        movContas.setValor("observacao", tabela.getValor("observacao"));

        movContas.setCon(tabela.getCon());
        movContas.update();

        if (tabela.getValor("movcontas_id").equals("0")) {
            tabela.setValor("movcontas_id", movContas.getValor("id"));
            tabela.update();
        }

    }

}
