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
        InputStream bookStream = null;
        if (!"misc/book.json" .equals(path)) {
            try {
                bookStream = new FileInputStream(path);
            } catch (FileNotFoundException e) {
                e.getStackTrace();
            }
        } else {
            ClassLoader classLoader = getClass().getClassLoader();
            bookStream = classLoader.getResourceAsStream(path);
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) parser.parse(
                    new InputStreamReader(bookStream, "UTF-8"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }
}
