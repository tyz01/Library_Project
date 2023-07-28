package by.tyzcorporation.library.model.repository;

import by.tyzcorporation.library.model.comporator.PublicationComparator;
import by.tyzcorporation.library.model.entity.Book;
import by.tyzcorporation.library.model.entity.Library;
import by.tyzcorporation.library.model.entity.Magazine;
import by.tyzcorporation.library.model.entity.Publication;
import by.tyzcorporation.library.model.entity.type.SortDirectionType;
import by.tyzcorporation.library.model.entity.type.SortFieldType;
import by.tyzcorporation.library.model.exception.logical.NoSuchPublicationException;
import by.tyzcorporation.library.model.exception.technical.PublicationRepositoryException;
import by.tyzcorporation.library.service.LibraryStatistics;
import by.tyzcorporation.library.service.db.controller.BookController;
import by.tyzcorporation.library.service.utility.file.DataReader;
import by.tyzcorporation.library.service.utility.file.DataWriter;
import by.tyzcorporation.library.service.utility.search.DataSearcher;
import by.tyzcorporation.library.service.utility.search.SearchStrategy;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class PublicationRepository implements Iterable<Publication>, Serializable {
    private static final String PATH_FILE = "library.txt";
    private final DataReader<Publication> dataReader = new DataReader<>();
    private final DataWriter<Publication> dataWriter = new DataWriter<>();
    private Publication[] publications;
    BookController bookController;
    private int size;

    public PublicationRepository(Publication[] publications) {
        this.publications = publications;
        this.size = publications.length;
    }

    public PublicationRepository() {
        publications = new Publication[10];
        size = 0;
    }

    public void addPublication(Publication publication) {
        if (publication instanceof Book) {
            LibraryStatistics.incrementBookCount();
        }
        if (publication instanceof Magazine) {
            LibraryStatistics.incrementMagazineCount();
        }
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
                publications[i].setBorrow(true);
            }
            publications[size - 1] = null;
            size--;
        }
        throw new PublicationRepositoryException("no such publication");
    }

    private int findPublicationIndex(Publication publication) {
        for (int i = 0; i < size; i++) {
            if (publications[i].equals(publication)) {
                return i;
            }
        }
        throw new PublicationRepositoryException("no such publication");
    }

    public Publication findPublicationByTitle(String title) {
        Publication[] publications1 = readFromFile();
        List<Publication> publications2 = Arrays.stream(publications1).toList();
        DataSearcher<Publication> bookSearcher = new DataSearcher<>(publications2);

        SearchStrategy<Publication> searchStrategy = (publication) -> publication.getTitle().equals("title");

        List<Publication> result = bookSearcher.search(searchStrategy);

        for (Publication book : result) {
            return book;
        }
        return null;
    }

    public List<Publication> findPublicationByType(String type) {
        Publication[] publications = readFromFile();
        List<Publication> filteredPublications = Arrays.stream(publications)
                .filter(p -> p.getClass().getSimpleName().equals(type))
                .collect(Collectors.toList());
        return filteredPublications;
    }

    public void getReadPublication(Publication publication) {
        if (publication.isBorrow()) {
            return;
        }
        int index = findPublicationIndex(publication);
        if (index >= 0 && index < size) {
            publications[index].setBorrow(true);
            int currentCountBorrowPublication = publications[index].getCountBorrowPublication();
            publications[index].setCountBorrowPublication(++currentCountBorrowPublication);
        }
    }

    public void backPublication(Publication publication) {
        int index = findPublicationIndex(publication);
        if (index >= 0 && index < size) {
            publications[index].setBorrow(false);
        }
    }

    public Publication getPublication(int index) throws NoSuchPublicationException {
        if (index >= 0 && index < size) {
            return publications[index];
        }
        throw new NoSuchPublicationException("No publication found at index: " + index);
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

    public void saveRepository() {
        DataWriter<Library> dataWriter = new DataWriter<>();
        Library publications1 = ContainerPublication.repositoryLibrary();
        dataWriter.write(publications1, PATH_FILE);
    }

    public Publication[] readFromFile() {
        DataReader<Publication[]> publicationRepositoryDataReader = new DataReader<>();
        return publicationRepositoryDataReader.read("library.txt");
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
