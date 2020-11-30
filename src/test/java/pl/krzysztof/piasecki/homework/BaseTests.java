package pl.krzysztof.piasecki.homework;

import org.junit.Assert;
import org.junit.Before;
import pl.krzysztof.piasecki.homework.utils.ParamInitializer;

import java.io.File;

public class BaseTests {
    protected String[] args;
    @Before
    public void initializeTestData() {
        args = new String []{"-Ddatasource=misc/singleBook.json"};
    }

    public File readBookFile() {
        ParamInitializer.getInstance().putParams(args);

        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(ParamInitializer.getInstance().getParam("datasource")).getFile());
    }
}
