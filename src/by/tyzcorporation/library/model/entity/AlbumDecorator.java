package by.tyzcorporation.library.model.entity;

import by.tyzcorporation.library.model.entity.Album;

public class AlbumDecorator implements Album {
    protected Album album;

    public AlbumDecorator(Album album) {
        this.album = album;
    }

    @Override
    public void draw() {
        album.draw();
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
    public void setTitle(String title) {

    }

    @Override
    public void setPage(int page) {

    }
}
