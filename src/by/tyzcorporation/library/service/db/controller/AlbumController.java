package by.tyzcorporation.library.service.db.controller;

import by.tyzcorporation.library.model.entity.Album;
import by.tyzcorporation.library.service.annotation.TextController;
import by.tyzcorporation.library.service.db.bisneslogic.AlbumService;

@TextController
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    public void createAlbum(Album album, int idPublication) {
        albumService.createAlbum(album, idPublication);
    }
}
