package by.tyzcorporation.library.model;

public class Magazine extends Publication {
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

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "category='" + category + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}