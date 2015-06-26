package hl;

import hl.AngularJS.Table;
import hl.AngularJS.form;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class templateCadastros {

    public form formulario;
    public Table tabela;
    private String arquivo;

    public abstract void builder();

    public templateCadastros() {
        formulario = new form();
        tabela = new Table("page.registro");
    }

    public void gravarArquivo(String conteudo) {
        PrintWriter writer;
        try {
            writer = new PrintWriter("C:\\fontes\\hl\\web\\templates\\" + getArquivo(), "ISO-8859-1");
//            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("C:\\fontes\\hl\\web\\templates\\" + getArquivo()),"UTF-8");
            writer.write(conteudo);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(templateCadastros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(templateCadastros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String lerArquivo() {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        try {

            String sCurrentLine;
            String sQuebra = "";

            br = new BufferedReader(new FileReader("C:\\fontes\\hl\\web\\templates\\modelo.jsp"));

            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sQuebra);
                sb.append(sCurrentLine);
                sQuebra = "\r\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return sb.toString();
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

}
