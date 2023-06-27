package by.tyzcorporation.library.service.utility.filter;

import by.tyzcorporation.library.model.entity.Book;
import by.tyzcorporation.library.model.entity.Publication;

public class BookGenreFilter implements GenreFilter {
    private final String genre;

    public BookGenreFilter(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean filter(Publication publication) {
        return publication instanceof Book && ((Book) publication).getGenre().equals(genre);
    }
}
