package lesson4.files_storage;

public class Demo {
    public static void main(String[] args) throws Exception {


        File file = new File(1001, "hello", "jar", 1000);
        Storage storage = new Storage(2, new String[]{"png,txt", "jar"}, "UK", 900);
        Storage storage1 = new Storage(8);


        Controller controller = new Controller();
//        controller.put(storage,file);
//        controller.delete(storage,file);
        controller.transferFile(storage1, storage, 100);
        StorageDAO storageDAO = new StorageDAO();
        FileDAO fileDAO = new FileDAO();


//        storageDAO.save(storage);
//        System.out.println(storageDAO.findById(8));
//        System.out.println(fileDAO.findById(100));


//        System.out.println(fileDAO.update(file));



    }


}
