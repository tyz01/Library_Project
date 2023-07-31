package by.tyzcorporation.library.service.db.bisneslogic;

import by.tyzcorporation.library.model.entity.Magazine;
import by.tyzcorporation.library.service.db.repository.MagazineRepository;
import by.tyzcorporation.library.service.db.repository.PublicationRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MagazineService {
    private final MagazineRepository magazineRepository;
    private Connection connection;

    public MagazineService(MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    public void createMagazine(Magazine book, Connection connection) {
        try {
            PublicationRepository publicationRepository = new PublicationRepository(connection);
            int publicationId = publicationRepository.insertIntoDatabase(book);
            magazineRepository.create(book, publicationId);
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating book", e);
        }
    }

    public void getAll() {
        try {
            List<Magazine> magazines = magazineRepository.getAll();
            for (Magazine magazine : magazines) {
                System.out.println(magazine);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting all books", e);
        }
    }

    public Magazine getMagazineById(Integer idMagazine) {
        try {
            return magazineRepository.getEntityById(idMagazine);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void removeMagazine(int idPublication) {
        try {
            magazineRepository.delete(idPublication);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

