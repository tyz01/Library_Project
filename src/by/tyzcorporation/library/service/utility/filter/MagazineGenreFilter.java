package by.tyzcorporation.library.service.utility.filter;

import by.tyzcorporation.library.model.entity.Magazine;
import by.tyzcorporation.library.model.entity.Publication;

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
