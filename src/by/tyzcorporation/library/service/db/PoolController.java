package by.tyzcorporation.library.service.db;

import by.tyzcorporation.library.model.entity.Book;

import java.sql.SQLException;

public class PoolController {

    public static void managePool() {
        ConnectionPool pool = new ConnectionPool(5);
        pool.initializePool();

        try {
            DAOController daoController = new DAOController(pool);

            Book book = new Book("Die Spirit", 600, "Gogol", "fantasy",
                    false, 0);
            daoController.insertIntoDatabase(book);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.closeAllConnections();
        }
    }
}
