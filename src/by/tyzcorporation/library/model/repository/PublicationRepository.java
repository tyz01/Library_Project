package by.tyzcorporation.library.model.repository;

import by.tyzcorporation.library.model.comporator.PublicationComparator;
import by.tyzcorporation.library.model.entity.Publication;
import by.tyzcorporation.library.model.entity.type.SortDirectionType;
import by.tyzcorporation.library.model.entity.type.SortFieldType;
import by.tyzcorporation.library.model.exception.logical.NoSuchPublicationException;
import by.tyzcorporation.library.service.utility.file.DataReader;
import by.tyzcorporation.library.service.utility.file.DataWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PublicationRepository implements Iterable<Publication> {
    private static final String PATH_FILE = "library.txt";
    private DataReader<Publication> dataReader = new DataReader<>();
    public DataWriter<Publication> dataWriter = new DataWriter<>();
    private Publication[] publications;
    private int size;

    public PublicationRepository() {
        publications = new Publication[10];
        size = 0;
    }

    public void addPublication(Publication publication) {
        if (size == publications.length) {
            publications = Arrays.copyOf(publications, size * 2);
        }
        publications[size] = publication;
        dataWriter.write(publications, PATH_FILE);
        size++;
    }

    public void removePublication(Publication publication) {
        int index = findPublicationIndex(publication);
        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                publications[i] = publications[i + 1];
            }
            publications[size - 1] = null;
            size--;
        }
    }

    public Publication getPublication(int index) throws NoSuchPublicationException {
        if (index >= 0 && index < size) {
            return publications[index];
        } else {
            throw new NoSuchPublicationException("No publication found at index: " + index);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(publications, null);
        size = 0;
    }

    public void sort(SortDirectionType sortDirectionType, SortFieldType sortFieldType) {
        Comparator<Publication> comparator = new PublicationComparator(sortDirectionType, sortFieldType);
        Arrays.sort(publications, 0, size, comparator);
    }

    private int findPublicationIndex(Publication publication) {
        for (int i = 0; i < size; i++) {
            if (publications[i].equals(publication)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<Publication> iterator() {
        return new PublicationIterator();
    }

    @Override
    public String toString() {
        return "PublicationRepository{" +
                "dataReader=" + dataReader +
                ", dataWriter=" + dataWriter +
                ", publications=" + Arrays.toString(publications) +
                ", size=" + size +
                '}';
    }

    private class PublicationIterator implements Iterator<Publication> {
        private int currentIndex;

        public PublicationIterator() {
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public Publication next() {
            if (hasNext()) {
                Publication publication = publications[currentIndex];
                currentIndex++;
                return publication;
            } else {
                throw new NoSuchElementException("No more publications in the repository.");
            }
        }
    }
}

