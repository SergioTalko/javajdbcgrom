package lesson4.files_storage;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.sql.*;

public class FileDAO {


    private static final String DB_URL = "jdbc:oracle:thin:@socnetwork.c1cgi5vwu7xv.eu-central-1.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "helloworld";






    public File save (File file) throws Exception{

        if (file == null) throw new NullPointerException("Cant save null");


        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO FILES VALUES (?, ?, ?, ?, ?)")) {

            statement.setLong(1, file.getId());
            statement.setString(2, file.getName());
            statement.setString(3,file.getFormat());
            statement.setLong(4, file.getSize());
            statement.setLong(5, file.getStorageId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Cant save file with id " + file.getId() + " try again later.");
        }
        return file;
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

    public void delete (long id) throws Exception{
        try(Connection connection = getConnection();
            PreparedStatement statementStr = connection.prepareStatement("DELETE FROM FILES WHERE FILE_ID = ?")){
            if(findById(id) == null) throw new Exception("Cant find file with id "  + id);

            statementStr.setLong(1, id);
            statementStr.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Some problem with deleting storage with id " + id + " , try again later.");
        }
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
