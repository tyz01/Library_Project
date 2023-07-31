package by.tyzcorporation.library.model.comporator;

import by.tyzcorporation.library.model.entity.Publication;
import by.tyzcorporation.library.model.entity.type.SortDirectionType;
import by.tyzcorporation.library.model.entity.type.SortFieldType;

import java.util.Comparator;

public class PublicationComparator implements Comparator<Publication> {
    private final SortDirectionType sortDirectionType;
    private final SortFieldType sortFieldType;
    public PublicationComparator(SortDirectionType sortDirectionType, SortFieldType sortFieldType) {
        this.sortDirectionType = sortDirectionType;
        this.sortFieldType = sortFieldType;
    }

    @Override
    public int compare(Publication publication1, Publication publication2) {
        int result = switch (sortFieldType) {
            case TITLE -> compareByTitle(publication1, publication2);
            case PAGE_COUNT -> compareByPageCount(publication1, publication2);
        };

        return applySortDirection(result, sortDirectionType);
    }

    private int compareByTitle(Publication publication1, Publication publication2) {
        return publication1.getTitle().compareTo(publication2.getTitle());
    }

    private int compareByPageCount(Publication publication1, Publication publication2) {
        return Integer.compare(publication1.getPageCount(), publication2.getPageCount());
    }
    private int applySortDirection(int result, SortDirectionType sortDirectionType) {
        if (sortDirectionType == SortDirectionType.DESCENDING) {
            result *= -1;
        }
        return result;
    }
}
