package Exception;

public class InsufficientStockException extends Exception {
    public InsufficientStockException() {
        super("Insufficient stock for this product!");
    }
}
