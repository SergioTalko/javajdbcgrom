package lesson7.entity;


import javax.persistence.*;


@Entity
@Table(name = "HOTEL_SECOND")
public class Hotel {



    private long id;
    private String name;
    private String country;
    private String city;
    private String street;


    @Id
    @SequenceGenerator(name = "GEN_SEQ", sequenceName = "HOTEL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "GEN_SEQ")
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

}
