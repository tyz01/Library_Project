package by.tyzcorporation.library.model.entity;

import java.io.Serializable;

public class AlbumDecorator extends Publication implements Album, Serializable {
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
        if (title != null && !title.isEmpty()) {
            throw new IllegalArgumentException("is empty");
        }
    }

    @Override
    public void setPage(int page) {
        if (page <= 0) {
            throw new IllegalArgumentException("incorrect count page");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Album: ")
                .append("title = ").append(getTitle())
                .append(", page count = ").append(getPageCount())
                .append(", borrow = ").append(isBorrow())
                .append(", countBorrowPublication = ").append(getCountBorrowPublication());
        return sb.toString();

    }
}
