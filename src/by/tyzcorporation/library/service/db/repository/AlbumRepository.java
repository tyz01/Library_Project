package by.tyzcorporation.library.service.db.repository;

import by.tyzcorporation.library.model.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AlbumRepository extends AbstractRepository<Album, Integer> {
    private static final Logger logger = Logger.getLogger(AlbumRepository.class.getName());
    private final Connection connection;

    public AlbumRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Album> getAll() throws SQLException {
        List<Album> books = new ArrayList<>();

        String getAllBooksQuery = "SELECT * FROM Album";

        try (PreparedStatement preparedStatement = connection.prepareStatement(getAllBooksQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                int pageCount = resultSet.getInt("pageCount");
                Album magazine = new ConcreteAlbum(title, pageCount);
                logger.info("magazine" + magazine);
                books.add(magazine);
            }
        } catch (SQLException e) {
            throw e;
        }

        return books;
    }

    @Override
    public Album update(Album entity) {
        return null;
    }

    @Override
    public Album getEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(Album entity) {
        return false;
    }

    @Override
    public int insertIntoDatabase(Album album, Integer publicationId) throws SQLException {
        String insertAlbumQuery = "INSERT INTO Album (title, pageCount, albumData, borrow, countBorrowAlbum, publicationId) VALUES (?, ?, ?, ?, ?, ?)";
        String insertDecoratorQuery = "INSERT INTO AlbumDecorator (decoratorType, albumId) VALUES (?, ?)";

        int generatedId = 0;
        String decoratorType = album.getClass().getSimpleName();

        try (PreparedStatement albumStatement = connection.prepareStatement(insertAlbumQuery, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement decoratorStatement = connection.prepareStatement(insertDecoratorQuery)) {

            albumStatement.setString(1, album.getTitle());
            albumStatement.setInt(2, album.getPageCount());
            albumStatement.setString(3, decoratorType);
            albumStatement.setBoolean(4, false);
            albumStatement.setInt(5, 0);
            albumStatement.setInt(6, generatedId);

            int affectedRows = albumStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to insert Album entry.");
            }

            try (ResultSet generatedKeys = albumStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                    albumStatement.setInt(6, generatedId);
                    albumStatement.executeUpdate();
                    System.out.println("id album: " + generatedId);
                } else {
                    throw new SQLException("Failed to get the generated ID for Album entry.");
                }
            }

            decoratorStatement.setString(1, decoratorType);
            decoratorStatement.setInt(2, generatedId);
            decoratorStatement.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }

        return generatedId;
    }
}
