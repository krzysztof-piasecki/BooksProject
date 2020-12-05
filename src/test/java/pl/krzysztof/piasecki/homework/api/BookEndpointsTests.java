package pl.krzysztof.piasecki.homework.api;

import io.restassured.RestAssured;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import pl.krzysztof.piasecki.homework.BaseTests;
import pl.krzysztof.piasecki.homework.service.BookService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookEndpointsTests extends BaseTests {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    BookService bookService;

    @Test
    public void getBookByIsbn() throws JSONException {
        port = randomServerPort;
        //given
        String path = "/isbn:{id}";
        String id = "9780226285108";
        String title = "The Religion of Java";
        String publisher = "University of Chicago Press";
        Long publishedDate = 193186800000L;
        String description = "Written with a rare combination of analysis and speculation, this comprehensive study of " +
                "Javanese religion is one of the few books on the religion of a non-Western people which emphasizes " +
                "variation and conflict in belief as well as similarity and harmony. The reader becomes aware of the " +
                "intricacy and depth of Javanese spiritual life and the problems of political and social integration " +
                "reflected in the religion. The Religion of Java will interest specialists in Southeast Asia, " +
                "anthropologists and sociologists concerned with the social analysis of religious belief and ideology, " +
                "students of comparative religion, and civil servants dealing with governmental policy toward Indonesia " +
                "and Southeast Asia.";

        Integer pageCount = 392;
        String language = "en";
        Float averageRating = 4.0f;
        String authors = "Clifford Geertz";
        String categories = "Religion";

        //then

        when().
                get(path, id).
                then().
                statusCode(200).
                body("isbn", equalTo(id)).
                body("title", equalTo(title)).
                body("publisher", equalTo(publisher)).
                body("publishedDate", equalTo(publishedDate)).
                body("description", equalTo(description)).
                body("pageCount", equalTo(pageCount)).
                body("language", equalTo(language)).
                body("averageRating", equalTo(averageRating)).
                body("authors", hasItem(authors)).
                body("categories", hasItem(categories));
    }


    @Test
    public void getAuthorsRatings() {
        port = randomServerPort;
        when().
                get("/authors-rating").
                then().
                statusCode(200).
                body("size()", is(15));
    }

    @Test
    public void getBooksByPageNumber() {
        port = randomServerPort;
        int maxPages = 391;
        get("/pages:{maxPages}", maxPages).
                then().
                statusCode(200).
                body("isbn", is("9780226285108"));
    }

    @Test
    public void getBooksByReadingSkills() {
        port = randomServerPort;
        int pace = 20;
        int daily = 2;
        Map<String, Integer> params =  new HashMap<>() {
            {
                put("pace", pace);
                put("daily", daily);
            }
        };
        given().
                pathParams(params).
        when().
                get("/best-pace:{pace}/daily:{daily}").
                then().
                statusCode(200).
                body("size()", is(2));
    }

    @Test
    public void getBookByCategory() {
        port = randomServerPort;
        String category = "Religion";
        when().
                get("/category:{category}", category).
                then().
                statusCode(200).
                body("size()", is(1));
    }

    @Test
    public void getRecentlyViewedBooks() {
        port = randomServerPort;
        String isbn = "9780226285108";
        get("/isbn:{id}",isbn).
                then().
                statusCode(200);
        when().
                get("/recently-viewed-books").
                then().
                statusCode(200).
                body("isbn", hasItem(isbn));
    }
}
