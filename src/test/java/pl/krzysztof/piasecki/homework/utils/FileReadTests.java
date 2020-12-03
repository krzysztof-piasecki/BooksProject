package pl.krzysztof.piasecki.homework.utils;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.krzysztof.piasecki.homework.BaseTests;

import java.io.File;

@SpringBootTest
public class FileReadTests extends BaseTests {
    @Test
    public void readFileFromProperty() {
        File file = readBookFile();
        Assert.assertTrue("File path is:" + file.getAbsolutePath(), file.exists());
    }
}
