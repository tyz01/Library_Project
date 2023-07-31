package by.tyzcorporation.library.service.db.controller;

import by.tyzcorporation.library.model.entity.Publication;
import by.tyzcorporation.library.service.db.bisneslogic.PublicationService;

public class PublicationController {
    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    public void createBook(Publication publication) {
        publicationService.createPublication(publication);
    }

    public void getAll() {
        publicationService.getAll();
    }

    public void removePublication(int idPublication) {
        publicationService.removePublication(idPublication);
    }

    public Publication getById(Integer idBook) {
        return publicationService.getPublicationById(idBook);
    }
}

