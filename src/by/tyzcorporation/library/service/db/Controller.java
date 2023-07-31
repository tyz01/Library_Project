package by.tyzcorporation.library.service.db;

import java.sql.SQLException;
import java.util.List;

public interface Controller<E, K> {
    List<E> getAll() throws SQLException;
    E update(E entity) throws SQLException;
    E getEntityById(K id) throws SQLException;
    boolean delete(K id) throws SQLException;
    int create(E entity, K id) throws SQLException;
}
