package co.uk.mytutor.code.challenge.codechallenge.exception;

/**
 * @author Samuel Catalano
 * @since Feb 28, 2020
 */
public class BookOutOfStockException extends Exception {

    public BookOutOfStockException() {
        super();
    }

    public BookOutOfStockException(String message) {
        super(message);
    }

    public BookOutOfStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookOutOfStockException(Throwable cause) {
        super(cause);
    }

    protected BookOutOfStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}