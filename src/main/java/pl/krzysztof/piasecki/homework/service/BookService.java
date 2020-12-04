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

    public Book getBookByIsbn(String id) {
        Book book = BookCacheImpl.get(id);
        if (book != null) {
            BookCacheImpl.put(book);
            return book;
        } else {
            List<Book> bookList = bookDao.getAllBooks();
            Optional<Book> optionalBook = bookList.stream().filter(e -> id.equals(e.getIsbn())).findFirst();
            optionalBook.ifPresent(BookCacheImpl::put);
            return optionalBook.orElse(null);
        }
    }

    public List<Book> getBookByCategory(String category) {
        List<Book> bookList = bookDao.getAllBooks();
        return bookList.stream().filter(e -> e.getCategories().contains(category)).collect(Collectors.toList());
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
        //Sorting Authors by rating
        authorRatings.sort(Comparator.comparing(AuthorRating::getAverageRating).reversed());
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

    public Book getBookByPageNumber(Integer pageNumber) {
        List<Book> bookList = bookDao.getAllBooks();
        Optional<Book> book = bookList.stream().filter(e -> e.getPageCount() != null && e.getPageCount()> pageNumber).findFirst();
        return book.orElse(null);
    }

    public List<Book> getBooksByReadingSkills(Integer numberOfPagesPerHour, Integer averageNumberOfHoursPerDay) {
        List<Book> bookList = bookDao.getAllBooks();
        List<Book> booksByReadingSkillsWithHighestRatings = new ArrayList<>();
        Integer averagePagesPerMonth = numberOfPagesPerHour * averageNumberOfHoursPerDay * 30;

        bookList = bookList.stream().filter(e -> e.getAverageRating() != null).filter(e -> e.getPageCount() != null).collect(Collectors.toList());
        bookList.sort(Comparator.comparing(Book::getAverageRating).reversed());

        Integer minPages = bookList.get(bookList.size() - 1).getPageCount();
        Integer numberOfPages = 0;

        for (Book book : bookList) {
            if (averagePagesPerMonth - numberOfPages < minPages) {
                break;
            } else {
                if (book.getPageCount() < averagePagesPerMonth - numberOfPages) {
                    booksByReadingSkillsWithHighestRatings.add(book);
                    numberOfPages += book.getPageCount();
                }
            }
        }
        return booksByReadingSkillsWithHighestRatings;
    }

}
