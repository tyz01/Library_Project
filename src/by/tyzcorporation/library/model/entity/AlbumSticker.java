package by.tyzcorporation.library.model.entity;

import java.io.Serial;
import java.io.Serializable;

public class AlbumSticker extends AlbumDecorator implements Serializable {
    @Serial
    private static final long serialVersionUID = 12345679L;
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

    @Override
    public String toString() {
        return "AlbumSticker " + album;
    }
}
