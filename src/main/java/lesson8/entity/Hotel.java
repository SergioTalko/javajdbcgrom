package lesson8.entity;


import java.util.List;

public class Hotel {

    private long id;
    private String name;
    private String country;
    private String city;
    private String street;

    private List<Room> rooms;


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    //--SETTERS
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;


    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
