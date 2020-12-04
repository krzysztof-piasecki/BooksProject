package pl.krzysztof.piasecki.homework.reader;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface JsonReader {
    JSONObject getData();

}
