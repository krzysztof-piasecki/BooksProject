package pl.krzysztof.piasecki.homework;

import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.krzysztof.piasecki.homework.utils.ParamInitializer;

@SpringBootApplication
public class HomeworkApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(HomeworkApplication.class, args);
        ParamInitializer.getInstance().putParams(args);

    }

}
