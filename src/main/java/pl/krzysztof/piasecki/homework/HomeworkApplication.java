package pl.krzysztof.piasecki.homework;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import pl.krzysztof.piasecki.homework.cache.BookCacheImpl;
import pl.krzysztof.piasecki.homework.reader.JsonReader;
import pl.krzysztof.piasecki.homework.reader.impl.APIJsonReader;
import pl.krzysztof.piasecki.homework.reader.impl.JsonFileReader;
import pl.krzysztof.piasecki.homework.utils.ParamInitializer;

import java.util.Arrays;

@SpringBootApplication
public class HomeworkApplication {
    @Autowired
    Environment env;
    @Value("${default.json.path}")
    private String defaultPath;
    public static final String DATASOURCE = "datasource";
    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
        ParamInitializer.getInstance().putParams(args);
        BookCacheImpl.getInstance();

    }
    @Bean
    public JsonReader jsonReader() {

        String path = ParamInitializer.getInstance().getParam(DATASOURCE);
        if(path == null) {
            path = defaultPath;
        }
        if(UrlValidator.getInstance().isValid(path)) {
            return new APIJsonReader(path);
        }
        if(Arrays.stream(env.getActiveProfiles()).filter(e -> "test".equals(e)).findFirst().isPresent()) {
            return new JsonFileReader("misc/books.json");
        }
        return new JsonFileReader(path);
    }
}
