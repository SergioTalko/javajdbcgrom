package lesson4.files_storage;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.sql.*;

public class FileDAO {


    private static final String DB_URL = "jdbc:oracle:thin:@socnetwork.c1cgi5vwu7xv.eu-central-1.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "helloworld";


    public File save(File file) throws Exception {

        if (file == null) throw new NullPointerException("Cant save null");


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO FILES VALUES (?, ?, ?, ?, ?)")) {

            statement.setLong(1, file.getId());
            statement.setString(2, file.getName());
            statement.setString(3, file.getFormat());
            statement.setLong(4, file.getSize());
            statement.setLong(5, file.getStorageId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Cant save file with id " + file.getId() + " try again later.");
            e.printStackTrace();
        }
        return file;
    }


    public File update(File file) throws Exception {

        if (file == null) throw new NullPointerException("Cant save null");
        if (findById(file.getId()) == null) throw new Exception("Cant find file with id " + file.getId());

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE FILES SET  FILE_NAME = ?, FILE_FORMAT = ?, FILE_SIZE = ?, STORAGE_ID = ? WHERE FILE_ID = ?")) {


            statement.setString(1, file.getName());
            statement.setString(2, file.getFormat());
            statement.setLong(3, file.getSize());
            statement.setLong(4, file.getStorageId());
            statement.setLong(5, file.getId());

            statement.executeUpdate();


        } catch (SQLException e) {
            System.err.println("Cant update file with id " + file.getId() + " try again later.");
            e.printStackTrace();
        }
        return file;
    }

    public File updateStorageId(File file) throws Exception {

        if (file == null) throw new NullPointerException("Cant save null");
        if (findById(file.getId()) == null) throw new Exception("Cant find file with id " + file.getId());

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE FILES SET   STORAGE_ID = ? WHERE FILE_ID = ?")) {

            statement.setLong(1, file.getStorageId());
            statement.setLong(2, file.getId());

            statement.executeUpdate();


        } catch (SQLException e) {
            System.err.println("Cant update storageID of file with id " + file.getId() + " try again later.");
            e.printStackTrace();
        }
        return file;
    }

    public void updateStorageIdArray(File[] files) throws Exception {

        if (files == null) throw new NullPointerException("Cant save null");

        long idFile = 0;
        try (Connection connection = getConnection()) {

        updateStorageId(connection,files);

        } catch (SQLException e) {

            System.err.println("Cant update storageID of file with id " + idFile + " try again later.");
            e.printStackTrace();
        }

    }

    private void updateStorageId(Connection connection, File[] files) throws SQLException {

        try (
                PreparedStatement statement = connection.prepareStatement("UPDATE FILES SET   STORAGE_ID = ? WHERE FILE_ID = ?")) {

            connection.setAutoCommit(false);
            for (File file : files) {
                statement.setLong(1, file.getStorageId());
                statement.setLong(2, file.getId());

                statement.executeUpdate();
            }


            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            throw e;

        }

    }


    public void delete(long id) throws Exception {
        try (Connection connection = getConnection();
             PreparedStatement statementStr = connection.prepareStatement("DELETE FROM FILES WHERE FILE_ID = ?")) {
            if (findById(id) == null) throw new Exception("Cant find file with id " + id);

            statementStr.setLong(1, id);
            statementStr.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Some problem with deleting storage with id " + id + " , try again later.");
        }
    }


    public File findById(long id) throws Exception {
        File resultFile = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM FILES WHERE FILE_ID = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                long fileId = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String format = resultSet.getString(3);
                long size = resultSet.getLong(4);
                long storageId = resultSet.getLong(5);

                resultFile = new File(fileId, name, format, size);
                resultFile.setStorageId(storageId);
            }
        } catch (SQLException e) {
            throw new SQLException("Cant find file with id " + id + " , try again later.");

        }
        return resultFile;
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
