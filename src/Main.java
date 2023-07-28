import by.tyzcorporation.library.controller.AppController;
import by.tyzcorporation.library.service.db.connector.PosgresConnector;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws InterruptedException, SQLException {
      //  LoggerConfigurator.configureLogger();
        PosgresConnector.connect(); //ToDo try with resources
        //  AppController appController = new AppController();
        //appController.start();
       // ConnectionPool pool = new ConnectionPool(5);
      //  pool.initializePool();
     //   Connection connection = pool.getConnection();

    //    BookRepository bookRepository = new BookRepository(connection);
    //    PublicationRepository publicationRepository = new PublicationRepository(connection);
      //  BookService bookService = new BookService(bookRepository, publicationRepository);

     //   BookController bookController = new BookController(bookService);
     //   Book book = new Book("RedLine1", 1111, "Nogach1", "fantasy", false, 0);
//        bookController.createBook(book);
//        bookController.getAll();
       // AlbumRepository albumRepository = new AlbumRepository(connection);
      //  AlbumService albumService = new AlbumService(albumRepository, publicationRepository);
       // Album concreteAlbum = new ConcreteAlbum("My Album 8", 22);
        //AlbumColoring albumColoring = new AlbumColoring(concreteAlbum);
     //   AlbumController albumController = new AlbumController(albumService);
        //albumController.createAlbum(concreteAlbum);

     //   Album concreteAlbum = new ConcreteAlbum("My Album 13", 22);
      //  AlbumColoring albumColoring = new AlbumColoring(concreteAlbum); // Remove the 'id' parameter here
      //  AlbumController albumController = new AlbumController(albumService);
      //  albumController.createAlbum(albumColoring, 10);

     //   System.out.println("book with id = 10: " + bookController.getById(10));
       // connection.close();
     //   pool.closeAllConnections();

        AppController appController = new AppController();
        appController.start();
    }

}
