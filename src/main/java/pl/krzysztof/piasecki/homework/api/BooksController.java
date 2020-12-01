package pl.krzysztof.piasecki.homework.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.krzysztof.piasecki.homework.model.AuthorRating;
import pl.krzysztof.piasecki.homework.model.Book;
import pl.krzysztof.piasecki.homework.service.BookService;

import java.util.List;

@RestController

public class BooksController {
    @Autowired
    BookService bookService;

    @GetMapping(path = "{id}")
    public Book getBooksByIsbn(@PathVariable String id){
        return bookService.getBookByIsbn(id);
    }

    @GetMapping(path = "category/{categories}")
    public List<Book> getBooksByCategory(@PathVariable String categories){
        return bookService.getBookByCategory(categories);
    }

    @GetMapping(path = "authors_rating")
    public List<AuthorRating> getBooksByCategory(){
        return bookService.getAuthorsAverageRatings();
    }
}
