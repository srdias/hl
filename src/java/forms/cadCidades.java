/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import hl.AngularJS.TableColuna;
import hl.AngularJS.formButton;
import hl.AngularJS.formFieldText;
import hl.templateCadastros;

/**
 *
 * @author AdrianoNB
 */
public class cadCidades extends templateCadastros{

    @Override
    public void builder() {

        setArquivo("cadCidades.jsp");
        
        formulario.setTitulo("Contas");
        formulario.addFieldObject(new formFieldText("Codigo:", "id"));
        formulario.addFieldObject(new formFieldText("Nome:", "nome"));
        formulario.addButtonObject(new formButton("Gravar", "page.actionSave()"));
        formulario.addButtonObject(new formButton("Voltar", "page.actionVoltar()"));

        tabela.addColuna(new TableColuna("id", "Codigo"));
        tabela.addColuna(new TableColuna("nome", "Nome da Cidade"));
    }

}
