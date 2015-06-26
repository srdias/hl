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

public class cadMovContas extends templateCadastros {

    @Override
    public void builder() {
        setArquivo("movContas.jsp");

        formulario.setTitulo("Movimento de Contas");
        formulario.addFieldObject(new formFieldText("Codigo:", "id"));
        formulario.addFieldObject(new formFieldSelect("Conta crédito:", "conta_credito_id", "page.contas", "id", "nome"));
        formulario.addFieldObject(new formFieldSelect("Conta débito:", "conta_debito_id", "page.contas", "id", "nome"));
        formulario.addFieldObject(new formFieldText("Data:", "dt_movto"));
        formulario.addFieldObject(new formFieldText("Valor:", "valor"));
        formulario.addFieldObject(new formFieldText("Observações:", "observacao"));
        formulario.addButtonObject(new formButton("Gravar", "page.actionSave()"));
        formulario.addButtonObject(new formButton("Voltar", "page.actionVoltar()"));

        formulario.fielSetVisible("id", false);

        tabela.addColuna(new TableColuna("id", "Código"));
        tabela.addColuna(new TableColuna("conta_debito_id.nome", "Conta debito"));
        tabela.addColuna(new TableColuna("conta_credito_id.nome", "Conta crédito"));
        tabela.addColuna(new TableColuna("dt_movto", "Data Venda", TableColuna.ALIGN_CENTER));
        tabela.addColuna(new TableColuna("valor", "Valor", TableColuna.ALIGN_RIGTH));
        tabela.addColuna(new TableColuna("observacao", "Observações"));
    }
}

