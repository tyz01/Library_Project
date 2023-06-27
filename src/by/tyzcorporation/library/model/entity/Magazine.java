package by.tyzcorporation.library.model.entity;

import java.util.Objects;

public abstract class Magazine extends Publication {
    private String category;
    private String genre;

    public Magazine(String title, int pageCount, String category, String genre) {
        super(title, pageCount);
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
        sb.append("Magazine{")
                .append("category='").append(category).append('\'')
                .append(", genre='").append(genre).append('\'')
                .append('}');
        return sb.toString();
    }
}