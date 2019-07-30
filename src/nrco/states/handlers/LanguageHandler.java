/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco.states.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nrco.MainGame;
import nrco.utils.i18n.I18NBundle;

/**
 *
 * @author Welcome
 */
public class LanguageHandler {
    private List<I18NBundle> languages;
    private int currentIndex;

    public LanguageHandler() throws IOException {
        languages = new ArrayList<>();
        load("vietnamese");
        load("english");
    }
    
    public String format(String string) {
        return languages.get(currentIndex).format(string);
    }

    private void load(String lang) throws IOException {
        I18NBundle bundle = I18NBundle.loadI18N("/lang/" + lang + ".properties");
        languages.add(bundle);
    }

    public void set(int langIndex) {
        currentIndex = langIndex;
    }

    public List<I18NBundle> getAllLanguages() {
        return languages;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
    
    
}
