package lesson4.files_storage;

public class Demo {
    public static void main(String[] args) throws Exception {


//        File file = new File(101, "ffff", "jar", 900);
//        Storage storage = new Storage(8, new String[]{"txt", "jar"}, "UK", 1000);


        Controller controller = new Controller();
//        controller.put(storage,file);

        StorageDAO storageDAO = new StorageDAO();
        FileDAO fileDAO = new FileDAO();


//        storageDAO.save(storage);
        System.out.println(storageDAO.findById(8));
        System.out.println(fileDAO.findById(100));
    }


}
