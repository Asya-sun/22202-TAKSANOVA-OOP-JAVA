import org.junit.Assert;
import org.junit.Test;
public class ValidatorTest {
    @Test
    public void testValidateOk() {
        Assert.assertEquals(Validator.InputAnalysisResult.ok, Validator.checkInput("1234"));
    }

    @Test
    public void testValidateNotFourDigits() {
        Assert.assertEquals(Validator.InputAnalysisResult.notFourDigits, Validator.checkInput("123"));
    }


    @Test
    public void testValidateRepeatingDigits() {
        Assert.assertEquals(Validator.InputAnalysisResult.repeatingDigits, Validator.checkInput("1223"));
        Assert.assertEquals(Validator.InputAnalysisResult.repeatingDigits, Validator.checkInput("2222"));
        Assert.assertEquals(Validator.InputAnalysisResult.repeatingDigits, Validator.checkInput("0989"));
    }

    @Test
    public void testValidateNotNumber() {
        Assert.assertEquals(Validator.InputAnalysisResult.notNumber, Validator.checkInput("aaa"));
    }
}
