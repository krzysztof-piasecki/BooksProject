package pl.krzysztof.piasecki.homework.cache;

import pl.krzysztof.piasecki.homework.model.Book;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class BookCacheImpl {
    private static BookCacheImpl lruBookCache = null;

    public static BookCacheImpl getInstance() {
        if (lruBookCache == null) {
            lruBookCache = new BookCacheImpl();
        }
        return lruBookCache;
    }

    static ConcurrentMap<String, Book> cacheMap = new ConcurrentHashMap<>();
    public static synchronized void put(Book book) {
        Calendar cal = Calendar.getInstance();
        if(cacheMap.size() > 4) {
            cacheMap.remove(cacheMap.entrySet().stream().sorted(new BookComparator()).findFirst().get().getKey());
        }
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, -1);
        book.setAddedTime(cal.getTimeInMillis());
        cacheMap.put(book.getIsbn(), book);
    }

    public static synchronized Book get(String isbn) {
        return cacheMap.get(isbn);
    }

    public static synchronized List<Book> getAll(){
        List<Book> bookList = new ArrayList<>();
        if(!cacheMap.isEmpty()){
            for (Book book : cacheMap.values()){
                bookList.add(book);
            }
        }
        return bookList;
    }
    static {
        new Thread(() -> {
            try {
                while (true) {
                    cacheMap.clear();
                    Thread.sleep(TimeUnit.MINUTES.toMillis(30));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
