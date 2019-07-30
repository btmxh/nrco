/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.utils.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Welcome
 */
public class I18NBundle extends Properties{
    public static I18NBundle loadI18N(String path) throws IOException {
        InputStream inputStream = I18NBundle.class.getResourceAsStream(path);
        I18NBundle bundle = new I18NBundle();
        bundle.load(inputStream);
        return bundle;
    }
    
    public String format(String string) {
        for (Object objKey : keySet()) {
            String key = (String) objKey;
            String value = getProperty(key);
            string = string.replace("%" + key + "%", value);
        }
        return string;
    }
    
    public static void main(String[] args) throws IOException {
    }
}
