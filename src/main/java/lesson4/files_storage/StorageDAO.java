package lesson4.files_storage;

import java.nio.file.FileAlreadyExistsException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StorageDAO {


    private static final String DB_URL = "jdbc:oracle:thin:@socnetwork.c1cgi5vwu7xv.eu-central-1.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "helloworld";

    public Storage save(Storage storage) throws Exception {
        if (storage == null) throw new NullPointerException("Cant save null");


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO STORAGE VALUES (?, ?, ?, ?)")) {


            statement.setLong(1, storage.getId());
            statement.setString(2, storage.getFormatsSupported());
            statement.setString(3, storage.getStorageCountry());
            statement.setLong(4, storage.getStorageSize());


            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Cant save file with id " + storage.getId() + " try again later.");
        }
        return storage;
    }

    public Storage findById(long id) throws Exception {
        Storage resultStorage = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM STORAGE WHERE STORAGE_ID = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();


           if (resultSet.next()) {
               String[] formats = resultSet.getString(2).split(",");
               String country = resultSet.getString(3);
               long size = resultSet.getLong(4);


               resultStorage = new Storage(id, formats, country, size);
               File[] files = findFilesByStorageId(id);
               resultStorage.setFiles(files);
           }

        } catch (SQLException e) {
            System.err.println("Smth went wrong when you try find storage with id" + id);;

        }
        return resultStorage;
    }


    private File[] findFilesByStorageId(long id) throws FileMatchException {
        List<File> files = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM FILES WHERE STORAGE_ID = ?")) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                long fileId = result.getLong(1);
                String name = result.getString(2);
                String formats = result.getString(3);
                long file_size = result.getLong(4);
                long storageId = result.getLong(5);

                File foundedFile = new File(fileId, name, formats, file_size);
                foundedFile.setStorageId(storageId);
                files.add(foundedFile);
            }
        } catch (SQLException e) {
            System.err.println("Smth went wrong with files from storage with id " + id);
            ;
        }
        File[] filesArray = new File[files.size()];
        return files.toArray(filesArray);
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
