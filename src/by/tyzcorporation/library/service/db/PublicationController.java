package by.tyzcorporation.library.service.db;

import by.tyzcorporation.library.model.entity.Book;
import by.tyzcorporation.library.model.entity.Publication;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PublicationController {
    private final Connection connection;

    public PublicationController( Connection connection) throws InterruptedException {
        this.connection = connection;
    }

    public List<Publication> getAll() {
        List<Publication> publications = new LinkedList<>();
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = getPrepareStatement(SELECT_ALL_PUBLICATION);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Publication publication = new Publication();
//                publication.setIdPublication(resultSet.getInt(1));
//                publication.setTitle(resultSet.getString(2));
//                System.out.println(publication);
//                publications.add(publication);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closePrepareStatement(preparedStatement);
//        }

        return publications;
    }

    public Publication update(Publication entity) {
        return null;
    }

    public Publication getEntityById(Integer id) {
        return null;
    }

    public boolean delete(Integer id) {
        return false;
    }

    public boolean create(Publication entity) {
        return false;
    }

    public int insertIntoDatabase(Publication entity) {
        return 0;
    }

    public int insertPublication(Book book) throws SQLException {
        String insertPublicationQuery = "INSERT INTO Publication (title, pageCount, borrow, countBorrowPublication) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertPublicationQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getPageCount());
            preparedStatement.setBoolean(3, book.isBorrow());
            preparedStatement.setInt(4, book.getCountBorrowPublication());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }

        throw new SQLException("Failed to insert publication.");
    }
}

