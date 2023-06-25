package by.tyzcorporation.library.service.filter.impl;

import by.tyzcorporation.library.model.Magazine;
import by.tyzcorporation.library.model.Publication;
import by.tyzcorporation.library.service.filter.GenreFilter;

public class MagazineGenreFilter implements GenreFilter {
    private final String genre;

    public MagazineGenreFilter(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean filter(Publication publication) {
        return publication instanceof Magazine && ((Magazine) publication).getGenre().equals(genre);
    }
}
