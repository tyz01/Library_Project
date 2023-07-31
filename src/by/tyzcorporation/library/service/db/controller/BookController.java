package by.tyzcorporation.library.service.db.controller;

import by.tyzcorporation.library.model.entity.Book;

import by.tyzcorporation.library.service.db.bisneslogic.BookService;

public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public void createBook(Book book) {
        bookService.createBook(book);
    }

    public void getAll() {
        bookService.getAll();
    }

    public void removePublication(int idPublication) {
        bookService.removePublication(idPublication);
    }

    public Book getById(Integer idBook) {
        return bookService.getBookById(idBook);
    }
}