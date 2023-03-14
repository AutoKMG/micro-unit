package microunit;

public class InvalidTestClassException extends RuntimeException {
    public InvalidTestClassException(String message){
        super(message);
    }
    public InvalidTestClassException(Throwable cause){
        super(cause);
    }
    public InvalidTestClassException(String message, Throwable cause){
        super(message,cause);
    }
}
