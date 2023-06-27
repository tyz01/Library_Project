package by.tyzcorporation.library.model.entity;

import by.tyzcorporation.library.model.entity.Album;
import by.tyzcorporation.library.model.entity.AlbumDecorator;

public class AlbumSticker extends AlbumDecorator {
    public AlbumSticker(Album album) {
        super(album);
    }
    @Override
    public void draw() {
        super.draw();
        System.out.println("glue");
    }
    @Override
    public String getTitle() {
        return album.getTitle();
    }

    @Override
    public int getPageCount() {
        return album.getPageCount();
    }
}
