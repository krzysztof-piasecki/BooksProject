package pl.krzysztof.piasecki.homework.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.krzysztof.piasecki.homework.BaseTests;
import pl.krzysztof.piasecki.homework.model.Book;
import pl.krzysztof.piasecki.homework.serializator.BookSerializeObject;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class BookDaoTests extends BaseTests {

    List<Book> bookList = new ArrayList<>();
    @Before
    public void getBooks() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(readBookFile()));

            assertNotNull("Object doesn't exist", obj);

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray books = (JSONArray) jsonObject.get("items");
            assertNotNull("\"items\" tag does not exist", books);


            Iterator<JSONObject> iterator = books.iterator();
            while (iterator.hasNext()) {
                Book book = objectMapper.readValue(iterator.next().toString(), BookSerializeObject.class);
                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkBookData(){
        assertEquals(40,bookList.size());
    }
}
