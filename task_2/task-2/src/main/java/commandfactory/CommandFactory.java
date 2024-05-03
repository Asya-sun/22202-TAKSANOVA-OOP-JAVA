package commandfactory;

import commands.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Scanner;

public class CommandFactory {
    private static final Logger logger = LoggerFactory.getLogger(CommandFactory.class);

    //if file is in commandfactory dir, path must be this
    //"src/main/java/commandfactory/configCommands.txt";
    private final String configFile = "/configCommands.txt";
    public HashMap<String, Command> commandsList = new HashMap<>();

    String str;
    public CommandFactory() {
        InputStream inputStream = CommandFactory.class.getResourceAsStream(configFile);
        if (inputStream == null) {
            logger.error("input stream is empty, no resource with this name is found");
            return;
        }
        Scanner comScan = new Scanner(inputStream);
        while (comScan.hasNext()) {
            str = comScan.next();
            String[] parts = str.split("=");
            if (parts.length != 2) {
                logger.warn("{} in config  file, no 2 parts separated by '='", str);
                continue;
            }
            try {
                commandsList.put(parts[0], (Command) Class.forName(parts[1]).getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
            }

        }
    }

    public void printCommands() {
        for (String a : commandsList.keySet()) {
            System.out.println(a +" " + commandsList.get(a).getClass());
        }
    }

     public HashMap<String, Command> getCommands() {
        return commandsList;
     }


}
