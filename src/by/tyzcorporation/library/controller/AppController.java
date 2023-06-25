package by.tyzcorporation.library.controller;

import by.tyzcorporation.library.model.Book;
import by.tyzcorporation.library.model.Library;
import by.tyzcorporation.library.model.Publication;
import by.tyzcorporation.library.service.DataSearcher;
import by.tyzcorporation.library.service.LibraryStatistics;
import by.tyzcorporation.library.service.SearchStrategy;

import java.util.ArrayList;
import java.util.List;

public class AppController {
    public static void startApplication() {
        Library library = new Library();
        library.addPublication(new Publication("Book 1", 200));
        library.addPublication(new Publication("Book 2", 150));
        library.addPublication(new Publication("Book 3", 300));

        System.out.println(LibraryStatistics.getBookCount());
        List<Book> books = new ArrayList<>();

        books.add(new Book("t", 1, "tyz", "genre"));
        books.add(new Book("t2", 2, "tyz2", "genre2"));
        DataSearcher<Book> bookSearcher = new DataSearcher<>(books);


        SearchStrategy<Book> searchStrategy = (book) -> book.getAuthor().equals("tyz2");
        List<Book> result = bookSearcher.search(searchStrategy);

        for (Book book : result) {
            System.out.println(book.getTitle());
        }
    }
    //Magazine magazine = new Magazine("tyz", 12, "category", "genre");

}

