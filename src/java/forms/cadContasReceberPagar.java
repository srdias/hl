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
public class cadContasReceberPagar extends templateCadastros {

    @Override
    public void builder() {
        setArquivo("cadContasReceberPagar.jsp");

        formulario.setTitulo("Clientes");
        formulario.addFieldObject(new formFieldText("Codigo:", "id"));
        formulario.addFieldObject(new formFieldSelect("Empresa:", "empresa_id", "page.clientes", "id", "nome"));
        formulario.addFieldObject(new formFieldText("Emissão:", "dt_emissao"));
        formulario.addFieldObject(new formFieldSelect("Tipo movimento:", "tipo_movimento_id", "page.tipo_movimento", "id", "nome"));
        formulario.addFieldObject(new formFieldSelect("Conta:", "conta_id", "page.contas", "id", "nome"));
        formulario.addFieldObject(new formFieldText("Desconto:", "valor_desconto"));
        formulario.addFieldObject(new formFieldText("Acrescimo:", "valor_acrescimo"));
        formulario.addFieldObject(new formFieldText("Total:", "valor_total"));
        formulario.addFieldObject(new formFieldText("Observações:", "observacao"));
        formulario.addButtonObject(new formButton("Gravar", "page.actionSave()"));
        formulario.addButtonObject(new formButton("Voltar", "page.actionVoltar()"));

        tabela.addColuna(new TableColuna("id", "Código"));
        tabela.addColuna(new TableColuna("empresa_id.nome", "Empresa"));
        tabela.addColuna(new TableColuna("dt_emissao", "Emissão", TableColuna.ALIGN_CENTER));
        tabela.addColuna(new TableColuna("tipo_movimento_id.nome", "Tipo movimento"));
        tabela.addColuna(new TableColuna("conta_id.nome", "Conta"));
        tabela.addColuna(new TableColuna("valor_total", "Valor Total", TableColuna.ALIGN_RIGTH));
        tabela.addColuna(new TableColuna("observacao", "Observações"));
    }
}
