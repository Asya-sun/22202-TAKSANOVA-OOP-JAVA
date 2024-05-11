package commands;

import calculatorcontext.CalculatorContext;
import commands.Command;
import exceptions.StackUnderflowException;

public class SubtractCommand extends Command {
    @Override
    public void execute(CalculatorContext context, Object... arg) throws StackUnderflowException {
        if (context.getStack().size() < 2) {
            throw new StackUnderflowException("not enough arguments");
        }
        Double first = context.getStack().pop();
        Double second = context.getStack().pop();
        context.getStack().push(first - second);
    }
}
