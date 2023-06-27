package by.tyzcorporation.library.model.entity;

import by.tyzcorporation.library.model.exception.logical.InvalidPageCountException;

import java.io.Serializable;
import java.util.Objects;

public abstract class Publication implements Comparable<Publication>, Serializable {
    private String title;
    private int pageCount;

    public Publication() {
        this.title = "Untitled";
        this.pageCount = 0;
    }

    public Publication(String title, int pageCount) {
        this.title = title;
        this.pageCount = pageCount;
    }

    public void setPageCount(int pageCount) throws InvalidPageCountException {
        if (pageCount > 0) {
            this.pageCount = pageCount;
        } else {
            throw new InvalidPageCountException("Page count must be greater than 0.");
        }
    }

    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
    }

    public String getTitle() {
        return title;
    }

    public int getPageCount() {
        return pageCount;
    }

    @Override
    public int compareTo(Publication other) {
        return Integer.compare(pageCount, other.pageCount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Publication other = (Publication) obj;
        return pageCount == other.pageCount && Objects.equals(title, other.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, pageCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Publication{")
                .append("title='").append(title).append('\'')
                .append(", pageCount=").append(pageCount)
                .append('}');
        return sb.toString();
    }
}

