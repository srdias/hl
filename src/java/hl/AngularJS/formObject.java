/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hl.AngularJS;

import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author AdrianoNB
 */
public abstract class formObject extends Html {

    private Properties props;

    @Override
    public abstract String toString();

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public void putProp(String nome, String valor) {
        if (props == null) {
            props = new Properties();
        }
        props.put(nome, valor);
    }

    public String getProperties() {
        
        if(props==null) return "";
        
        Enumeration e = props.propertyNames();
        StringBuilder sb = new StringBuilder();

        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            sb.append(getProp(key, props.getProperty(key)));
            sb.append(" ");
        }
        return sb.toString();
    }

}
