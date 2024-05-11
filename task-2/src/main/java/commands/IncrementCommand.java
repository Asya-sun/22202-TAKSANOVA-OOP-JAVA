package commands;

import calculatorcontext.CalculatorContext;
import exceptions.ArgumentsException;
import exceptions.DivideByZeroException;
import exceptions.StackUnderflowException;

public class IncrementCommand extends Command{
    @Override
    public void execute(CalculatorContext context, Object... arg) throws StackUnderflowException, DivideByZeroException, ArgumentsException {
        if (context.getStack().empty()) {
            throw new StackUnderflowException("not enough arguments");
        }
        Double number = context.getStack().pop();
        number++;
        context.getStack().push(number);
    }
}
