package by.tyzcorporation.library.model.entity;

import by.tyzcorporation.library.model.exception.logical.NoSuchPublicationException;
import by.tyzcorporation.library.model.repository.ContainerPublication;
import by.tyzcorporation.library.model.repository.PublicationRepository;
import by.tyzcorporation.library.service.utility.PublicationFactoryUI;
import by.tyzcorporation.library.service.utility.file.DataReader;
import by.tyzcorporation.library.service.utility.file.DataWriter;

import java.io.Serializable;
import java.util.Iterator;

public abstract class Library implements Iterable<Publication>, Serializable {
   // private final DataWriter<Library> dataWriter = new DataWriter<>();
    private final PublicationRepository publicationRepository;
   // DataReader<Library> publicationRepositoryDataReader = new DataReader<>();
   // Library library = publicationRepositoryDataReader.read("library.txt");
  // Library library = ContainerPublication.repositoryLibrary();
    public Library() {
        publicationRepository = new PublicationRepository();
    }


    public void addPublication(Publication publication) {

        publicationRepository.addPublication(publication);
        //publicationRepository.saveRepository();
    }


    //    public void saveRepository() {
//        dataWriter.write(library, "library.txt");
//    }
    public void removePublication(Publication publication) {
        publicationRepository.removePublication(publication);
    }

    public Publication getPublication(int index) throws NoSuchPublicationException {
        return publicationRepository.getPublication(index);
    }

    public void getReadPublication(Publication publication) {
        publicationRepository.getReadPublication(publication);
    }

        public void returnPublication(Publication publication) {
        publicationRepository.backPublication(publication);
    }
//    public void returnPublication(String type) {
//        publicationRepository.backPublication(PublicationFactoryUI.createPublication(type));
//        saveRepository();
//    }
//    public void saveRepository() {
//        dataWriter.write(library, "library.txt");
//    }
    public boolean isEmpty() {
        return publicationRepository.isEmpty();
    }

    public int size() {
        return publicationRepository.size();
    }

    public void clear() {
        publicationRepository.clear();
    }

    @Override
    public Iterator<Publication> iterator() {
        return publicationRepository.iterator();
    }
}