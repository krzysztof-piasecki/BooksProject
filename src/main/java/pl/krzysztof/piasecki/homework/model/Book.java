package pl.krzysztof.piasecki.homework.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import pl.krzysztof.piasecki.homework.serializator.BookDeserializer;

@JsonDeserialize(using = BookDeserializer.class)
public class Book extends BookModel {
}
