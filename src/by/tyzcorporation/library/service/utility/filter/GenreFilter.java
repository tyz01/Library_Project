package by.tyzcorporation.library.service.utility.filter;

import by.tyzcorporation.library.model.entity.Publication;

@FunctionalInterface
public interface GenreFilter {
    boolean filter(Publication publication);
}
