package pl.krzysztof.piasecki.homework.controller;

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

    @GetMapping(path = "isbn:{id}")
    public Book getBooksByIsbn(@PathVariable String id){
        return bookService.getBookByIsbn(id);
    }

    @GetMapping(path = "category:{category}")
    public List<Book> getBooksByCategory(@PathVariable String category){
        return bookService.getBookByCategory(category);
    }

    @GetMapping(path = "authors_rating")
    public List<AuthorRating> getBooksByAuthorRatings(){
        return bookService.getAuthorsAverageRatings();
    }

    @GetMapping(path = "pages:{pages}")
    public Book getBookByPages(@PathVariable Integer pages){
        return bookService.getBookByPageNumber(pages);
    }

    @GetMapping(path = "best/pace:{pages}/daily:{hours}")
    public List<Book> getBooksByReadingSkills(@PathVariable Integer pages, @PathVariable Integer hours){
        return bookService.getBooksByReadingSkills(pages,hours);
    }
}
