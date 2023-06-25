package by.tyzcorporation.library.model;

import by.tyzcorporation.library.repository.PublicationRepository;
import by.tyzcorporation.library.service.LibraryStatistics;

import java.util.*;

public class Library implements Iterable<Publication> {
    private PublicationRepository publicationRepository;

    public Library() {
        publicationRepository = new PublicationRepository();
    }

    public void addPublication(Publication publication) {
        LibraryStatistics.incrementBookCount();
        publicationRepository.addPublication(publication);
    }

    public void removePublication(Publication publication) {
        publicationRepository.removePublication(publication);
    }

    public Publication getPublication(int index) {
        return publicationRepository.getPublication(index);
    }

    public boolean isEmpty() {
        return publicationRepository.isEmpty();
    }

    public int size() {
        return publicationRepository.size();
    }

    public void clear() {
        publicationRepository.clear();
    }

    @Override
    public Iterator<Publication> iterator() {
        return publicationRepository.iterator();
    }
}