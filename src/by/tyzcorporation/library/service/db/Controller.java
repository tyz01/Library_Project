package by.tyzcorporation.library.service.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Controller<E, K> {
    List<E> getAll();
    E update(E entity);
    E getEntityById(K id);
    boolean delete(K id);
    boolean create(E entity);
    int insertIntoDatabase(E entity) throws SQLException;
}
