package by.tyzcorporation.library.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Magazine extends Publication implements Serializable {
    @Serial
    private static final long serialVersionUID = 1234572L;
    private String category;
    private String genre;

    public Magazine(){}
    public Magazine(Integer idMagazine, String title, int pageCount, String category, String genre, boolean borrow, int countBorrowPublication) {
        super(idMagazine, title, pageCount, borrow,countBorrowPublication);
        this.category = category;
        this.genre = genre;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category != null && !category.isEmpty()) {
            this.category = category;
        }
            throw new IllegalArgumentException("category cannot be null or empty.");
    }

    public void setGenre(String genre) {
        if (genre != null && !genre.isEmpty()) {
            this.genre = genre;
        }
        throw new IllegalArgumentException("category cannot be null or empty.");
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }
        Magazine magazine = (Magazine) o;
        return Objects.equals(category, magazine.category) && Objects.equals(genre, magazine.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), category, genre);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Magazine: ")
                .append("title = ").append(getTitle())
                .append(", category = ").append(category)
                .append(", genre = ").append(genre)
                .append(", borrow = ").append(isBorrow())
                .append(", count borrow publication = ").append(getCountBorrowPublication());
        return sb.toString();
    }
}