package pl.krzysztof.piasecki.homework.dao;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.krzysztof.piasecki.homework.BaseTests;
import pl.krzysztof.piasecki.homework.model.Book;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class BookDaoTests extends BaseTests {

    @Autowired
    BookDao bookDao;

    @Test
    public void getAllBooks() {
        //given
        List<Book> bookList = bookDao.getAllBooks();

        //then
        Assert.assertEquals(40, bookList.size());
        Assert.assertTrue(!bookList.stream().anyMatch(e -> e.getIsbn().equals(null)));
    }

//    @Test
//    public void checkBookData(){
//        assertEquals(40,bookList.size());
//    }


}
