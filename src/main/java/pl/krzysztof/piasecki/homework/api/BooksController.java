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
    private static final String BOOKSNOTFOUND = "Books not found";
    private static final String BOOKNOTFOUND = "Book not found";
    @Autowired
    BookService bookService;

    /**
     * To retrive data from specific ISBN the path is "/isbn:{id}"
     * @param id
     * @return
     */
    @GetMapping(path = "isbn:{id}")
    public Book getBooksByIsbn(@PathVariable String id) {
        Book book = bookService.getBookByIsbn(id);
        if (book == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, BOOKNOTFOUND
            );
        }
        return book;
    }

    /**
     * To retrive the list of books by category the path is "/category:{category}"
     * @param category
     * @return
     */

    @GetMapping(path = "category:{category}")
    public List<Book> getBooksByCategory(@PathVariable String category) {
        List<Book> booksByCategory = bookService.getBookByCategory(category);
        if (booksByCategory.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, BOOKSNOTFOUND
            );
        }
        return booksByCategory;
    }

    /**
     * To retrive authors by average books written rating the the path in url is "/rating"
     * @return
     */
    @GetMapping(path = "authors-rating")
    public List<AuthorRating> getBooksByAuthorRatings() {
        List<AuthorRating> bookList = bookService.getAuthorsAverageRatings();
        if (bookList.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, BOOKSNOTFOUND
            );
        }
        return bookList;
    }

    /**
     * To retrive book for the maximum pages the the path in url is "/pages:{pages}"
     * @param pages
     * @return
     */
    @GetMapping(path = "pages:{pages}")
    public Book getBookByPages(@PathVariable Integer pages) {
        Book book = bookService.getBookByPageNumber(pages);
        if (book == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, BOOKNOTFOUND
            );
        }
        return book;
    }

    /**
     * To retrive data for list of book by the user reading speed and daily hours for reading
     * the path in url is "/best-pace:{pace}/daily:{daily}"
     * @param pace
     * @param daily
     * @return
     */
    @GetMapping(path = "best-pace:{pace}/daily:{daily}")
    public List<Book> getBooksByReadingSkills(@PathVariable Integer pace, @PathVariable Integer daily) {
        List<Book> bookList = bookService.getBooksByReadingSkills(pace, daily);
        if (bookList.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, BOOKSNOTFOUND
            );
        }
        return bookList;
    }
    /**
     * To retrive data for the recently viewed books
     * the path in url is "/recently-viewed-books"
     * @return
     */
    @GetMapping(path = "recently-viewed-books")
    public List<Book> getRecentlyViewedBooks() {
        List<Book> bookList = bookService.getRecentlyViewedBooks();
        if (bookList.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There isn't any books in cache"
            );
        }
        return bookList;
    }
}
