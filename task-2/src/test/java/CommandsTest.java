import calculatorcontext.CalculatorContext;
import commands.AddCommand;
import commands.*;
import commands.PopCommand;
import exceptions.ArgumentsException;
import exceptions.DivideByZeroException;
import exceptions.StackUnderflowException;
import org.junit.Assert;
import org.junit.Test;

public class CommandsTest {
    private final CalculatorContext context = new CalculatorContext();

    @Test
    public void addTest(){
        AddCommand command = new AddCommand();
        context.getStack().push(5.0);
        context.getStack().push(3.0);
        Object a = new Object();
        try {
            command.execute(context, a);
        } catch (StackUnderflowException e) {
            System.out.println(e.getLocalizedMessage());
        }
        double b = context.getStack().pop();
        Assert.assertEquals(8.0, b, 0.0);
    }

    @Test
    public void subtractTest(){
        SubtractCommand command = new SubtractCommand();
        context.getStack().push(5.0);
        context.getStack().push(3.0);
        Object a = new Object();
        try {
            command.execute(context, a);
        } catch (StackUnderflowException e) {
            System.out.println(e.getLocalizedMessage());
        }
        double b = context.getStack().pop();
        Assert.assertEquals(-2.0, b, 0.0);
    }

    @Test
    public void multiplyTest(){
        MultiplyCommand command = new MultiplyCommand();
        context.getStack().push(5.0);
        Object a = new Object();
        context.getStack().push(3.0);
        try {
            command.execute(context, a);
        } catch (StackUnderflowException e) {
            System.out.println(e.getLocalizedMessage());
        }
        double b = context.getStack().pop();
        Assert.assertEquals(15.0, b, 0.0);
    }

    @Test
    public void divideTest(){
        DivideCommand command = new DivideCommand();
        context.getStack().push(5.0);
        Object a = new Object();
        context.getStack().push(10.0);
        try {
            command.execute(context, a);
        } catch (StackUnderflowException | DivideByZeroException e) {
            System.out.println(e.getLocalizedMessage());
        }
        double b = context.getStack().pop();
        Assert.assertEquals(2.0, b, 0.0);
    }

    /**
     * StackUnderflowException is never thrown
     * because stack is filled with 2 elements right before executing command
     * @throws DivideByZeroException
     * @throws StackUnderflowException
     */
    @Test(expected = DivideByZeroException.class)
    public void divideByZeroExceptionTest() throws DivideByZeroException, StackUnderflowException {
        DivideCommand command = new DivideCommand();
        context.getStack().push(0.0);
        Object a = new Object();
        context.getStack().push(10.0);
        command.execute(context, a);

    }

    @Test
    public void sqrtTest(){
        SqrtCommand command = new SqrtCommand();
        context.getStack().push(9.0);
        Object a = new Object();
        try {
            command.execute(context, a);
        } catch (StackUnderflowException | ArgumentsException e) {
            System.out.println(e.getLocalizedMessage());
        }
        double b = context.getStack().pop();
        Assert.assertEquals(3.0, b, 0.0000001);
    }

    @Test
    public void pushTest(){
        PushCommand command = new PushCommand();
        double a = 5.0;
        try {
            command.execute(context, a);
        } catch (ArgumentsException e) {
            System.out.println(e.getLocalizedMessage());
        }
        Assert.assertEquals(context.getStack().peek(), a, 0.0);
    }

    @Test
    public void popTest(){
        PopCommand command = new PopCommand();
        double a = 5.0;
        context.getStack().push(a);
        try {
            command.execute(context, a);
        } catch (StackUnderflowException e) {
            System.out.println(e.getLocalizedMessage());
        }
        Assert.assertTrue(context.getStack().empty());
    }

    @Test(expected = StackUnderflowException.class)
    public void stackUnderflowExceptionTest() throws StackUnderflowException{
        PopCommand command = new PopCommand();
        Object a = new Object();
        command.execute(context, a);
    }

    @Test
    public void defineTest(){
        DefineCommand command = new DefineCommand();
        String a = "var";
        double b = 5;
        try {
            command.execute(context, new Object[] {a, b});
        } catch (ArgumentsException e) {
            System.out.println(e.getLocalizedMessage());
        }
        Assert.assertTrue(context.getDefNamedParameters().containsKey(a));
        Assert.assertEquals(context.getDefNamedParameters().get(a), b, 0.0);
    }

    @Test(expected = ArgumentsException.class)
    public void argumentExceptionPushTest() throws ArgumentsException{
        PushCommand command = new PushCommand();
        String a = "someString";
        command.execute(context, a);
    }
}
