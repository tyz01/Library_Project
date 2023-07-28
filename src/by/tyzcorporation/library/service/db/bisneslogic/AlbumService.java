package by.tyzcorporation.library.service.db.bisneslogic;

import by.tyzcorporation.library.model.entity.Album;
import by.tyzcorporation.library.service.db.repository.AlbumRepository;
import by.tyzcorporation.library.service.db.repository.PublicationRepository;

import java.sql.SQLException;
import java.util.List;

public class AlbumService {
    private final AlbumRepository albumRepository;
    private final PublicationRepository publicationRepository;

    public AlbumService(AlbumRepository albumRepository, PublicationRepository publicationRepository) {
        this.albumRepository = albumRepository;
        this.publicationRepository = publicationRepository;
    }

    public void createAlbum(Album album, int idPublication) {
        try {
          //  int publicationId = albumRepository.insertIntoDatabase(album, album.getPublicationId());
            albumRepository.insertIntoDatabase(album, idPublication);
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating album", e);
        }
    }

    public void getAll() {
        try {
            List<Album> albums = albumRepository.getAll();
            for (Album book : albums) {
                System.out.println(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting all albums", e);
        }
    }

    public Album getBookById(Integer idBook) {
        return albumRepository.getEntityById(idBook);
    }
}
