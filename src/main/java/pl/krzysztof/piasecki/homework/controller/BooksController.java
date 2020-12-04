package pl.krzysztof.piasecki.homework.controller;

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
