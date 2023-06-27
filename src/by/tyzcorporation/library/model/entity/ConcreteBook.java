package by.tyzcorporation.library.model.entity;

import java.io.Serializable;

public class ConcreteBook extends Book implements Serializable {
    public ConcreteBook(Book other) {
        super(other);
    }

    public ConcreteBook(String title, int pageCount, String author, String genre) {
        super(title, pageCount, author, genre);
    }

}
