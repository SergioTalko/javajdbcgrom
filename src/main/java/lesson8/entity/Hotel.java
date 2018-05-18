package lesson8.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "HOTEL")
public class Hotel {

    private long id;
    private String name;
    private String country;
    private String city;
    private String street;

    private Set<Room> rooms;

    @Id
    @SequenceGenerator(name = "HOTEL_SEQ", sequenceName = "H_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "HOTEL_SEQ")
    @Column(name = "HOTEL_ID")
    public long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    //TODO Change to LAZY
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "hotel")
    public Set<Room> getRooms() {
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

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
