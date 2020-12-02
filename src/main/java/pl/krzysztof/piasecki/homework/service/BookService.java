package pl.krzysztof.piasecki.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzysztof.piasecki.homework.cache.BookCacheImpl;
import pl.krzysztof.piasecki.homework.dao.BookDao;
import pl.krzysztof.piasecki.homework.model.AuthorRating;
import pl.krzysztof.piasecki.homework.model.Book;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public Book getBookByIsbn (String id){
        Book book = BookCacheImpl.get(id);
        if (book != null){
            BookCacheImpl.put(book);
            return book;
        }else{
            List<Book> bookList = bookDao.getAllBooks();
            Optional<Book> optionalBook = bookList.stream().filter(e -> id.equals(e.getIsbn())).findFirst();
            optionalBook.ifPresent(b -> BookCacheImpl.put(b));
            return optionalBook.isPresent() ? optionalBook.get() : null;
        }
    }

    public List<Book> getBookByCategory (String category){
        List<Book> bookList = bookDao.getAllBooks();
        List<Book> booksByCategory = bookList.stream().filter(e -> e.getCategories().contains(category)).collect(Collectors.toList());
        return booksByCategory;
    }

    public List<AuthorRating> getAuthorsAverageRatings() {
        List<Book> bookList = bookDao.getAllBooks();
        Map<String, List<Double>> authorsAllBookRatings = new HashMap<>();
        for (Book book : bookList) {
            for (String str : book.getAuthors()) {
                authorsAllBookRatings.computeIfAbsent(str, k -> new ArrayList<>()).add(book.getAverageRating());
            }
        }
        List<AuthorRating> authorRatings = new ArrayList<>();
        Double authorsAverageRating = null;
        for (Map.Entry<String, List<Double>> entryRatings : authorsAllBookRatings.entrySet()) {
            authorsAverageRating = computeAverageRating(entryRatings.getValue());
            if (computeAverageRating(entryRatings.getValue()) != null) {
                authorRatings.add(new AuthorRating(entryRatings.getKey(), authorsAverageRating));
            }
        }
        return authorRatings;
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

    public Book getBookByPageNumber (int pageNumber){
        List<Book> bookList = bookDao.getAllBooks();
        Optional<Book> book = bookList.stream().filter(e -> e.getPageCount() > pageNumber).findFirst();
        return book.isPresent() ? book.get() : null;
    }

    public List<Book> getBooksByReadingSkills (int numberOfPagesPerHour, int averageNumberOfHoursPerDay){
        List<Book> bookList = bookDao.getAllBooks();
        int averagePagesPerMonth = numberOfPagesPerHour * averageNumberOfHoursPerDay * 30;
        bookList.sort(Comparator.comparing(Book::getAverageRating));
        List <Book> booksByReadingSkillsWithHighestRatings = new ArrayList<>();
        int numberOfPages = 0;
        for (Book book: bookList){
            if(book.getPageCount() < averagePagesPerMonth - numberOfPages){
                booksByReadingSkillsWithHighestRatings.add(book);
            }
        }
        return booksByReadingSkillsWithHighestRatings;
    }

}
