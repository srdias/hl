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
public class cadClientes extends templateCadastros {

    @Override
    public void builder() {
        
        setArquivo("cadClientes.jsp");

        formulario.setTitulo("Clientes");
        formulario.addFieldObject(new formFieldText("Codigo:", "id"));
        formulario.addFieldObject(new formFieldText("Nome:", "nome"));
        formulario.addFieldObject(new formFieldText("Endereço:", "endereco"));
        formulario.addFieldObject(new formFieldSelect("Cidade:", "cidade_id", "page.cidades", "id", "nome"));
        formulario.addFieldObject(new formFieldText("Preço:", "preco"));
        formulario.addFieldObject(new formFieldText("Taxas:", "taxas"));
        formulario.addButtonObject(new formButton("Gravar", "page.actionSave()"));
        formulario.addButtonObject(new formButton("Voltar", "page.actionVoltar()"));

        tabela.addColuna(new TableColuna("id", "Código"));
        tabela.addColuna(new TableColuna("nome", "Nome"));
        tabela.addColuna(new TableColuna("endereco", "Endereço"));
        tabela.addColuna(new TableColuna("cidade_id.nome", "Cidade"));
        tabela.addColuna(new TableColuna("preco", "Preços", TableColuna.ALIGN_RIGTH));
        tabela.addColuna(new TableColuna("taxas", "Taxas", TableColuna.ALIGN_RIGTH));
    }

}
