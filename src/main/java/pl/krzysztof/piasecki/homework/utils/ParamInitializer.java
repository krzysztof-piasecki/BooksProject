package pl.krzysztof.piasecki.homework.utils;

import java.util.HashMap;
import java.util.Map;

public class ParamInitializer {

    private static ParamInitializer paramInitializer = null;
    private Map<String, String> params = new HashMap<>();

    public static ParamInitializer getInstance(){
        if (paramInitializer == null){
            paramInitializer = new ParamInitializer();
        }

        return paramInitializer;
    }

    public void putParams(String [] args){
        for (String param:args){
            params.put(parseKeyFromArg(param),parseValueFromArg(param));
        }
    }

    private String parseKeyFromArg(String arg){
        return arg.split("=")[0].replace("-D","");
    }
    private String parseValueFromArg(String arg){
        StringBuilder value = new StringBuilder();
        String[] brokenValues = arg.split("=");
        for(int i=1; i<brokenValues.length; i++) {
            value.append(brokenValues[i]);
        }
        return value.toString();
    }

    public String getParam(String key) {
        return params.get(key);
    }
}
