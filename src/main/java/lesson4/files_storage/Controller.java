package lesson4.files_storage;


public class Controller {

   private FileDAO fileDAO = new FileDAO();

    public void put(Storage storage, File file) throws Exception{

        if (storage == null || file == null) throw new NullPointerException("Check your input data");


            if(storage.getStorageSize() < file.getSize()) throw new Exception("Cant save file with id " + file.getId() + " in storage with id " + storage.getId() + " because not enough free space there");

            if (!checkAllowedFormat(storage, file)) throw new Exception("Cant save file with id " + file.getId() + " in storage with id " + storage.getId() + " because file format not allow in this storage");

            file.setStorageId(storage.getId());


                fileDAO.save(file);
                storage.setStorageSize(storage.getStorageSize() - file.getSize());
        }


    public void delete(Storage storage, File file){


    }

    public void transferAll(Storage storageFrom, Storage storageTo){

    }

    public void transferFile(Storage storageFrom,Storage storageTo, long id){

    }

    private boolean checkAllowedFormat(Storage storage, File file){

        String[] formars = storage.getFormatsSupported().split(",");
        for (String format : formars){
            if (file.getFormat().equals(format)){
                return true;
            }
        }
        return false;
    }


}
