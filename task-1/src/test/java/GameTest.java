import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertThrows;

public class GameTest {
    @Test
    public void cowsTest() {
        Game a = new Game("1234");
        String test = "4123";
        Assert.assertEquals(a.cowCounter(test), 4);
        test = "0123";
        Assert.assertEquals(a.cowCounter(test), 3);
        test = "0723";
        Assert.assertEquals(a.cowCounter(test), 2);
    }

    @Test
    public void bulsTest() {
        Game a = new Game("1234");
        String test = "1234";
        Assert.assertEquals(a.bulCounter(test), 4);
        test = "0234";
        Assert.assertEquals(a.bulCounter(test), 3);
        test = "0134";
        Assert.assertEquals(a.bulCounter(test), 2);
    }

    @Test
    public void gameConstructorException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Game("1233");
        });
        String expectedMessage = "Number must contain 4 unrepeated digits";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }



}


