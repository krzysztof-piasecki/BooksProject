package pl.krzysztof.piasecki.homework.serializator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pl.krzysztof.piasecki.homework.model.Book;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

public class BookDeserializer extends StdDeserializer<Book> {

    private static final long serialVersionUID = 7923585097068641765L;


    public BookDeserializer()
    {
        super(Book.class);
    }
    @Override
    public Book deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode treeNode = jsonParser.readValueAsTree();
        JsonNode volumeInfoNode = treeNode.get("volumeInfo");
        Book book = new ObjectMapper().readValue(volumeInfoNode.toString(), Book.class);
        if(volumeInfoNode.hasNonNull("industryIdentifiers"))
            book.setIsbn(parseIsbn(treeNode));
        if(volumeInfoNode.hasNonNull("publishedDate"))
            book.setPublishedDate(parseDateToLong(volumeInfoNode));
        if(volumeInfoNode.hasNonNull("imageLinks") && volumeInfoNode.get("imageLinks").hasNonNull("thumbnail")){
            book.setThumbnailUrl(volumeInfoNode.get("imageLinks").get("thumbnail").asText());
        }
        return book;
    }

    private String parseIsbn(JsonNode node) {
        String identifier = node.get("id").asText();
        List<JsonNode> list = node.findParents("identifier");
        Optional<JsonNode> isbn13 = list.stream().filter(e -> e.get("type").asText().endsWith("13")).findFirst();
        if(isbn13.isPresent() && isbn13.get().has("identifier")) {
            identifier = isbn13.get().get("identifier").asText();
        }
        return identifier ;
    }

    private Long parseDateToLong(JsonNode node) {
        Long publishedDate = null;
        String publishedDateString = node.get("publishedDate").asText();
        SimpleDateFormat sdf = publishedDateString.length() == 4 ? new SimpleDateFormat("yyyy"): new SimpleDateFormat("yyyy-MM-dd");
        try {
            publishedDate = sdf.parse(publishedDateString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return publishedDate;
    }


}
