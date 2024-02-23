import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Objects;


public class Game {
    private static ArrayList<Integer> number;
    public Game() {
        number = new ArrayList<Integer>();
        Random random = new Random();

        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 0; i < 10;++i) {
            digits.add(i);
        }
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(9);
            Collections.swap(digits, x, i);
        }
        for (int i = 0; i < 4; ++i) {
            number.add(digits.get(i));
        }
    }



    public Game(String userString) {
        Validator validator = new Validator();
        Validator.InputAnalysisResult inputCheck = validator.checkInput(userString);
        if (! Objects.equals(inputCheck, Validator.InputAnalysisResult.ok)) {
            throw new IllegalArgumentException("Number must contain 4 unrepeated digits");
        }
        number = new ArrayList<Integer>();
        for (char x : userString.toCharArray()) {
            number.add(Character.getNumericValue(x));
        }
    }

    public void StartGame() {
        ArrayList<Integer> suggested;
        int buls = 0;
        int cows = 0;
        Scanner in = new Scanner(System.in);
        InputService reader = new InputService(in);

        while (buls != 4) {
            suggested = reader.GetNumber();
            cows = cowCounter(suggested);
            buls = bulCounter(suggested);
            if (buls == 4) {
                System.out.println("Good work! You won!");
                break;
            } else {
                System.out.println(buls + " buls " + cows + " cows");
            }
            System.out.println("Try again: ");
        }

        in.close();
    }

    public int bulCounter(ArrayList<Integer> userNumber) {
        int buls = 0;
        for (int i = 0; i < 4; ++i) {
            int userDigit = userNumber.get(i);
            if (number.indexOf(userDigit) == i) {
                buls++;
            }
        }
        return buls;
    }

    public int cowCounter(ArrayList<Integer> userNumber) {
        int cows = 0;
        for (int i = 0; i < 4; ++i) {
            int userDigit = userNumber.get(i);
            if (number.contains(userDigit) && number.indexOf(userDigit) != i ) {
                cows++;
            }
        }
        return cows;
    }
}
