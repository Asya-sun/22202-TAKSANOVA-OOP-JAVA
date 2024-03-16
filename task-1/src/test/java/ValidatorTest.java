import org.junit.Assert;
import org.junit.Test;
public class ValidatorTest {
    Validator validator = new Validator();
    @Test
    public void testValidateOk() {
        Assert.assertEquals(Validator.InputAnalysisResult.ok, validator.checkInput("1234"));
    }

    @Test
    public void testValidateNotFourDigits() {
        Assert.assertEquals(Validator.InputAnalysisResult.notFourDigits, validator.checkInput("123"));
    }


    @Test
    public void testValidateRepeatingDigits() {
        Assert.assertEquals(Validator.InputAnalysisResult.repeatingDigits, validator.checkInput("1223"));
        Assert.assertEquals(Validator.InputAnalysisResult.repeatingDigits, validator.checkInput("2222"));
        Assert.assertEquals(Validator.InputAnalysisResult.repeatingDigits, validator.checkInput("0989"));
    }

    @Test
    public void testValidateNotNumber() {
        Assert.assertEquals(Validator.InputAnalysisResult.notNumber, validator.checkInput("aaa"));
    }
}
