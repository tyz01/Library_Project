package by.tyzcorporation.library.service.filter.impl;

import by.tyzcorporation.library.model.Book;
import by.tyzcorporation.library.model.Publication;
import by.tyzcorporation.library.service.filter.GenreFilter;

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
