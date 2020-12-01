package pl.krzysztof.piasecki.homework.serializator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import pl.krzysztof.piasecki.homework.model.Book;

@JsonDeserialize(using = BookDeserializer.class)
public class BookSerializeObject extends Book {
}
