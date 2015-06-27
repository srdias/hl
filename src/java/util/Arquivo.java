package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Arquivo {

    public static String ler(String arquivo) {

        String lsNome = "C:\\Users\\u0180759\\Fontes\\Acompanhamento\\web\\sql\\"+arquivo;
        StringBuilder retorno = new StringBuilder();

        BufferedReader br = null;

        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(lsNome));
            while ((sCurrentLine = br.readLine()) != null) {
                retorno.append(sCurrentLine);
                retorno.append("\r\n");
            }
        } catch (IOException e) {
            retorno = new StringBuilder("null");
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }

        return retorno.toString();
    }
}
