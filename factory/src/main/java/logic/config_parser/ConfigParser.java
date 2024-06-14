package logic.config_parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class ConfigParser {
    private final String configFile = "/config.txt";
    HashMap<String, Integer> parametersHashmap;


    public ConfigParser() {
        InputStream inputStream = ConfigParser.class.getResourceAsStream(configFile);
        if (inputStream == null) {
            throw new RuntimeException();
        }
        Properties properties = new Properties();
        parametersHashmap =  new HashMap<>();
        try {
            properties.load(inputStream);
//            System.out.println("properties was loaded");
//            for (String parameter : properties.stringPropertyNames())
            for (String parameter : properties.stringPropertyNames()) {
                try {
                    parametersHashmap.put(parameter, Integer.parseInt(properties.get(parameter).toString()));
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(e.getMessage());
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (ConfigParameters parameter : ConfigParameters.values()) {
            if (parametersHashmap == null || !parametersHashmap.containsKey(parameter.toString())) {
                parametersHashmap.put(parameter.toString(), parameter.getDefaultValue(parameter));
            }
        }


    }


    public int getValue(ConfigParameters name) {
        return parametersHashmap.get(name.toString());
    }
}
