import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertThrows;

public class GameTest {
    @Test
    public void cowsTest() {
        Game a = new Game("1234");
        ArrayList<Integer> test = new ArrayList<>();
        test.add(4);
        test.add(1);
        test.add(2);
        test.add(3);


        Assert.assertEquals(a.cowCounter(test), 4);
        test.set(0,0);
        Assert.assertEquals(a.cowCounter(test), 3);
        test.set(1,7);
        Assert.assertEquals(a.cowCounter(test), 2);
    }

    @Test
    public void bulsTest() {
        Game a = new Game("1234");
        ArrayList<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);
        Assert.assertEquals(a.bulCounter(test), 4);
        test.set(0,0);
        Assert.assertEquals(a.bulCounter(test), 3);
        test.set(1,1);
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


