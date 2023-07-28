package by.tyzcorporation.library.service.db.repository;

import by.tyzcorporation.library.model.entity.Publication;
import by.tyzcorporation.library.service.db.repository.AbstractRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PublicationRepository extends AbstractRepository<Publication, Integer> {
    private final Connection connection;

    public PublicationRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Publication> getAll() throws SQLException {
        String SELECT_ALL_PUBLICATION = "SELECT * FROM Publication";
        List<Publication> publications = new LinkedList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_PUBLICATION);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Publication publication = new Publication();
                publication.setIdPublication(resultSet.getInt(1));
                publication.setTitle(resultSet.getString(2));
                System.out.println(publication);
                publications.add(publication);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

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

    @Override
    public int insertIntoDatabase(Publication publication, Integer id) throws SQLException {
        String insertPublicationQuery = "INSERT INTO Publication (title, pageCount, borrow, countBorrowPublication) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertPublicationQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, publication.getTitle());
            preparedStatement.setInt(2, publication.getPageCount());
            preparedStatement.setBoolean(3, publication.isBorrow());
            preparedStatement.setInt(4, publication.getCountBorrowPublication());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
               id = generatedKeys.getInt(1);
               return id;
            }
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
        throw new SQLException("Failed to insert publication.");

    }

}

