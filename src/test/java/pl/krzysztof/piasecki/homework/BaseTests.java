package pl.krzysztof.piasecki.homework;

import org.junit.Before;
import pl.krzysztof.piasecki.homework.model.AuthorRating;
import pl.krzysztof.piasecki.homework.model.Book;
import pl.krzysztof.piasecki.homework.utils.ParamInitializer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseTests {
    protected String[] args;

    @Before
    public void initializeTestData() {
        args = new String[]{"-Ddatasource=misc/books.json"};
    }

    public File readBookFile() {
        ParamInitializer.getInstance().putParams(args);

        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(ParamInitializer.getInstance().getParam("datasource")).getFile());
    }

    public Book getSingleBookMockDataWithCorrectIsbn() {
        List<String> authors = new ArrayList<>();
        List<String> categories = new ArrayList<>();

        authors.add("Clifford Geertz");
        categories.add("Religion");
        return new Book.Builder()
                .withIsbn("9780226285108")
                .withTitle("The Religion of Java")
                .withPublisher("University of Chicago Press")
                .withPublishedDate(193186800000L)
                .withDescription("Written with a rare combination of analysis and speculation, this comprehensive study of Javanese religion is one of the few books on the religion of a non-Western people which emphasizes variation and conflict in belief as well as similarity and harmony. The reader becomes aware of the intricacy and depth of Javanese spiritual life and the problems of political and social integration reflected in the religion. The Religion of Java will interest specialists in Southeast Asia, anthropologists and sociologists concerned with the social analysis of religious belief and ideology, students of comparative religion, and civil servants dealing with governmental policy toward Indonesia and Southeast Asia.")
                .withPageCount(392)
                .withThumbnailUrl("http://books.google.com/books/content?id=-SYM4PW-YAgC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api")
                .withLanguage("en")
                .withPreviewLink("http://books.google.pl/books?id=-SYM4PW-YAgC&printsec=frontcover&dq=java&hl=&cd=2&source=gbs_api")
                .withAverageRating(4.0)
                .withAuthors(authors)
                .withCategories(categories)
                .build();
    }

    public List<Book> getMockDataForReligionCategory() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(getSingleBookMockDataWithCorrectIsbn());
        return bookList;
    }

    public Book getSingleBookMockDataWithIdIsbn() {
        List<String> authors = new ArrayList<>();
        List<String> categories = new ArrayList<>();

        authors.add("James William Bayley Money");
        categories.add("Great Britain");
        return new Book.Builder()
                .withIsbn("y6QNAAAAQAAJ")
                .withTitle("Java")
                .withPublishedDate(-3439674000000L)
                .withSubtitle("Or, How to Manage a Colony. Showing a Practical Solution of the Questions Now Affecting British India")
                .withThumbnailUrl("http://books.google.com/books/content?id=y6QNAAAAQAAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api")
                .withLanguage("en")
                .withPreviewLink("http://books.google.pl/books?id=y6QNAAAAQAAJ&pg=PP1&dq=java&hl=&cd=6&source=gbs_api")
                .withAverageRating(1.0)
                .withAuthors(authors)
                .withCategories(categories)
                .build();
    }

    public List<AuthorRating> getMockDataForAuthorsRatings() {
        return Arrays.asList(
                new AuthorRating("Jain Pravin", 5.0),
                new AuthorRating("BUYYA", 5.0),
                new AuthorRating("Kathy Sierra", 4.5),
                new AuthorRating("Bert Bates", 4.5),
                new AuthorRating("Sir Thomas Stamford Raffles", 4.5),
                new AuthorRating("David Flanagan", 4.0),
                new AuthorRating("Kenneth L. Calvert", 4.0),
                new AuthorRating("Clifford Geertz", 4.0),
                new AuthorRating("Douglas Lea", 4.0),
                new AuthorRating("Eric Burke", 4.0),
                new AuthorRating("Bruce Eckel", 4.0),
                new AuthorRating("Michael J. Donahoo", 4.0),
                new AuthorRating("Barry Burd", 3.5),
                new AuthorRating("George Reese", 3.5),
                new AuthorRating("James William Bayley Money", 1.0)
        );
    }
}
