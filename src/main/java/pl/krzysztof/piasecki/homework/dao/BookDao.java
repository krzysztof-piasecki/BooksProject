package pl.krzysztof.piasecki.homework.dao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import pl.krzysztof.piasecki.homework.utils.ParamInitializer;

import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Iterator;

public class BookDao<Book> {
    JSONParser parser = new JSONParser();

    public void getElements(){
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            Object obj = parser.parse(classLoader.getResource(ParamInitializer.getInstance().getParam("datasource")).getFile());

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray companyList = (JSONArray) jsonObject.get("Company List");

            // An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
            // Iterators differ from enumerations in two ways:
            // 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
            // 2. Method names have been improved.
            Iterator<JSONObject> iterator = companyList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
