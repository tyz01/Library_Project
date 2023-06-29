package by.tyzcorporation.library.model.entity;

import by.tyzcorporation.library.model.exception.logical.NoSuchPublicationException;
import by.tyzcorporation.library.model.repository.PublicationRepository;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public abstract class Library implements Iterable<Publication>, Serializable {
    private final PublicationRepository publicationRepository;

    public Library() {
        publicationRepository = new PublicationRepository();
    }

    public void addPublication(Publication publication) {
        publicationRepository.addPublication(publication);
    }

    public void removePublication(Publication publication) {
        publicationRepository.removePublication(publication);
    }

    public Publication getPublication(int index) throws NoSuchPublicationException {
        return publicationRepository.getPublication(index);
    }
    public List<Publication> findPublicationByType(String type){
        return publicationRepository.findPublicationByType(type);
    }
    public void getReadPublication(Publication publication) {
        publicationRepository.getReadPublication(publication);
    }

    public Publication findPublicationByTitle(String title) {
        return publicationRepository.findPublicationByTitle(title);
    }

    public void returnPublication(Publication publication) {
        publicationRepository.backPublication(publication);
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