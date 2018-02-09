package lesson4.files_storage;

import java.util.Arrays;

public class Storage {

    private long id;
    private File[] files;
    private String[] formatsSupported;
    private String storageCountry;
    private long storageSize;


    public Storage(long id, String[] formatsSupported, String storageCountry, long storageSize) throws Exception {
        if (id <= 0 ||  storageSize <= 0) throw new Exception("Please check storage with id " + id +".Some parameters not allow here.");
        this.id = id;
        this.formatsSupported = formatsSupported;
        this.storageCountry = storageCountry;
        this.storageSize = storageSize;
    }

    public long getId() {
        return id;
    }


    public File[] getFiles() {
        return files;
    }

    public String getFormatsSupported() {
        String res = "";

        for (String s : formatsSupported){
            res += "," + s;
        }
        return res.substring(1);
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    public long getStorageSize() {
        return storageSize;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public void setFormatsSupported(String[] formatsSupported) {
        this.formatsSupported = formatsSupported;
    }

    public void setStorageCountry(String storageCountry) {
        this.storageCountry = storageCountry;
    }

    public void setStorageSize(long storageSize) {
        this.storageSize = storageSize;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", files=" + Arrays.toString(files) +
                ", formatsSupported=" + Arrays.toString(formatsSupported) +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageSize=" + storageSize +
                '}';
    }
}
