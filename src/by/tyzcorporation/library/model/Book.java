package by.tyzcorporation.library.model;

public class Book extends Publication implements Cloneable {
    private String author;
    private String genre;

    public Book(Book other) {
        this.author = other.author;
        this.genre = other.genre;
    }
    public Book(String title, int pageCount, String author, String genre) {
        super(title, pageCount);
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
        } else {
            throw new IllegalArgumentException("Author cannot be null or empty.");
        }
    }

    public void setGenre(String genre) {
        if (genre != null && !genre.isEmpty()) {
            this.genre = genre;
        } else {
            throw new IllegalArgumentException("Author cannot be null or empty.");
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    @Override
    public Book clone() {
        try {
            return (Book) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
