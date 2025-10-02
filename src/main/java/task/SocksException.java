package task;

/**
 * Represents custom exceptions thrown by the Socks application.
 * Used to indicate errors in user input or command handling.
 */
public class SocksException extends Exception {

    /**
     * Constructs a SocksException with the specified error message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public SocksException(String message) {
        super(message); // call Exception class's constructor
    }
}
