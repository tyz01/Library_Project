package by.tyzcorporation.library.model.exception.logical;

public class InvalidPageCountException extends RuntimeException  {
    public InvalidPageCountException(String description) {
        super(description);
    }
}
