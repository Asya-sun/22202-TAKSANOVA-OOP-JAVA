package commands;

import calculatorcontext.CalculatorContext;
import exceptions.ArgumentsException;
import exceptions.DivideByZeroException;
import exceptions.StackUnderflowException;

public abstract class Command {
    public abstract void execute(CalculatorContext context, Object... arg) throws StackUnderflowException, DivideByZeroException, ArgumentsException;
    public static boolean isNumeric(Object object) {
        if (object == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(object.toString());
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
