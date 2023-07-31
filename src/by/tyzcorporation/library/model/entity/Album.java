package by.tyzcorporation.library.model.entity;

import java.io.Serializable;

public interface Album extends Serializable {
    void draw();

    String getTitle();

    int getPageCount();

    void setTitle(String title);

    void setPage(int page);

}
