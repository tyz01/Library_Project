package by.tyzcorporation.library.model.entity;

import by.tyzcorporation.library.service.annotation.Entity;

import java.io.Serial;
import java.io.Serializable;

@Entity
public class ConcreteAlbum extends Publication implements Album, Serializable {
    private String title;
    private int pageCount;

    @Serial
    private static final long serialVersionUID = 123456789L;
    public ConcreteAlbum(Integer idAlbum, String title, int pageCount) {
        super(idAlbum, title, pageCount);
        System.out.println(idAlbum);
    }

    public ConcreteAlbum( String title, int pageCount) {
        this.title = title;
        this.pageCount = pageCount;
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

//    @Override
//    public int getPublicationId() {
//        return publicationId;
//    }

//    @Override
//    public void setPublicationId(Integer publicationId) {
//        this.publicationId = publicationId;
//    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setPage(int page) {
        this.pageCount = page;
    }

    @Override
    public String toString() {
        return "Album: " + "title = " + getTitle() +
                ", page count = " + getPageCount() +
                ", publicationId = " + getIdPublication();
    }
}