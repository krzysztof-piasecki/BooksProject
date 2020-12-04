package pl.krzysztof.piasecki.homework.reader.impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.krzysztof.piasecki.homework.reader.JsonReader;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class APIJsonReader implements JsonReader {
    private String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=inpublisher:Penguin&maxResults=40&key=AIzaSyA4ELtY-uh6xUjFXvZ3_gqwIYQl9K2Iumo";

    @Override
    public JSONObject getData() {
        JSONObject jsonObject = null;
        InputStream inputStream = null;
        try {
            try {
                inputStream = new URL(apiUrl).openStream();

                BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                JSONParser parser = new JSONParser();
                Object obj = null;
                obj = parser.parse(jsonText);
                jsonObject = (JSONObject) obj;
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}

