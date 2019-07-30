/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nrco;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.eclipsesource.json.ParseException;
import com.eclipsesource.json.WriterConfig;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Welcome
 */
public class SaveFile {

    private static SaveFile defaultSaveFile() {
        boolean[] audio = {true, true};
        int[] levels = new int[Level.values().length];
        levels[0] = 0;
        for (int i = 1; i < Level.values().length; i++) {
            levels[i] = -1;
        }
        return new SaveFile(audio, levels, 0);
    }
    
    private boolean[] audio;
    private int[] levels;
    private int languageIndex;

    public SaveFile(boolean[] audio, int[] levels, int languageIndex) {
        this.audio = audio;
        this.levels = levels;
        this.languageIndex = languageIndex;
    }
    
    
    
    public static SaveFile load(String path) throws IOException {
        try {
            JsonValue json = Json.parse(new FileReader(path));
            boolean[] audio = boolArray(json.asObject().get("audio").asArray());
            int[] levels = intArray(json.asObject().get("levels").asArray());
            int languageIndex = json.asObject().getInt("lang", 0);

            return new SaveFile(audio, levels, languageIndex);
        } catch (ParseException ex) {
            System.out.println("Parsing Save File Failed. Using default one instead...");
            return defaultSaveFile();
        }
    }
    
    public void save(String path) throws IOException {
        JsonObject content = Json.object().add("audio", Json.array(audio))
                                          .add("levels", Json.array(levels))
                                          .add("lang", languageIndex);
        
        FileWriter writer = new FileWriter(path);
        content.writeTo(writer, WriterConfig.PRETTY_PRINT);
        writer.flush();
    }
    
    public static SaveFile create(MainGame game) {
        boolean[] audio = {game.audioHandler.musicEnabled, game.audioHandler.soundEnabled};
        int[] levels = game.levelHandler.levels;
        int languageIndex = game.languageHandler.getCurrentIndex();
        
        return new SaveFile(audio, levels, languageIndex);
    }
    
    
    
    private static boolean[] boolArray(JsonArray array) {
        List<JsonValue> list = array.values();
        boolean[] bs = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            JsonValue val = list.get(i);
            bs[i] = val.asBoolean();
        }
        return bs;
    }

    private static int[] intArray(JsonArray array) {
        List<JsonValue> list = array.values();
        int[] bs = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            JsonValue val = list.get(i);
            bs[i] = val.asInt();
        }
        return bs;
    }

    public void update(MainGame game) {
        game.audioHandler.musicEnabled = audio[0];
        game.audioHandler.soundEnabled = audio[1];
        
        game.levelHandler.levels = levels;
        
        game.languageHandler.set(languageIndex);
    }
}
