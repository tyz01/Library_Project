package by.tyzcorporation.library.model.entity;

import by.tyzcorporation.library.model.exception.logical.NoSuchPublicationException;
import by.tyzcorporation.library.model.repository.PublicationFileRepository;
import by.tyzcorporation.library.service.annotation.Entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
@Entity
public class Library implements Iterable<Publication>, Serializable {
    @Serial
    private static final long serialVersionUID = 1234571L;
    private Integer idLibrary;
    private int idPublication;
    private final PublicationFileRepository publicationFileRepository;

    public Library() {
        publicationFileRepository = new PublicationFileRepository();
    }

    public void addPublication(Publication publication) {
        publicationFileRepository.addPublication(publication);
    }

    public void removePublication(int idPublication) {
        publicationFileRepository.removePublication(idPublication);
    }

    public Publication getPublication(int index) throws NoSuchPublicationException {
        return publicationFileRepository.getPublication(index);
    }

    public List<Publication> findPublicationByType(String type) {
        return publicationFileRepository.findPublicationByType(type);
    }

    public void getReadPublication(Publication publication) {
        publicationFileRepository.getReadPublication(publication);
    }

    public Publication findPublicationById(Integer idPublication) {
        return publicationFileRepository.findPublicationById(idPublication);
    }

    public Publication findPublicationByTitle(String title) {
        return publicationFileRepository.findPublicationByTitle(title);
    }

    public void returnPublication(Publication publication) {
        publicationFileRepository.backPublication(publication);
    }

    public boolean isEmpty() {
        return publicationFileRepository.isEmpty();
    }

    public int size() {
        return publicationFileRepository.size();
    }

    public void clear() {
        publicationFileRepository.clear();
    }

    public int getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }

    public Integer getIdLibrary() {
        return idLibrary;
    }

    public void setIdLibrary(Integer idLibrary) {
        this.idLibrary = idLibrary;
    }

    public PublicationFileRepository getPublicationRepository() {
        return publicationFileRepository;
    }

    @Override
    public Iterator<Publication> iterator() {
        return publicationFileRepository.iterator();
    }
}