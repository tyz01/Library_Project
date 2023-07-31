package by.tyzcorporation.library.service.db.controller;

import by.tyzcorporation.library.model.entity.Book;

import by.tyzcorporation.library.service.annotation.TextController;
import by.tyzcorporation.library.service.db.bisneslogic.BookService;

import java.sql.Connection;

@TextController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public void createBook(Book book, Connection connection) {
        bookService.createBook(book, connection);
    }

    public void getAll() {
        bookService.getAll();
    }

    public void removePublication(int idPublication) {
        bookService.removeBook(idPublication);
    }

    public Book getById(Integer idBook) {
        return bookService.getBookById(idBook);
    }
}