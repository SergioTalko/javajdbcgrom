package lesson4.files_storage;


import java.nio.file.FileAlreadyExistsException;

public class Controller {

    private FileDAO fileDAO = new FileDAO();
    private StorageDAO storageDAO = new StorageDAO();

    public void put(Storage storage, File file) throws Exception {

        storage = storageDAO.findById(storage.getId());

        if (storage == null || file == null) throw new NullPointerException("Check your input data");


        if (storage.getStorageSize() < file.getSize())
            throw new Exception("Cant save file with id " + file.getId() + " in storage with id " + storage.getId() + " because not enough free space there");

        if (!checkAllowedFormat(storage, file))
            throw new Exception("Cant save file with id " + file.getId() + " in storage with id " + storage.getId() + " because file format not allow in this storage");


        try {
            file.setStorageId(storage.getId());
            storage.setStorageSize(storage.getStorageSize() - file.getSize());

            storageDAO.updateSize(storage);

            if (fileDAO.findById(file.getId()) != null)
                throw new FileAlreadyExistsException("File with id " + file.getId() + " already exist");


            fileDAO.save(file);


        } catch (Exception e) {
            System.err.println("Cant put file with id " + file.getId() + " in storage with id " + storage.getId() + " . Please try again later");
            e.printStackTrace();
        }

    }


    public void delete(Storage storage, File file) throws Exception {
        if (storage == null || file == null) throw new NullPointerException("Check your input data");

        storage = storageDAO.findById(storage.getId());

        try {
            storage.setStorageSize(storage.getStorageSize() + file.getSize());
            file.setStorageId(0);
            fileDAO.updateStorageId(file);
            storageDAO.updateSize(storage);
        } catch (Exception e) {
            System.err.println("Cant delete file with id " + file.getId() + " from storage with id " + storage.getId());
        }


    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        if (storageFrom == null || storageTo == null) throw new NullPointerException("Check your input data");

        storageFrom = storageDAO.findById(storageFrom.getId());
        storageTo = storageDAO.findById(storageTo.getId());
        File[] filesFrom = storageDAO.findFilesByStorageId(storageFrom.getId());
        long sizeOfFilesFromStorage = 0;

        for (File file : filesFrom) {
            if (file != null) {
                sizeOfFilesFromStorage += file.getSize();
            }
        }

        if (storageTo.getStorageSize() < sizeOfFilesFromStorage)
            throw new Exception("You have only " + storageTo.getStorageSize() + " free space in storage with id " + storageTo.getId() + " And you try transfer files total space of" + sizeOfFilesFromStorage);

        storageFrom.setStorageSize(storageFrom.getStorageSize() + sizeOfFilesFromStorage);


        for (File file : filesFrom) {
            if (checkAllowedFormat(storageTo, file)) {
                file.setStorageId(storageTo.getId());
            } else
                throw new Exception("Cant transfer files in storage with id " + storageTo.getId() + " from storage with id " + storageFrom.getId() + " .Because file format not allowed there");
        }

        storageTo.setStorageSize(storageTo.getStorageSize() - sizeOfFilesFromStorage);


        storageDAO.updateSize(storageFrom);
        fileDAO.updateStorageIdArray(filesFrom);
        storageDAO.updateSize(storageTo);


    }


    public void transferFile(Storage storageFrom, Storage storageTo, long id) {
        if (storageFrom == null || storageTo == null || id <= 0)
            throw new NullPointerException("Check your input data");
    }


    private boolean checkAllowedFormat(Storage storage, File file) {

        String[] formars = storage.getFormatsSupported().split(",");
        for (String format : formars) {
            if (file.getFormat().equals(format)) {
                return true;
            }
        }
        return false;
    }


}
