package pl.krzysztof.piasecki.homework.service;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.krzysztof.piasecki.homework.BaseTests;
import pl.krzysztof.piasecki.homework.dao.BookDao;
import pl.krzysztof.piasecki.homework.model.AuthorRating;
import pl.krzysztof.piasecki.homework.model.Book;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class BookServiceTests extends BaseTests {
    @Autowired
    private BookDao bookDao;

    @Test
    public void getBookByIsbn() {
        List<Book> bookList = bookDao.getAllBooks();
        String id = "9780226285108";
        Optional<Book> book = bookList.stream().filter(e -> id.equals(e.getIsbn())).findFirst();
        assertTrue(new ReflectionEquals(getTestData()).matches(book.get()));
    }

    @Test
    public void getBookByCategory() {
        List<Book> bookList = bookDao.getAllBooks();
        String category = "Computers";
        List<Book> booksByCategory = bookList.stream().filter(e -> e.getCategories().contains(category)).collect(Collectors.toList());
        assertEquals(22, booksByCategory.size());
    }

    @Test
    public void getAuthorsAndAverageRatings() {
        List<Book> bookList = bookDao.getAllBooks();
        Map<String, List<Double>> authorsAllBookRatings = new HashMap<>();
        for (Book book : bookList) {
            for (String str : book.getAuthors()) {
                authorsAllBookRatings.computeIfAbsent(str, k -> new ArrayList<>()).add(book.getAverageRating());
            }
        }
        List<AuthorRating> authorRatings = new ArrayList<>();
        for (Map.Entry<String, List<Double>> entryRatings : authorsAllBookRatings.entrySet()) {
            authorRatings.add(new AuthorRating(entryRatings.getKey(), computeAverageRating(entryRatings.getValue())));
        }
        System.out.println(authorRatings);
    }

    private Double computeAverageRating(List<Double> ratings) {
        Double ratingSum = null;
        for (Double rating : ratings) {
            if (null != rating) {
                ratingSum = ratingSum == null ? rating : ratingSum + rating;
            }
        }
        return ratingSum == null ? null : ratingSum / ratings.size();
    }
}
