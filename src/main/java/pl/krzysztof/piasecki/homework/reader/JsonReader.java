package pl.krzysztof.piasecki.homework.reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.api.client.util.Value;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public interface JsonReader {
    JSONObject getData();

}
