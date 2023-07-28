package by.tyzcorporation.library.service.db.repository;

import by.tyzcorporation.library.model.entity.Library;
import by.tyzcorporation.library.service.db.repository.AbstractRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LibraryRepository extends AbstractRepository<Library, Integer> {
    private final Connection connection;

    public LibraryRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Library> getAll() throws SQLException {
        return null;
    }

    @Override
    public Library update(Library entity) {
        return null;
    }

    @Override
    public Library getEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(Library entity) {
        return false;
    }

    @Override
    public int insertIntoDatabase(Library entity, Integer id) throws SQLException {
        return 0;
    }
//    public void insertIntoLibrary(int publicationId) throws SQLException {
//        String insertIntoLibrary = "INSERT INTO Library (publicationId) VALUES (?)";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(insertIntoLibrary)) {
//            preparedStatement.setInt(1, publicationId);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            connection.rollback();
//            throw e;
//        }
//    }
}
