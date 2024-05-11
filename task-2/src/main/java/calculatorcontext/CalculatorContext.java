package calculatorcontext;

import java.util.HashMap;
import java.util.Stack;

public class CalculatorContext {
    private final Stack<Double> stack;
    private HashMap<String, Double> defNamedParameters;

    public CalculatorContext() {
        stack = new Stack<>();
        defNamedParameters = new HashMap<>();

    }


    public HashMap<String, Double> getDefNamedParameters() {
        return defNamedParameters;
    }

    public Stack<Double> getStack(){
        return stack;
    }
}
