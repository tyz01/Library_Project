package by.tyzcorporation.library.model.entity;

import by.tyzcorporation.library.model.entity.Album;

import java.io.Serial;
import java.io.Serializable;

public class ConcreteAlbum extends Publication implements Album, Serializable {
    private String title;
    private int pageCount;
    private int publicationId;
    private Integer id;

    @Serial
    private static final long serialVersionUID = 123456789L;
    public ConcreteAlbum(String title, int pageCount, int publicationId) {
        this.title = title;
        this.pageCount = pageCount;
        this.publicationId = publicationId;
    }

    public ConcreteAlbum(Integer id, String title, int pageCount, int publicationId) {
        this.id = id;
        this.title = title;
        this.pageCount = pageCount;
        this.publicationId = publicationId;
    }
    public ConcreteAlbum( String title, int pageCount) {
        this.title = title;
        this.pageCount = pageCount;
    }
    public ConcreteAlbum(Integer id, String title, int pageCount) {
        this.id = id;
        this.title = title;
        this.pageCount = pageCount;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void draw() {
        System.out.println("Drawing album");
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getPageCount() {
        return pageCount;
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
        this.title = title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void setPage(int page) {
        this.pageCount = page;
    }

    @Override
    public String toString() {
        return "Album: " + "title = " + getTitle() + ", page count = " + getPageCount() + ", publicationId = " + getPublicationId();
    }
}