import java.util.ArrayList;
import java.util.Scanner;
import java.util.Objects;
public class InputService {
    public static ArrayList<Integer> GetNumber(Scanner in) {
        System.out.println("enter a number : ");
        String entered = in.nextLine();
        Validator.InputAnalysisResult chechRes = Validator.checkInput(entered);
        while (!Objects.equals(chechRes, Validator.InputAnalysisResult.ok)) {
            switch (chechRes) {
                case notNumber:
                    System.out.println("Your answer must be a 4-digit number\nTry again:");
                    break;
                case notFourDigits:
                    System.out.println("Your answer must contain 4 digits\nTry again:");
                    break;
                case repeatingDigits:
                    System.out.println("Digits mustn't repeat\nTry again:");
                    break;
            }
            entered = in.nextLine();
            chechRes = Validator.checkInput(entered);
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (char x : entered.toCharArray()) {
            res.add(Character.getNumericValue(x));
        }
        return res;
    }
}