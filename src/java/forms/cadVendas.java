/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import hl.AngularJS.TableColuna;
import hl.AngularJS.formButton;
import hl.AngularJS.formFieldSelect;
import hl.AngularJS.formFieldText;
import hl.templateCadastros;

/**
 *
 * @author AdrianoNB
 */
public class cadVendas extends templateCadastros {

    @Override
    public void builder() {
        setArquivo("cadVendas.jsp");

        formulario.setTitulo("Vendas");
        formulario.addFieldObject(new formFieldText("Codigo:", "id"));
        formulario.addFieldObject(new formFieldSelect("Empresa:", "empresa_id", "page.clientes", "id", "nome"));
        formulario.addFieldObject(new formFieldText("Data Venda:", "dt_venda"));
        formulario.addFieldObject(new formFieldText("Valor unidade:", "valor_unidade"));
        formulario.addFieldObject(new formFieldText("Quantidade:", "quantidade"));
        formulario.addFieldObject(new formFieldText("Taxas:", "taxas"));
        formulario.addFieldObject(new formFieldText("Total:", "total"));
        formulario.addFieldObject(new formFieldText("Valor recebido:", "vlr_recebido"));
        formulario.addFieldObject(new formFieldText("Observações:", "observacao"));
        formulario.addFieldObject(new formFieldText("ID contas a receber:", "contasPagarReceber_id"));
        formulario.addButtonObject(new formButton("Gravar", "page.actionSave()"));
        formulario.addButtonObject(new formButton("Voltar", "page.actionVoltar()"));

        formulario.fieldObjectAddProp("dt_venda", "ng-change", "changeDataVenda()");
        formulario.fieldObjectAddProp("empresa_id", "ng-change", "chageCliente()");
        formulario.fieldObjectAddProp("quantidade", "ng-change", "chageQtde()");
        formulario.fieldObjectAddProp("valor_unidade", "ng-change", "chageQtde()");
        formulario.fielSetVisible("id", false);
        formulario.fielSetVisible("taxas", false);
        formulario.fielSetVisible("valor_unidade", false);
        formulario.fielSetVisible("contasPagarReceber_id", false);

        //tabela.addColuna(new TableColuna("id", "Código"));
        tabela.addColuna(new TableColuna("empresa_id.nome", "Empresa"));
        tabela.addColuna(new TableColuna("dt_venda", "Data Venda", TableColuna.ALIGN_CENTER));
        tabela.addColuna(new TableColuna("quantidade", "Quantidade", TableColuna.ALIGN_RIGTH));
        //tabela.addColuna(new TableColuna("valor_unidade", "Valor Unidade",TableColuna.ALIGN_RIGTH));
        tabela.addColuna(new TableColuna("taxas", "Taxas", TableColuna.ALIGN_RIGTH));
        tabela.addColuna(new TableColuna("total", "Valor Total", TableColuna.ALIGN_RIGTH));
        tabela.addColuna(new TableColuna("observacao", "Observações"));
        //tabela.addColuna(new TableColuna("contasPagarReceber_id", "contasPagarReceber_id"));
    }
}

/*
 tabela.addColuna(new Coluna("id", java.sql.Types.INTEGER, true));
 tabela.addColuna(new Coluna("empresa_id", java.sql.Types.INTEGER));
 tabela.addColuna(new Coluna("dt_venda", java.sql.Types.DATE));
 tabela.addColuna(new Coluna("valor_unidade", java.sql.Types.DECIMAL));
 tabela.addColuna(new Coluna("quantidade", java.sql.Types.INTEGER));
 tabela.addColuna(new Coluna("taxas", java.sql.Types.DECIMAL));
 tabela.addColuna(new Coluna("total", java.sql.Types.DECIMAL));
 tabela.addColuna(new Coluna("observacao", java.sql.Types.CHAR, 1024));
 tabela.addColuna(new Coluna("contasPagarReceber_id", java.sql.Types.INTEGER));
 */
