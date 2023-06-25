package by.tyzcorporation.library.model.comporator;

import by.tyzcorporation.library.model.Publication;
import by.tyzcorporation.library.service.type.SortDirection;
import by.tyzcorporation.library.service.type.SortField;

import java.util.Comparator;

public class PublicationComparator implements Comparator<Publication> {
    private SortDirection sortDirection;
    private SortField sortField;

    public PublicationComparator(SortDirection sortDirection, SortField sortField) {
        this.sortDirection = sortDirection;
        this.sortField = sortField;
    }

    @Override
    public int compare(Publication publication1, Publication publication2) {
        int result = switch (sortField) {
            case TITLE -> publication1.getTitle().compareTo(publication2.getTitle());
            case PAGE_COUNT -> Integer.compare(publication1.getPageCount(), publication2.getPageCount());
        };

        if (sortDirection == SortDirection.DESCENDING) {
            result *= -1; // Инвертируем результат для обратной сортировки
        }
        return result;
    }
}
