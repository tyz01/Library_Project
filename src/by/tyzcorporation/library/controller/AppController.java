package by.tyzcorporation.library.controller;

import by.tyzcorporation.library.model.entity.Library;
import by.tyzcorporation.library.service.utility.file.DataReader;

import java.util.Scanner;

public class AppController {
    private int operation;
    private final PublicationController publicationController;

    public AppController() {

        DataReader<Library> publicationRepositoryDataReader = new DataReader<>();
        Library library = publicationRepositoryDataReader.read("library.txt");
        publicationController = new PublicationController(library);
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
                    5: Remove a publication
                    6: Add a publication
                    7: find Publication by title
                    8: find Publication by type
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
                        publicationController.getPublication(type);
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
                        publicationController.returnPublication(type);
                    } else {
                        System.out.println("Invalid publication type");
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred while returning the publication: " + e.getMessage());
                }
            }
            case 4 -> {
                try {
                    publicationController.showAllPublication();
                } catch (Exception e) {
                    System.out.println("An error occurred while showing all publications: " + e.getMessage());
                }
            }
            case 5 -> {
                try {
                    String type = getUserInputType();
                    if (type != null) {
                        publicationController.removePublication(type);
                    } else {
                        System.out.println("Invalid publication type");
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred while removing the publication: " + e.getMessage());
                }
            }
            case 6 -> {
                try {
                    String type = getUserInputType();
                    if (type != null) {
                        publicationController.addPublication(type);
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
                        System.out.println(publicationController.findPublicationByTitle(type));
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
                        System.out.println(publicationController.findPublicationByType(type));
                    } else {
                        System.out.println("Invalid publication type");
                    }
                } catch (Exception e) {
                    System.out.println("An error publication: " + e.getMessage());
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
