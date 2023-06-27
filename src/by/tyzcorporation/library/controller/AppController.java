package by.tyzcorporation.library.controller;

import by.tyzcorporation.library.model.entity.*;
import by.tyzcorporation.library.service.utility.file.DataReader;
import by.tyzcorporation.library.service.utility.file.DataWriter;
import by.tyzcorporation.library.service.utility.search.DataSearcher;
import by.tyzcorporation.library.service.utility.search.SearchStrategy;

import java.util.ArrayList;
import java.util.List;

public class AppController {
    public static void startApplication() {
        Album album = new ConcreteAlbum("tyz", 35);
        Album albumStickerColoring = new AlbumDecorator(new AlbumSticker(new AlbumColoring(album)));


        albumStickerColoring.draw();
        System.out.println(albumStickerColoring.getPageCount());
        System.out.println(albumStickerColoring.getTitle());

        System.out.println("----------------");

        List<Book> books = new ArrayList<>();
        books.add(new ConcreteBook("t", 1, "tyz", "genre"));
        books.add(new ConcreteBook("t2", 2, "tyz2", "genre2"));
        DataSearcher<Book> bookSearcher = new DataSearcher<>(books);

        SearchStrategy<Book> searchStrategy = (book) -> book.getAuthor().equals("tyz2");
        List<Book> result = bookSearcher.search(searchStrategy);

        for (Book book : result) {
            System.out.println(book.getTitle());
        }
        DataWriter<Book> dataWriter = new DataWriter<>();
        dataWriter.write(result, "library.txt");

        DataReader<Book> dataReader = new DataReader<>();
        List<Book> books1 = dataReader.read("library.txt");
        for (Book book: books1) {
            System.out.println(book);
        }
    }

}

