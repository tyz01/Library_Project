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
                System.out.print("Enter book page count: ");
                pageCount = scanner.nextInt();
                return new ConcreteBook(title, pageCount, author, genre, false, 0);
            }
            case "MAGAZINE" -> {
                System.out.print("Enter magazine title: ");
                title = scanner.nextLine();
                System.out.print("Enter magazine category: ");
                String category = scanner.nextLine();
                System.out.print("Enter magazine genre: ");
                genre = scanner.nextLine();
                System.out.print("Enter magazine page count: ");
                pageCount = scanner.nextInt();
                return new ConcreteMagazine(title, pageCount, category, genre, false, 0);
            }
            case "ALBUM" -> {
                System.out.print("Enter album title: ");
                title = scanner.nextLine();
                System.out.print("Enter album page count: ");
                pageCount = scanner.nextInt();
                return new ConcreteAlbum(title, pageCount, false, 0);
            }
            default -> throw new IllegalArgumentException("Invalid publication type: " + type);
        }
    }
    public static String getUserInputType() {
        return new Scanner(System.in).nextLine();
    }
}
