package by.tyzcorporation.library.model.entity;

import java.io.Serializable;

public class ConcreteMagazine extends Magazine implements Serializable {
    public ConcreteMagazine(String title, int pageCount, String category, String genre, boolean borrow, int countBorrowPublication) {
        super(title, pageCount, category, genre, borrow,countBorrowPublication);
    }

}
