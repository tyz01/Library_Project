package by.tyzcorporation.library.service;

import by.tyzcorporation.library.exception.NoSuchPublicationException;
import by.tyzcorporation.library.model.Publication;

import java.util.Iterator;
import java.util.List;

public class LibraryIterator implements Iterator<Publication> {
    private List<Publication> publications;
    private int index;

    public LibraryIterator(List<Publication> publications) {
        this.publications = publications;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < publications.size();
    }

    @Override
    public Publication next() {
        if (!hasNext()) {
            try {
                throw new NoSuchPublicationException("no suh publication");
            } catch (NoSuchPublicationException e) {
                throw new RuntimeException(e);
            }
        }
        return publications.get(index++);
    }
}
