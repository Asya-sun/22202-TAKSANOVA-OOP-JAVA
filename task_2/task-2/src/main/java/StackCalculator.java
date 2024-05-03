import calculatorcontext.CalculatorContext;
import commandfactory.CommandFactory;
import commands.Command;
import exceptions.ArgumentsException;
import exceptions.DivideByZeroException;
import exceptions.StackUnderflowException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Scanner;

public class StackCalculator {
    private static final Logger logger = LoggerFactory.getLogger(StackCalculator.class);
    private final CalculatorContext context = new CalculatorContext();
    private final CommandFactory commands = new CommandFactory();
    private final Scanner input;

    public StackCalculator(Scanner in) {
        input = in;
    }

    public void execute() {
        while (input.hasNext()) {
            String line = input.nextLine();
            String[] parsed = line.split(" ");
            String[] argumentsToCommand = Arrays.copyOfRange(parsed, 1, parsed.length);
            if (!commands.getCommands().containsKey(parsed[0])) {
                logger.warn("no such command {}", parsed[0]);
                continue;
            }
            Command command = commands.getCommands().get(parsed[0]);
            try {
                command.execute(context, (Object[]) argumentsToCommand);
            } catch (ArgumentsException | DivideByZeroException | StackUnderflowException e) {
                logger.warn(e.getLocalizedMessage());
            }
        }

    }
}
