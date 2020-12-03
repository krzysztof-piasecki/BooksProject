package pl.krzysztof.piasecki.homework.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.krzysztof.piasecki.homework.BaseTests;
import pl.krzysztof.piasecki.homework.model.AuthorRating;
import pl.krzysztof.piasecki.homework.model.Book;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class BookServiceTests extends BaseTests {

    @Autowired
    BookService bookService;
    @Test
    public void getBookByIsbn() {
        //given
        String id = "9780226285108";
        Book actual = bookService.getBookByIsbn(id);
        Book expected = getSingleBookMockData();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void getBookByCategory() {
        //given
        String category = "Religion";
        List<Book> expectedBooks = bookService.getBookByCategory(category);
        List<Book> actualBooks = getMockDataForReligionCategory();

        //then
        assertTrue(expectedBooks.size() == actualBooks.size());
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void getAuthorsAverageRatings() {
        //given
        List<AuthorRating> authorRatingsList = bookService.getAuthorsAverageRatings();

        //then
        assertTrue(!authorRatingsList.stream().anyMatch(e ->  e.getAverageRating().equals(null)));
        assertEquals(15, authorRatingsList.size());
        }

    @Test
    public void getBooksByPageNumber() {
        //given
        int maxPages = 391;
        Book book = bookService.getBookByPageNumber(maxPages);

        //then
        assertEquals(getSingleBookMockData(), book);
    }

    @Test
    public void getBooksByReadingSkills(){
        //given
        int numberOfPagesPerHour = 20;
        int averageNumberOfHoursPerDay = 2;
        List<Book> bookList = bookService.getBooksByReadingSkills(numberOfPagesPerHour, averageNumberOfHoursPerDay);

        //then
        assertEquals(3, bookList.size());
    }

}
