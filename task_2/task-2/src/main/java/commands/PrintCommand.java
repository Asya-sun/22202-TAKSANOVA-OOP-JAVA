package commands;

import calculatorcontext.CalculatorContext;
import exceptions.StackUnderflowException;


public class PrintCommand extends Command{
    @Override
    public void execute(CalculatorContext context, Object... args) throws StackUnderflowException {
        if (context.getStack().isEmpty()) {
            throw new StackUnderflowException("can't print empty stack");
        }
        double a = context.getStack().peek();
        System.out.println(a);
    }
}
