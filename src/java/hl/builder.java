/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hl;

import forms.cadClientes;
import forms.cadContas;

/**
 *
 * @author AdrianoNB
 */
public class builder {

    public static void main(String args[]) {
        builder.gerar(new cadContas());
        builder.gerar(new cadClientes());
    }

    public static void gerar(templateCadastros template) {
        
        String conteudo = template.lerArquivo();

        template.builder();

        conteudo = conteudo.replaceAll("<!-- FormCadastro -->", template.formulario.buildForm());
        conteudo = conteudo.replaceAll("<!-- Listagem -->", template.tabela.buildTable());

        template.gravarArquivo(conteudo);
        
    }
}
