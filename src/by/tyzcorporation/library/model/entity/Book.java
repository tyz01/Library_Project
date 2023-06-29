package by.tyzcorporation.library.model.entity;

import java.io.Serializable;

public abstract class Book extends Publication implements Cloneable, Serializable {
    private String author;
    private String genre;

    public Book(Book other) {
        this.author = other.author;
        this.genre = other.genre;
    }
    public Book(String title, int pageCount, String author, String genre, boolean borrow, int countBorrowPublication) {
        super(title, pageCount, borrow, countBorrowPublication);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public void setAuthor(String author) {
        if (author != null && !author.isEmpty()) {
            this.author = author;
        }
        throw new IllegalArgumentException("Author cannot be null or empty.");
    }

    public void setGenre(String genre) {
        if (genre != null && !genre.isEmpty()) {
            this.genre = genre;
        }
            throw new IllegalArgumentException("Genre cannot be null or empty.");
    }
    @Override
    public Book clone() {
        try {
            return (Book) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book: ")
                .append("title = ").append(getTitle()).append(", ")
                .append("author = ").append(author).append(", ")
                .append("genre = ").append(genre).append(", ")
                .append("borrow = ").append(isBorrow()).append(", ")
                .append("count get publication = ").append(getCountBorrowPublication());
        return sb.toString();
    }

}
