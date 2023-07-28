package by.tyzcorporation.library.model.entity;

import java.io.Serializable;

public interface Album extends Serializable {
    int getId();
    void draw();

    String getTitle();

    int getPageCount();
    int getPublicationId();
    void setPublicationId(Integer publicationId);

    void setTitle(String title);

    void setPage(int page);

}
