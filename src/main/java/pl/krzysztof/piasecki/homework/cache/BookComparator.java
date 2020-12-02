package pl.krzysztof.piasecki.homework.cache;

import pl.krzysztof.piasecki.homework.model.Book;

import java.util.Comparator;
import java.util.Map;

public class BookComparator implements Comparator<Map.Entry<String, Book>> {
    @Override
    public int compare(Map.Entry<String, Book> o1, Map.Entry<String, Book> o2) {
        return o1.getValue().getAddedTime().compareTo(o2.getValue().getAddedTime());
    }
}
