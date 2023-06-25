package by.tyzcorporation.library.repository;

import by.tyzcorporation.library.model.Publication;
import by.tyzcorporation.library.model.comporator.PublicationComparator;
import by.tyzcorporation.library.service.type.SortDirection;
import by.tyzcorporation.library.service.type.SortField;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class PublicationRepository implements Iterable<Publication> {
    private final List<Publication> publications;

    public PublicationRepository() {
        publications = new ArrayList<>();
    }

    public void addPublication(Publication publication) {
        publications.add(publication);
    }

    public void removePublication(Publication publication) {
        publications.remove(publication);
    }

    public Publication getPublication(int index) {
        return publications.get(index);
    }

    public boolean isEmpty() {
        return publications.isEmpty();
    }

    public int size() {
        return publications.size();
    }

    public void clear() {
        publications.clear();
    }

    public void sort(SortDirection sortDirection, SortField sortField) {
        Comparator<Publication> comparator = new PublicationComparator(sortDirection, sortField);
        publications.sort(comparator);
    }

    @Override
    public Iterator<Publication> iterator() {
        return publications.iterator();
    }
}

