/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hl.AngularJS;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;

/**
 *
 * @author AdrianoNB
 */
public class Table extends Html {

    private String jsonData;
    ArrayList<TableColuna> colunas;

    public Table(String jsonData) {
        colunas = new ArrayList<>();
        this.jsonData = jsonData;
    }

    public void addColuna(TableColuna coluna) {
        colunas.add(coluna);
    }

    public String buildTable(){
        StringBuilder sb = new StringBuilder();

        sb.append("<table class=\"table table-bordered table-striped table-hover\">\n");

        sb.append("<thead>\n");
        sb.append("<tr>\n");
        for (TableColuna coluna : colunas) {
            sb.append("<th>");
            sb.append(coluna.getLabel());
            sb.append("</th>\n");
        }
        sb.append("</tr>\n");
        sb.append("</thead>\n");

        sb.append("<tbody>\n");
        sb.append("<tr ");
        sb.append(getProp("ng-repeat", "item in " + getJsonData()));
        sb.append(" ");
        sb.append(getProp("ng-click", "page.actionEditar(item.id)"));
        sb.append(">\n");

        for (TableColuna coluna : colunas) {
            sb.append("<td>{{item.");
            sb.append(coluna.getName());
            sb.append("}}</td>\n");
        }

        sb.append("</tr>\n");
        sb.append("</tbody>\n");
        sb.append("</table>\n");

        return sb.toString();
    }

    public String getJsonData() {
        return jsonData;
    }

}
