/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hl;

import forms.cadCidades;
import forms.cadClientes;
import forms.cadContas;
import forms.cadContasReceberPagar;

/**
 *
 * @author AdrianoNB
 */
public class builder {

    public static void main(String args[]) {
        builder.gerar(new cadContas());
        builder.gerar(new cadClientes());
        builder.gerar(new cadCidades());
        builder.gerar(new cadContasReceberPagar());
    }

    public static void gerar(templateCadastros template) {
        
        String conteudo = template.lerArquivo();

        template.builder();

        conteudo = conteudo.replaceAll("<!-- FormCadastro -->", template.formulario.buildForm());
        conteudo = conteudo.replaceAll("<!-- Listagem -->", template.tabela.buildTable());

        template.gravarArquivo(conteudo);
        
    }
}
