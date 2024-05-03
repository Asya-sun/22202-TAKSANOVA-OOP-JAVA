package commands;

import calculatorcontext.CalculatorContext;
import commands.Command;
import exceptions.ArgumentsException;
import exceptions.StackUnderflowException;

public class SqrtCommand extends Command {
    @Override
    public void execute(CalculatorContext context, Object... arg) throws StackUnderflowException, ArgumentsException {
        if (context.getStack().empty()) {
            throw new StackUnderflowException("can't execute command with empty stack");
        }
        double a = context.getStack().pop();
        if (a < 0) {
            throw new ArgumentsException("can't sqrt command negative number");
        }
        context.getStack().push(Math.sqrt(a));
    }
}
