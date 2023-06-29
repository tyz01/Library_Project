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
import by.tyzcorporation.library.service.utility.file.DataReader;
import by.tyzcorporation.library.service.utility.file.DataWriter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PublicationRepository implements Iterable<Publication>, Serializable {
    private static final String PATH_FILE = "library.txt";
    private final DataReader<Publication> dataReader = new DataReader<>();
    private final DataWriter<Publication> dataWriter = new DataWriter<>();
    private Publication[] publications;
    private int size;

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
    }

    private int findPublicationIndex(Publication publication) {
        for (int i = 0; i < size; i++) {
            if (publications[i].equals(publication)) {
                return i;
            }
        }
        throw new PublicationRepositoryException("no such publication");
    }

    public void getReadPublication(Publication publication) {
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


//    public List<String> getListTitleLibrary() {
//        List<String> title = new ArrayList<>();
//        for (Library library : publications) {
//            title.add(library.getTitle());
//        }
//        return title;
//    }

//    public Library chooseLibrary(String name) {
//        for (ConcreteLibrary library : publications) {
//            if ()
//        }
//    }

    //    public Publication[] findPublicationsByParameters(int minPageCount, int maxPageCount) throws NoSuchPublicationException {
//        Publication[] foundPublications = new Publication[size];
//        int foundCount = 0;
//        for (int i = 0; i < size; i++) {
//            Publication publication = getPublication(i);
//            int pageCount = publication.getPageCount();
//            if (pageCount >= minPageCount && pageCount <= maxPageCount) {
//                foundPublications[foundCount] = publication;
//                foundCount++;
//            }
//        }
//        return Arrays.copyOf(foundPublications, foundCount);
//    }
//    public Publication findMostPopularPublication() {
//        Publication mostPopularPublication = null;
//        int maxBorrowCount = 0;
//        for (Publication publication : publications) {
//            int borrowCount = LibraryStatistics.getPublicationBorrowCount(publication);
//            if (borrowCount > maxBorrowCount) {
//                maxBorrowCount = borrowCount;
//                mostPopularPublication = publication;
//            }
//        }
//        return mostPopularPublication;
//    }
//    public Publication findLeastPopularPublication() {
//        Publication leastPopularPublication = null;
//        int minBorrowCount = Integer.MAX_VALUE;
//        for (Publication publication : publications) {
//            int borrowCount = LibraryStatistics.getPublicationBorrowCount(publication);
//            if (borrowCount < minBorrowCount) {
//                minBorrowCount = borrowCount;
//                leastPopularPublication = publication;
//            }
//        }
//        return leastPopularPublication;
//    }
//    public int countBooksOnHands() {
//        return LibraryStatistics.getBookCount();
//    }
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

