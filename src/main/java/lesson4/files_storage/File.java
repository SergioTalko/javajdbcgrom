package lesson4.files_storage;


import java.util.Objects;

public class File {
    private long id;
    private String name;
    private String format;
    private long size;
    private long storageId;


    public File(long id, String name, String format, long size)  throws FileMatchException{
        if (name == null || name.length() > 10) throw new FileMatchException("Cant create file with name " + name + ". Name cant be null and must be less than 10 letters");
        if (id <= 0 || format == null || size <= 0) throw new FileMatchException("Please check file with id " + id +".Some parameters not allow here.");
        this.id = id;
        this.name = name;
        this.format = format;
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }

    public long getSize() {
        return size;
    }

    public long getStorageId() {
        return storageId;
    }

    public void setStorageId(long storageId) {
        this.storageId = storageId;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", size=" + size +
                ", storageId=" + storageId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return id == file.id &&
                Objects.equals(name, file.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
