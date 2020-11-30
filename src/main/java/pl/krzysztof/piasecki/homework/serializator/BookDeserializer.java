package pl.krzysztof.piasecki.homework.serializator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pl.krzysztof.piasecki.homework.model.Book;
import pl.krzysztof.piasecki.homework.model.BookModel;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class BookDeserializer extends StdDeserializer<BookModel> {

    private static final long serialVersionUID = 7923585097068641765L;


    public BookDeserializer()
    {
        super(BookModel.class);
    }
    @Override
    public BookModel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode treeNode = jsonParser.readValueAsTree();
        JsonNode volumeInfoNode = treeNode.get("volumeInfo");
        BookModel bookModel = new ObjectMapper().readValue(volumeInfoNode.toString(), BookModel.class);
        if(volumeInfoNode.hasNonNull("industryIdentifiers"))
            bookModel.setIsbn(parseIsbn(treeNode));
        if(volumeInfoNode.hasNonNull("publishedDate"))
            bookModel.setPublishedDate(parseDateToLong(volumeInfoNode));
        return bookModel;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            publishedDate = sdf.parse(node.get("publishedDate").asText()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return publishedDate;
    }


}
