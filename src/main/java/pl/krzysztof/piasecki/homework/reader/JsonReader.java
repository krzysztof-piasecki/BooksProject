package pl.krzysztof.piasecki.homework.reader;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public interface JsonReader {
    JSONObject getData();

}
