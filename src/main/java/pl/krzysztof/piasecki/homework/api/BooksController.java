package pl.krzysztof.piasecki.homework.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.krzysztof.piasecki.homework.model.AuthorRating;
import pl.krzysztof.piasecki.homework.model.Book;
import pl.krzysztof.piasecki.homework.service.BookService;

import java.util.List;

@RestController
public class BooksController {
    @Autowired
    BookService bookService;

    /**
     * To retrive data from specific ISBN the path is "isbn:{id}"
     * @param id
     * @return
     */
    @GetMapping(path = "isbn:{id}")
    public Book getBooksByIsbn(@PathVariable String id) {
        Book book = bookService.getBookByIsbn(id);
        if (book == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Book not found"
            );
        }
        return book;
    }

    /**
     * To retrive the list of books by category the path is "category:{category}"
     * @param category
     * @return
     */

    @GetMapping(path = "category:{category}")
    public List<Book> getBooksByCategory(@PathVariable String category) {
        List<Book> booksByCategory = bookService.getBookByCategory(category);
        if (booksByCategory.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Books not found"
            );
        }
        return booksByCategory;
    }

    /**
     * To retrive authors by average books written rating the path is "authors_rating"
     * @return
     */
    @GetMapping(path = "authors_rating")
    public List<AuthorRating> getBooksByAuthorRatings() {
        List<AuthorRating> bookList = bookService.getAuthorsAverageRatings();
        if (bookList.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Books not found"
            );
        }
        return bookList;
    }

    /**
     * To retrive book for the maximum pages the path is "pages:{pages}"
     * @param pages
     * @return
     */
    @GetMapping(path = "pages:{pages}")
    public Book getBookByPages(@PathVariable Integer pages) {
        Book book = bookService.getBookByPageNumber(pages);
        if (book == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Book not found"
            );
        }
        return book;
    }

    /**
     * To retrive data for list of book by the user reading speed and daily hours for reading
     * the path is best/pace:{pages}/daily:{hours}
     * @param pages
     * @param hours
     * @return
     */
    @GetMapping(path = "best/pace:{pages}/daily:{hours}")
    public List<Book> getBooksByReadingSkills(@PathVariable Integer pages, @PathVariable Integer hours) {
        List<Book> bookList = bookService.getBooksByReadingSkills(pages, hours);
        if (bookList.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Books not found"
            );
        }
        return bookList;
    }
}
