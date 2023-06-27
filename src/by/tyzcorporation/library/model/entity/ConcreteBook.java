package by.tyzcorporation.library.model.entity;

public class ConcreteBook extends Book {
    public ConcreteBook(Book other) {
        super(other);
    }

    public ConcreteBook(String title, int pageCount, String author, String genre) {
        super(title, pageCount, author, genre);
    }
}
