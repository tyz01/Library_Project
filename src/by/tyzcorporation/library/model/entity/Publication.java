package by.tyzcorporation.library.model.entity;

import by.tyzcorporation.library.model.exception.logical.InvalidPageCountException;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class  Publication implements Comparable<Publication>, Serializable {
    @Serial
    private static final long serialVersionUID = 1234573L;
    private Integer idPublication;
    private String title;
    private int pageCount;
    private boolean borrow;
    private int countBorrowPublication;

    public Publication(Integer idPublication, String title) {
        this.idPublication = idPublication;
        this.title = title;
    }

    public Publication(Integer idPublication, String title, int pageCount) {
        this.idPublication = idPublication;
        this.title = title;
        this.pageCount = pageCount;
    }

    public Publication(Integer idPublication, String title, boolean borrow, int countBorrowPublication) {
        this.idPublication = idPublication;
        this.title = title;
        this.borrow = borrow;
        this.countBorrowPublication = countBorrowPublication;
    }


    public Publication() {
        this.idPublication = null;
        this.title = "Untitled";
        this.pageCount = 0;
        this.borrow = false;
        this.countBorrowPublication = 0;
    }

    public Publication(Integer idPublication, String title, int pageCount, boolean borrow, int countBorrowPublication) {
        this.idPublication = idPublication;
        this.title = title;
        this.pageCount = pageCount;
        this.borrow = borrow;
        this.countBorrowPublication = countBorrowPublication;
    }

    public Publication(Integer idPublication) {
        this.idPublication = idPublication;
    }

    public int getCountBorrowPublication() {
        return countBorrowPublication;
    }

    public void setCountBorrowPublication(int countBorrowPublication) {
        this.countBorrowPublication = countBorrowPublication;
    }

    public void setPageCount(int pageCount) throws InvalidPageCountException {
        if (pageCount > 0) {
            this.pageCount = pageCount;
        }
        throw new InvalidPageCountException("Page count must be greater than 0.");
    }

    public Integer getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(Integer idPublication) {
        this.idPublication = idPublication;
    }

    public boolean isBorrow() {
        return borrow;
    }

    public void setBorrow(boolean borrow) {
        this.borrow = borrow;
    }

    public void setTitle(String title) {
//        if (title != null && !title.isEmpty()) {
            this.title = title;
//        }
//        throw new IllegalArgumentException("Title cannot be null or empty.");
    }

    public String getTitle() {
        return title;
    }

    public int getPageCount() {
        return pageCount;
    }

    @Override
    public int compareTo(Publication other) {
        return Integer.compare(pageCount, other.pageCount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Publication other = (Publication) obj;
        return pageCount == other.pageCount && Objects.equals(title, other.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, pageCount);
    }

    @Override
    public String toString() {
        return "Publication{" +
                "idPublication=" + idPublication +
                ", title='" + title + '\'' +
                ", pageCount=" + pageCount +
                ", borrow=" + borrow +
                ", countBorrowPublication=" + countBorrowPublication +
                '}';
    }
}

