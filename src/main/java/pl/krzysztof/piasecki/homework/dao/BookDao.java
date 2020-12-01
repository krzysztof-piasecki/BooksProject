package pl.krzysztof.piasecki.homework.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.krzysztof.piasecki.homework.model.Book;
import pl.krzysztof.piasecki.homework.serializator.BookSerializeObject;
import pl.krzysztof.piasecki.homework.reader.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class BookDao {
    @Autowired
    JsonReader jsonReader;
    JSONParser parser = new JSONParser();

    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Iterator<JSONObject> iterator = readBooksFromFile();
            while (iterator.hasNext()) {
                Book book = objectMapper.readValue(iterator.next().toString(), BookSerializeObject.class);
                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public Iterator<JSONObject> readBooksFromFile() throws IOException, ParseException {
        JSONObject jsonObject = jsonReader.getData();
        JSONArray books = (JSONArray) jsonObject.get("items");
        return books.iterator();
    }
}
