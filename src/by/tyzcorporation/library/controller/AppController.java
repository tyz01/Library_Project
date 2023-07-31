package by.tyzcorporation.library.controller;

import by.tyzcorporation.library.model.entity.Library;
import by.tyzcorporation.library.service.db.connector.ConnectionPool;
import by.tyzcorporation.library.service.db.bisneslogic.PublicationService;
import by.tyzcorporation.library.service.db.controller.PublicationController;
import by.tyzcorporation.library.service.db.repository.PublicationRepository;
import by.tyzcorporation.library.service.utility.file.DataReader;

import java.sql.Connection;
import java.util.Scanner;

public class AppController {
    private int operation;
    private PublicationFileController publicationFileController;
    private final PublicationController publicationController;


    {
        ConnectionPool pool = new ConnectionPool(5);
        pool.initializePool();
        Connection connection;
        try {
            connection = pool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        PublicationRepository publicationRepository = new PublicationRepository(connection);
        publicationController = new PublicationController(new PublicationService(publicationRepository));

    }

    public AppController() {
//        Library library1 = new Library();
//        library1.addPublication(new ConcreteAlbum(1,"t2", 2));
//         DataWriter<Library> publicationDataWriter = new DataWriter<>();
//         publicationDataWriter.write(library1, "library.txt");

        DataReader<Library> publicationRepositoryDataReader = new DataReader<>();
        Library library = publicationRepositoryDataReader.read("library.txt");
        if (library.isEmpty()) {
            library = new Library();
        } else {
            publicationFileController = new PublicationFileController(library);
        }
    }

    public void start() {
        System.out.println("Enter 0 to continue or 1 to exit the library:");
        operation = new Scanner(System.in).nextInt();
        while (isExit(operation)) {
            System.out.println("""
                    Choose an operation:
                    1: Show library information
                    2: Get a publication
                    3: Return a publication
                    4: Show all publications
                    5: Remove a publication by id
                    6: Add a publication
                    7: find Publication by title
                    8: find Publication by type
                    9: find publication by id
                    0: Exit""");

            switchController(new Scanner(System.in).nextInt());
        }
    }

    public void switchController(int numberOperation) {
        if (numberOperation < 0 || numberOperation > 9) {
            System.out.println("No such operation");
        }
        switch (numberOperation) {
            case 2, 3, 5, 6 -> System.out.println("BOOK, ALBUM, or MAGAZINE");
            case 0 -> {
                System.out.println("""
                        Are you sure you want to exit?
                        0 - Yes
                        1 - No"""
                );
                operation = new Scanner(System.in).nextInt();
                operation = isExit(operation) ? 1 : 0;
            }
        }
        switch (numberOperation) {
            case 2 -> {
                try {
                    String type = getUserInputType();
                    if (type != null) {
                        publicationFileController.getPublication(type);
                    } else {
                        System.out.println("Invalid publication type");
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred while getting the publication: " + e.getMessage());
                }
            }
            case 3 -> {
                try {
                    String type = getUserInputType();
                    if (type != null) {
                        publicationFileController.returnPublication(type);
                    } else {
                        System.out.println("Invalid publication type");
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred while returning the publication: " + e.getMessage());
                }
            }
            case 4 -> {
                try {
                    publicationFileController.showAllPublication();
                    publicationController.getAll();
                } catch (Exception e) {
                    System.out.println("An error occurred while showing all publications: " + e.getMessage());
                }
            }
            case 5 -> {
                try {
                    System.out.println("print id: ");
                    int idPublication = new Scanner(System.in).nextInt();
                    publicationFileController.removePublication(idPublication);
                    publicationController.removePublication(idPublication);
                } catch (Exception e) {
                    System.out.println("An error delete publication: " + e.getMessage());
                }
            }
            case 6 -> {
                try {
                    String type = getUserInputType();
                    if (type != null) {
                        publicationFileController.addPublication(type);
                    } else {
                        System.out.println("Invalid publication type");
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred while adding the publication: " + e.getMessage());
                }
            }
            case 7 -> {
                try {
                    String type = getUserInputType();
                    if (type != null) {
                        System.out.println(publicationFileController.findPublicationByTitle(type));
                    } else {
                        System.out.println("Invalid publication type");
                    }
                } catch (Exception e) {
                    System.out.println("An error publication: " + e.getMessage());
                }
            }
            case 8 -> {
                try {
                    String type = getUserInputType();
                    System.out.println("book, album, or magazine");
                    if (type != null) {
                        System.out.println(publicationFileController.findPublicationByType(type));
                    } else {
                        System.out.println("Invalid publication type");
                    }
                } catch (Exception e) {
                    System.out.println("An error publication: " + e.getMessage());
                }
            }
            case 9 -> {
                try {
                    System.out.println("print id: ");
                    int idPublication = new Scanner(System.in).nextInt();
                    System.out.println(publicationFileController.findPublicationById(idPublication));
                    System.out.println(publicationController.getById(idPublication));
                } catch (Exception e) {
                    System.out.println("An error find publication: " + e.getMessage());
                }
            }
        }
    }

    public boolean isExit(int operation) {
        return operation != 1;
    }

    public String getUserInputType() {
        System.out.println("Enter publication type (BOOK, ALBUM, or MAGAZINE):");
        String type = new Scanner(System.in).nextLine();
        if (isValidPublicationType(type)) {
            return type;
        } else {
            return null;
        }
    }

    public boolean isValidPublicationType(String type) {
        return type.equals("BOOK") || type.equals("ALBUM") || type.equals("MAGAZINE");
    }
}
