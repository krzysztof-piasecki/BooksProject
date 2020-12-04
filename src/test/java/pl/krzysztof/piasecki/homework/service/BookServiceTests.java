package pl.krzysztof.piasecki.homework.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.krzysztof.piasecki.homework.BaseTests;
import pl.krzysztof.piasecki.homework.model.AuthorRating;
import pl.krzysztof.piasecki.homework.model.Book;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@ActiveProfiles("test")
public class BookServiceTests extends BaseTests {

    @Autowired
    BookService bookService;
    @Test
    public void getBookByIsbn() {
        //given
        String correctIsbn = "9780226285108";
        Book actual = bookService.getBookByIsbn(correctIsbn);
        Book expected = getSingleBookMockDataWithCorrectIsbn();

        //then
        assertEquals(expected, actual);

        //given
        String idIsbn = "y6QNAAAAQAAJ";
        expected = getSingleBookMockDataWithIdIsbn();

        assertNotEquals(expected, actual);

        actual = bookService.getBookByIsbn(idIsbn);

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
        assertFalse(expectedBooks.size() == actualBooks.size() + 1);
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void getAuthorsAverageRatings() {
        //given
        List<AuthorRating> authorRatingsList = bookService.getAuthorsAverageRatings();

        //then
        assertEquals(getMockDataForAuthorsRatings(), authorRatingsList);
        assertEquals(15, authorRatingsList.size());
        }

    @Test
    public void getBooksByPageNumber() {
        //given
        int maxPages = 391;
        Book book = bookService.getBookByPageNumber(maxPages);

        //then
        assertEquals(getSingleBookMockDataWithCorrectIsbn(), book);
    }

    @Test
    public void getBooksByReadingSkills(){
        //given
        int numberOfPagesPerHour = 20;
        int averageNumberOfHoursPerDay = 2;
        List<Book> bookList = bookService.getBooksByReadingSkills(numberOfPagesPerHour, averageNumberOfHoursPerDay);

        //then
        assertEquals(2, bookList.size());
    }

}
