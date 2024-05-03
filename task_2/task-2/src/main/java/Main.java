import commandfactory.*;
import commands.Command;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        /*CommandFactory cf = new CommandFactory();
        cf.printCommands();*/
        Scanner in;
        if (args.length == 0) {
            in = new Scanner(System.in);
        } else {
            try {
                File file = new File(args[0]);
                in = new Scanner(file);
            } catch (FileNotFoundException e) {
                logger.error(e.getLocalizedMessage());
                return;
            }
        }
        StackCalculator calculator = new StackCalculator(in);
        calculator.execute();
        in.close();
    }
}
