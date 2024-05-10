package commands;

import calculatorcontext.CalculatorContext;
import commands.Command;
import exceptions.DivideByZeroException;
import exceptions.StackUnderflowException;

public class DivideCommand extends Command {
    @Override
    public void execute(CalculatorContext context, Object... arg) throws StackUnderflowException, DivideByZeroException {
        if (context.getStack().size() < 2) {
            throw new StackUnderflowException("not enough arguments");
        }
        Double first = context.getStack().pop();
        Double second = context.getStack().pop();
        if (second == 0) {
            context.getStack().push(second);
            context.getStack().push(first);
            throw new DivideByZeroException("can't divide on zero!!!");
        }
        context.getStack().push(first / second);
    }
}
