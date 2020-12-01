package pl.krzysztof.piasecki.homework.reader.impl;

import org.json.simple.JSONObject;
import pl.krzysztof.piasecki.homework.reader.JsonReader;

public class APIJsonReader implements JsonReader {
    private String path;
    public APIJsonReader(String path) {
        this.path = path;
    }
    @Override
    public JSONObject getData() {
        return null;
    }
}
