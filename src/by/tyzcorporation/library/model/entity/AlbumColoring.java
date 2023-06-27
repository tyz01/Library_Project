package by.tyzcorporation.library.model.entity;

public class AlbumColoring extends AlbumDecorator {
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
}
