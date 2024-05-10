package commands;

import calculatorcontext.CalculatorContext;
import exceptions.ArgumentsException;
import exceptions.StackUnderflowException;

import javax.naming.Context;

public class DefineCommand extends Command{
    @Override
    public void execute(CalculatorContext context, Object... args) throws ArgumentsException {
        if (args.length < 2) {
            throw new ArgumentsException("there is nothing to define");
        } else if (context.getDefNamedParameters().containsKey(args[0].toString())) {
            throw new ArgumentsException("this argument is already defined");
        }

        if (isNumeric(args[0])) {
            throw new ArgumentsException("you can't redefine number!!!");
        }
        String newName = args[0].toString();
        if(isNumeric(args[1])) {
            double newDef = Double.parseDouble(args[1].toString());
            context.getDefNamedParameters().put(newName, newDef);
        } else {
            throw new ArgumentsException("wrong second argument in Define command");
        }
    }
}
