package pl.krzysztof.piasecki.homework.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import static org.junit.Assert.*;

import org.junit.Test;
import pl.krzysztof.piasecki.homework.BaseTests;
import pl.krzysztof.piasecki.homework.model.BookModel;
import pl.krzysztof.piasecki.homework.model.Book;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookModelDaoTests extends BaseTests {
    List<BookModel> bookList = new ArrayList<>();
    @Test
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
                BookModel bookModel = objectMapper.readValue(iterator.next().toString(), Book.class);
                bookList.add(bookModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    @Test
//    public void checkBookData(){
//        assertEquals(1,bookList.size());
//        BookModel bookModel = bookList.get(0);
//        assertNotNull(bookModel);
//        assertEquals("", bookModel.getIsbn());
//
//
//    }

}
