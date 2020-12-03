package pl.krzysztof.piasecki.homework;

import org.junit.Before;
import org.junit.Test;
import pl.krzysztof.piasecki.homework.model.Book;
import pl.krzysztof.piasecki.homework.utils.ParamInitializer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BaseTests {
    protected String[] args;
    @Before
    public void initializeTestData() {
        args = new String []{"-Ddatasource=misc/books.json"};
    }

    public File readBookFile() {
        ParamInitializer.getInstance().putParams(args);

        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(ParamInitializer.getInstance().getParam("datasource")).getFile());
    }

    public Book getSingleBookMockData(){
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
    public List<Book> getMockDataForReligionCategory(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(getSingleBookMockData());
        return bookList;
    }
}
