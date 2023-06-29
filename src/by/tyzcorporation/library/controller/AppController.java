package by.tyzcorporation.library.controller;

import by.tyzcorporation.library.model.entity.Library;
import by.tyzcorporation.library.model.entity.Publication;
import by.tyzcorporation.library.model.repository.ContainerPublication;
import by.tyzcorporation.library.service.utility.PublicationFactoryUI;
import by.tyzcorporation.library.service.utility.file.DataReader;
import by.tyzcorporation.library.service.utility.file.DataWriter;

import java.util.Scanner;

import static by.tyzcorporation.library.service.utility.PublicationFactoryUI.getUserInputType;

public class AppController {
    private final DataWriter<Library> dataWriter = new DataWriter<>();
    private final Library library;

    private int operation;

    public AppController() {
       // library = ContainerPublication.repositoryLibrary();
        DataReader<Library> publicationRepositoryDataReader = new DataReader<>();
        library = publicationRepositoryDataReader.read("library.txt");
    }

    public void start() {
        //saveRepository();
        saveRepository();
        System.out.println("enter library 0 - true" + "\n" +
                "exit library 1 - false"
        );
        operation = new Scanner(System.in).nextInt();
        while (isExit(operation)) {
            System.out.println("""
                    choose operation:\s
                    1: show information library
                    2: get Publication
                    3: return Publication
                    4: show all publication
                    5: remove publication
                    6: add Publication
                    7: exit""");

            switchController(new Scanner(System.in).nextInt());
        }
    }

    public void switchController(int numberOperation) {
        switch (numberOperation) {
            case 2, 3, 5, 6 -> System.out.println("BOOK, ALBUM or MAGAZINE");
            case 7 -> System.out.println("""
                    are you sure?
                    0 - true
                    1 - false"""
            );
        }
        switch (numberOperation) {
            // case 1 -> System.out.println(showListTitleLibrary());
            case 2 -> getPublication(getUserInputType());
            case 3 -> returnPublication(getUserInputType());
            case 4 -> showAllPublication();
            case 5 -> removePublication(getUserInputType());
            case 6 -> addPublication(getUserInputType());
            case 7 -> {
                operation = new Scanner(System.in).nextInt();
                operation = isExit(operation) ? 1 : 0;
            }
        }
    }
    public void showAllPublication() {
        DataReader<Library> libraryDataReader = new DataReader<>();
        Library repository = libraryDataReader.read("library.txt");
        if (repository != null) {
            for (Publication publication : repository) {
                System.out.println(publication);
            }
        }
    }
    public void returnPublication(String type) {
        library.returnPublication(PublicationFactoryUI.createPublication(type));
        saveRepository();
    }

    private void removePublication(String type) {
        library.removePublication(PublicationFactoryUI.createPublication(type));
        saveRepository();
    }

    private void getPublication(String type) {
        library.getReadPublication(PublicationFactoryUI.createPublication(type));
        saveRepository();
    }

    private void addPublication(String type) {
        library.getReadPublication(PublicationFactoryUI.createPublication(type));
        saveRepository();
    }

    public boolean isExit(int operation) {
        return operation != 1;
    }

    public void saveRepository() {
        dataWriter.write(library, "library.txt");
    }
}

//        List<Magazine> magazines = publications1.stream()
//                .filter(p -> p instanceof Magazine)
//                .map(p -> (Magazine) p).toList();
//        for (Magazine magazine : magazines) {
//            System.out.println(magazine);
//        }
//        Album album = new ConcreteAlbum("tyz", 35);
//        Album albumStickerColoring = new AlbumDecorator(new AlbumSticker(new AlbumColoring(album)));
//
//
//        albumStickerColoring.draw();
//        System.out.println(albumStickerColoring.getPageCount());
//        System.out.println(albumStickerColoring.getTitle());
//
//        System.out.println("----------------");
//
//        List<Book> books = new ArrayList<>();
//        books.add(new ConcreteBook("t", 1, "tyz", "genre"));
//        books.add(new ConcreteBook("t2", 2, "tyz2", "genre2"));
//
//        DataSearcher<Book> bookSearcher = new DataSearcher<>(books);
//
//        SearchStrategy<Book> searchStrategy = (book) -> book.getAuthor().equals("tyz2");
//
//        List<Book> result = bookSearcher.search(searchStrategy);
//
//        for (Book book : result) {
//            System.out.println(book.getTitle());
//        }
//        DataWriter<Book> dataWriter = new DataWriter<>();
//        dataWriter.write(result, "library.txt");
//
//        DataReader<Book> dataReader2 = new DataReader<>();
//        List<Book> books1 = dataReader2.readArray("library.txt");
//        for (Book book : books1) {
//            System.out.println(book);
//        }
//    }
//
//        DataReader<Book> dataReader2 = new DataReader<>();
//        List<Book> books1 = dataReader2.readArray("library.txt");
//        for (Book book : books1) {
//            System.out.println(book);
//        }
// System.out.println(LibraryStatistics.getBookCount());
//  System.out.println(LibraryStatistics.getMagazineCount());

//        DataReader<Publication> dataReader = new DataReader<>();
//        List<Publication> publications1 = dataReader.readList("library.txt");
//        for (Publication pub : publications1) {
//            System.out.println(pub);
// }