public class Validator {
    String verifiable;
    public enum InputAnalysisResult{
        notFourDigits,
        repeatingDigits,
        notNumber,
        ok

    }

    /*public Validator(String str) {
        verifiable = new String(str);
    }*/
    public Validator() {}




    public InputAnalysisResult checkInput(String entered) {
        String working = entered.trim(); // to delete starting and ending spaces
        //to check length
        for (char x : working.toCharArray()) {
            if (!Character.isDigit(x)) {
                return InputAnalysisResult.notNumber;
            }
        }

        if (working.length() != 4) {
            return InputAnalysisResult.notFourDigits;
        }
        //to check if it contains only digits

        //to check repeating symbols
        for (char x : working.toCharArray()) {
            if (working.indexOf(x) != working.lastIndexOf(x)) {
                return InputAnalysisResult.repeatingDigits;
            }
        }

        entered = working;
        return InputAnalysisResult.ok;
    }
}
