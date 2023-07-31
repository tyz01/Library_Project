package by.tyzcorporation.library.controller;

import by.tyzcorporation.library.model.entity.Library;
import by.tyzcorporation.library.model.entity.Publication;
import by.tyzcorporation.library.service.utility.PublicationFactoryUI;
import by.tyzcorporation.library.service.utility.file.DataWriter;

import java.util.List;

public class PublicationFileController {
    private final Library library;
    private final DataWriter<Library> dataWriter;

    public PublicationFileController(Library library) {
        this.library = library;
        this.dataWriter = new DataWriter<>();
    }

    public void returnPublication(String type) {
        library.returnPublication(PublicationFactoryUI.createPublication(type));
        saveLibrary();
    }

    public Publication findPublicationById(Integer idPublication) {
        return library.findPublicationById(idPublication);
    }

    public void removePublication(Integer idPublication) {
        library.removePublication(idPublication);
        saveLibrary();
    }

    public Publication findPublicationByTitle(String title) {
        return library.findPublicationByTitle(title);
    }

    public void getPublication(String type) {
        library.getReadPublication(PublicationFactoryUI.createPublication(type));
        saveLibrary();
    }

    public List<Publication> findPublicationByType(String type) {
        return library.findPublicationByType(type);
    }

    public void addPublication(String type) {
        library.addPublication(PublicationFactoryUI.createPublication(type));
        saveLibrary();
    }

    public void showAllPublication() {
        if (!library.isEmpty()) {
            for (Publication publication : library) {
                System.out.println(publication);
            }
        }
    }

    private void saveLibrary() {
        dataWriter.write(library, "library.txt");
    }
}