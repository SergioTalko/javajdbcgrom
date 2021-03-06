package lesson4.files_storage;

import java.io.FileNotFoundException;
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

    public void delete(long id) throws Exception {
        try (Connection connection = getConnection();
             PreparedStatement statementStr = connection.prepareStatement("DELETE FROM STORAGE WHERE STORAGE_ID = ?")) {
            if (findById(id).getFiles().length > 0)
                throw new Exception("Cant delete storage with files. First you must delete all files in storage with id " + id);

            statementStr.setLong(1, id);
            statementStr.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Some problem with deleting storage with id " + id + " , try again later.");
        }
    }

    public Storage update(Storage storage) throws Exception {

        if (storage == null) throw new NullPointerException("Cant save null");
        if (findById(storage.getId()) == null) throw new Exception("Cant find storage with id " + storage.getId());

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE STORAGE SET  FORMATS = ?, COUNTRY = ?, STORAGE_SIZE = ? WHERE STORAGE_ID = ?")) {


            if (findFilesByStorageId(storage.getId()).length != 0)
                throw new Exception("You try update info about storage with files.This changing might be not allowed here. So first delete files or transfer all files in another storage");


            statement.setString(1, storage.getFormatsSupported());
            statement.setString(2, storage.getStorageCountry());
            statement.setLong(3, storage.getStorageSize());
            statement.setLong(4, storage.getId());

            statement.executeUpdate();


        } catch (SQLException e) {
            System.err.println("Cant update storage with id " + storage.getId() + " try again later.");
        }
        return storage;
    }

    public Storage updateSize(Storage storage) throws Exception {

        if (storage == null) throw new NullPointerException("Cant save null");
        if (findById(storage.getId()) == null) throw new Exception("Cant find storage with id " + storage.getId());

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE STORAGE SET STORAGE_SIZE = ? WHERE STORAGE_ID = ?")) {

            statement.setLong(1, storage.getStorageSize());
            statement.setLong(2, storage.getId());

            statement.executeUpdate();


        } catch (SQLException e) {
            System.err.println("Cant update storage size with id " + storage.getId() + " try again later.");
            e.printStackTrace();
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
            System.err.println("Smth went wrong when you try find storage with id" + id);
            ;

        }
        return resultStorage;
    }


    public File[] findFilesByStorageId(long id) throws FileMatchException, SQLException {
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
        }


        File[] filesArray = new File[files.size()];
        return files.toArray(filesArray);
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
