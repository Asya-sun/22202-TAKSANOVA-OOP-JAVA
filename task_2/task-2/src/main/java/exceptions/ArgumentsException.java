package exceptions;

/**
 * Thrown when there are not enough arguments, or they are of wrong type
 */
public class ArgumentsException extends Exception{
    public ArgumentsException(String message) {
        super(message);
    }
}
