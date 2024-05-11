import commands.AddCommand;
import commandfactory.CommandFactory;
import commands.*;
import commands.PopCommand;
import commands.PrintCommand;
import exceptions.ArgumentsException;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandFactoryTest {
    private static final Logger logger = LoggerFactory.getLogger(CommandFactory.class);
    @Test
    public void commandFactoryKeysTest(){
        CommandFactory factory = new CommandFactory();
        try {
            Assert.assertEquals(factory.createCommand("+").getClass(), AddCommand.class);
            Assert.assertEquals(factory.createCommand("-").getClass(), SubtractCommand.class);
            Assert.assertEquals(factory.createCommand("/").getClass(), DivideCommand.class);
            Assert.assertEquals(factory.createCommand("*").getClass(), MultiplyCommand.class);
            Assert.assertEquals(factory.createCommand("PUSH").getClass(), PushCommand.class);
            Assert.assertEquals(factory.createCommand("POP").getClass(), PopCommand.class);
            Assert.assertEquals(factory.createCommand("DEFINE").getClass(), DefineCommand.class);
            Assert.assertEquals(factory.createCommand("PRINT").getClass(), PrintCommand.class);
            Assert.assertEquals(factory.createCommand("SQRT").getClass(), SqrtCommand.class);
        } catch (ArgumentsException e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    @Test
    public void commandFactoryCommandsTest(){
        CommandFactory factory = new CommandFactory();
        try {
            Assert.assertTrue(factory.createCommand("POP") instanceof PopCommand);
            Assert.assertTrue(factory.createCommand("PUSH") instanceof PushCommand);
            Assert.assertTrue(factory.createCommand("PRINT") instanceof PrintCommand);
            Assert.assertTrue(factory.createCommand("DEFINE") instanceof DefineCommand);
            Assert.assertTrue(factory.createCommand("+") instanceof AddCommand);
            Assert.assertTrue(factory.createCommand("-") instanceof SubtractCommand);
            Assert.assertTrue(factory.createCommand("/") instanceof DivideCommand);
            Assert.assertTrue(factory.createCommand("*") instanceof MultiplyCommand);
            Assert.assertTrue(factory.createCommand("SQRT") instanceof SqrtCommand);

        } catch (ArgumentsException e) {
            throw new RuntimeException(e);
        }
    }
}
