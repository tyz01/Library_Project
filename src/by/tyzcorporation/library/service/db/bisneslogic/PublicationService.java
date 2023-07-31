package by.tyzcorporation.library.service.db.bisneslogic;

import by.tyzcorporation.library.model.entity.Publication;
import by.tyzcorporation.library.service.db.repository.PublicationRepository;

import java.sql.SQLException;
import java.util.List;

public class PublicationService {
    private final PublicationRepository publicationRepository;

    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public void createPublication(Publication publication) {
        try {
            int publicationId = publicationRepository.create(publication, publication.getIdPublication());
            publicationRepository.create(publication, publicationId);
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating publication", e);
        }
    }

    public void getAll() {
        try {
            List<Publication> publications = publicationRepository.getAll();
            for (Publication publication : publications) {
                System.out.println(publication);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting all books", e);
        }
    }

    public Publication getPublicationById(Integer idBook) {
        return publicationRepository.getEntityById(idBook);
    }

    public void removePublication(int idPublication) {
        publicationRepository.delete(idPublication);
    }
}
