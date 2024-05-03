package commands;

import calculatorcontext.CalculatorContext;
import exceptions.StackUnderflowException;

public class PopCommand extends Command{
    @Override
    public void execute(CalculatorContext context, Object... args) throws StackUnderflowException {
        if (context.getStack().isEmpty()) {
            throw new StackUnderflowException("stack is empty");
        }
        context.getStack().pop();

    }
}
