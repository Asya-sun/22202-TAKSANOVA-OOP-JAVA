import commandfactory.CommandFactory;
import commands.*;
import org.junit.Assert;
import org.junit.Test;

public class CommandFactoryTest {
    @Test
    public void commandFactoryKeysTest(){
        CommandFactory factory = new CommandFactory();
        Assert.assertTrue(factory.getCommands().containsKey("POP"));
        Assert.assertTrue(factory.getCommands().containsKey("PUSH"));
        Assert.assertTrue(factory.getCommands().containsKey("DEFINE"));
        Assert.assertTrue(factory.getCommands().containsKey("PRINT"));
        Assert.assertTrue(factory.getCommands().containsKey("+"));
        Assert.assertTrue(factory.getCommands().containsKey("-"));
        Assert.assertTrue(factory.getCommands().containsKey("*"));
        Assert.assertTrue(factory.getCommands().containsKey("/"));
        Assert.assertTrue(factory.getCommands().containsKey("SQRT"));
    }

    @Test
    public void commandFactoryCommandsTest(){
        CommandFactory factory = new CommandFactory();
        Assert.assertTrue(factory.getCommands().get("POP") instanceof PopCommand);
        Assert.assertTrue(factory.getCommands().get("PUSH") instanceof PushCommand);
        Assert.assertTrue(factory.getCommands().get("DEFINE") instanceof DefineCommand);
        Assert.assertTrue(factory.getCommands().get("PRINT") instanceof PrintCommand);
        Assert.assertTrue(factory.getCommands().get("+") instanceof AddCommand);
        Assert.assertTrue(factory.getCommands().get("-") instanceof SubtractCommand);
        Assert.assertTrue(factory.getCommands().get("/") instanceof DivideCommand);
        Assert.assertTrue(factory.getCommands().get("*") instanceof MultiplyCommand);
        Assert.assertTrue(factory.getCommands().get("SQRT") instanceof SqrtCommand);
    }
}
