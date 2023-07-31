package by.tyzcorporation.library.service.db.bisneslogic;

import by.tyzcorporation.library.model.entity.Album;
import by.tyzcorporation.library.service.db.repository.AlbumRepository;

import java.sql.SQLException;
import java.util.List;

public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public void createAlbum(Album album, int idPublication) {
        try {
            albumRepository.create(album, idPublication);
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
    public Album getAlbumById(Integer idBook) {
        return albumRepository.getEntityById(idBook);
    }
}
