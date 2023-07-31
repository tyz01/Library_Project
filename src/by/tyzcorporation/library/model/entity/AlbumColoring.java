package by.tyzcorporation.library.model.entity;

import java.io.Serial;
import java.io.Serializable;

public class AlbumColoring extends AlbumDecorator implements Serializable {
    @Serial
    private static final long serialVersionUID = 1234567L;
    private int id;

    public AlbumColoring(Album album, int id) {
        super(album, id);
    }

    public AlbumColoring(Album album) {
        super(album);
    }

    @Override
    public void draw() {
        super.draw();
        System.out.println("coloring");
    }

    @Override
    public String getTitle() {
        return album.getTitle();
    }

    @Override
    public int getPageCount() {
        return album.getPageCount();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AlbumColoring " + album ;
    }
}
