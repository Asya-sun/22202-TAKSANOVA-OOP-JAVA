import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class GameTest {
    @Test
    public void cowsTest() {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);

        ArrayList<Integer> number = new ArrayList<>();
        number.add(4);
        number.add(1);
        number.add(2);
        number.add(3);

        Assert.assertEquals(Game.cowCounter(test, number), 4);
        number.set(0,0);
        Assert.assertEquals(Game.cowCounter(test, number), 3);
        number.set(3,9);
        Assert.assertEquals(Game.cowCounter(test, number), 2);
        number.set(2,8);
        Assert.assertEquals(Game.cowCounter(test, number), 1);
        number.set(1,7);
        Assert.assertEquals(Game.cowCounter(test, number), 0);
    }

    @Test
    public void bulsTest() {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);

        ArrayList<Integer> number = new ArrayList<>();
        number.add(1);
        number.add(2);
        number.add(3);
        number.add(4);

        Assert.assertEquals(Game.bulCounter(test, number), 4);
        number.set(0,0);
        Assert.assertEquals(Game.bulCounter(test, number), 3);
        number.set(3,9);
        Assert.assertEquals(Game.bulCounter(test, number), 2);
        number.set(2,8);
        Assert.assertEquals(Game.bulCounter(test, number), 1);
        number.set(1,7);
        Assert.assertEquals(Game.bulCounter(test, number), 0);
    }


}
