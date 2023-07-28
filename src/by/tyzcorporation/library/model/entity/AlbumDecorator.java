package by.tyzcorporation.library.model.entity;

import java.io.Serial;
import java.io.Serializable;

public class AlbumDecorator extends Publication implements Album, Serializable {
    private int publicationId;
    private int id;
    @Serial
    private static final long serialVersionUID = 1234568L;
    protected Album album;

    public AlbumDecorator(Album album, int id) {
        this.album = album;
        this.id = id;
    }

    public AlbumDecorator(Album album) {
        this.album = album;
    }

    public AlbumDecorator() {
    }

    @Override
    public int getId() {
        return id;
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
    public int getPublicationId() {
        return publicationId;
    }

    @Override
    public void setPublicationId(Integer publicationId) {
        this.publicationId = publicationId;
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
