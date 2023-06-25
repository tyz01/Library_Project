package by.tyzcorporation.library.service.filter;

import by.tyzcorporation.library.model.Publication;

@FunctionalInterface
public interface GenreFilter {
    boolean filter(Publication publication);
}
