package by.tyzcorporation.library.model.entity;

import java.io.Serializable;

public class ConcreteAlbum extends Publication implements Album, Serializable {

    public ConcreteAlbum(String title, int pageCount, boolean borrow, int countBorrowPublication) {
        super(title, pageCount, borrow, countBorrowPublication);
    }
    @Override
    public void draw() {
        System.out.println("draw");
    }
    @Override
    public void setPage(int page) {
        if (page <= 0) {
            throw new IllegalArgumentException("incorrect count page");
        }
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Album: ")
                .append("title = ").append(getTitle())
                .append(", page count = ").append(getPageCount())
                .append(", borrow = ").append(isBorrow())
                .append(", count borrow publication = ").append(getCountBorrowPublication());
        return sb.toString();

    }

}
