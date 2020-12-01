package pl.krzysztof.piasecki.homework.reader.impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.krzysztof.piasecki.homework.reader.JsonReader;

import java.io.*;

public class JsonFileReader implements JsonReader {
    private String path;
    public JsonFileReader(String path) {
        this.path = path;
    }
    @Override
    public JSONObject getData() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(file));

        }catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }
}
