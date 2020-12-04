package pl.krzysztof.piasecki.homework.serializator;

import com.fasterxml.jackson.core.JsonParser;
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
    private static final String IMAGELINKS = "imageLinks";
    private static final String IDENTIFIER = "identifier";
    private static final String VOLUMEINFO = "volumeInfo";
    private static final String INDUSTRYIDENTIFIERS = "industryIdentifiers";
    private static final String PUBLISHEDDATE = "publishedDate";
    private static final String THUMBNAIL = "thumbnail";
    private static final String TYPE = "type";
    private static final String ISBNTYPENUMBER = "13";
    private static final String ID = "id";


    public BookDeserializer() {
        super(Book.class);
    }

    @Override
    public Book deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode treeNode = jsonParser.readValueAsTree();
        JsonNode volumeInfoNode = treeNode.get(VOLUMEINFO);
        Book book = new ObjectMapper().readValue(volumeInfoNode.toString(), Book.class);
        if (volumeInfoNode.hasNonNull(INDUSTRYIDENTIFIERS))
            book.setIsbn(parseIsbn(treeNode));
        if (volumeInfoNode.hasNonNull(PUBLISHEDDATE))
            book.setPublishedDate(parseDateToLong(volumeInfoNode));
        if (volumeInfoNode.hasNonNull(IMAGELINKS) && volumeInfoNode.get(IMAGELINKS).hasNonNull(THUMBNAIL)) {
            book.setThumbnailUrl(volumeInfoNode.get(IMAGELINKS).get(THUMBNAIL).asText());
        }
        return book;
    }

    private String parseIsbn(JsonNode node) {
        String identifier = node.get(ID).asText();
        List<JsonNode> list = node.findParents(IDENTIFIER);
        Optional<JsonNode> isbn13 = list.stream().filter(e -> e.get(TYPE).asText().endsWith(ISBNTYPENUMBER)).findFirst();
        if (isbn13.isPresent() && isbn13.get().has(IDENTIFIER)) {
            identifier = isbn13.get().get(IDENTIFIER).asText();
        }
        return identifier;
    }

    private Long parseDateToLong(JsonNode node) {
        Long publishedDate = null;
        String publishedDateString = node.get(PUBLISHEDDATE).asText();
        SimpleDateFormat sdf;
        if (publishedDateString.length() == 4) {
            sdf = new SimpleDateFormat("yyyy");
        } else if (publishedDateString.length() == 7) {
            sdf = new SimpleDateFormat("yyyy-MM");
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        try {
            publishedDate = sdf.parse(publishedDateString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return publishedDate;
    }


}
