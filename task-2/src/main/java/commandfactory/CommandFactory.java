package commandfactory;

import commands.*;
import exceptions.ArgumentsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class CommandFactory {
    private static final Logger logger = LoggerFactory.getLogger(CommandFactory.class);

    //if file is in commandfactory dir, path must be this
    //"src/main/java/commandfactory/config.properties.txt";
    private final String configFile = "/config.properties.txt";
    public HashMap<String, Command> commandsList = new HashMap<>();

    public CommandFactory() {
        InputStream inputStream = CommandFactory.class.getResourceAsStream(configFile);
        if (inputStream == null) {
            logger.error("input stream is empty, no resource with this name is found");
            return;
        }

        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            for (String name : properties.stringPropertyNames()) {
                commandsList.put(name, (Command) Class.forName(properties.get(name).toString()).getDeclaredConstructor().newInstance());
            }
        } catch (IOException e) {
            logger.error("impossible to load config file");
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

     public Command createCommand(String commandName) throws ArgumentsException {
        if (commandsList.containsKey(commandName)) {
            return  commandsList.get((commandName));
        } else {
            throw new ArgumentsException("No such command");
        }
     }


}
