package by.tyzcorporation.library.service.utility;

import by.tyzcorporation.library.model.entity.*;

import java.util.Scanner;

public class PublicationFactoryUI {
    public static Publication createPublication(String type) {
        Scanner scanner = new Scanner(System.in);
        String title;
        String author;
        String genre;
        int pageCount;
        switch (type) {
            case "BOOK" -> {
                System.out.print("Enter book title: ");
                title = scanner.nextLine();
                System.out.print("Enter book author: ");
                author = scanner.nextLine();
                System.out.print("Enter book genre: ");
                genre = scanner.nextLine();
                pageCount = getUserInputInt("Enter book page count: ");
                return new ConcreteBook(title, pageCount, author, genre, false, 0);
            }
            case "MAGAZINE" -> {
                System.out.print("Enter magazine title: ");
                title = scanner.nextLine();
                System.out.print("Enter magazine category: ");
                String category = scanner.nextLine();
                System.out.print("Enter magazine genre: ");
                genre = scanner.nextLine();
                pageCount = getUserInputInt("Enter magazine page count: ");
                return new ConcreteMagazine(title, pageCount, category, genre, false, 0);
            }
            case "ALBUM" -> {
                System.out.print("Enter album title: ");
                title = scanner.nextLine();
                pageCount = getUserInputInt("Enter album page count: ");
                return new ConcreteAlbum(title, pageCount, false, 0);
            }
            default -> throw new IllegalArgumentException("Invalid publication type: " + type);
        }
    }

    public static int getUserInputInt(String message) {
        Scanner scanner = new Scanner(System.in);
        int value;
        while (true) {
            try {
                System.out.print(message);
                value = Integer.parseInt(scanner.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Invalid input. Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
            }
        }
    }
}
