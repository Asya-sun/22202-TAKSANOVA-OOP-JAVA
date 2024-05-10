package commands;

import calculatorcontext.CalculatorContext;
import exceptions.ArgumentsException;

import javax.naming.Context;

public class PushCommand extends Command{
    @Override
    public void execute(CalculatorContext context, Object... args) throws ArgumentsException {
        if (args.length == 0) {
            throw new ArgumentsException("there are nothing to push");
        }
        if (isNumeric(args[0])) {
            context.getStack().push(Double.parseDouble(args[0].toString()));
        } else if (context.getDefNamedParameters().containsKey(args[0].toString())) {
            context.getStack().push(context.getDefNamedParameters().get(args[0].toString()));
        } else {
            throw new ArgumentsException("can't push not a number or non-defined named parameter");
        }
    }
}
