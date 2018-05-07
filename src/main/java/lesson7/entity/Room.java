package lesson7.entity;


import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "ROOM")
public class Room {
    private long id;
    private int numberOfGuests;
    private double price;
    private int breakfastIncluded;
    private int petsAllowded;
    private Date dateAvailableFrom;
    private Hotel hotel;


    @Id
    @SequenceGenerator(name = "P_SEQ", sequenceName = "ROOM_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "P_SEQ")
    @Column(name = "ROOM_ID")
    public long getId() {
        return id;
    }

    @Column(name = "NUM_GUESTS")
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }

    @Column(name = "BREAKFAST_INCLUD")
    public int getBreakfastIncluded() {
        return breakfastIncluded;
    }

    @Column(name = "PETS_ALLOW")
    public int getPetsAllowded() {
        return petsAllowded;
    }

    @Column(name = "DATE_FROM")
    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "HOTEL")
    public Hotel getHotel() {
        return hotel;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBreakfastIncluded(int breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    public void setPetsAllowded(int petsAllowded) {
        this.petsAllowded = petsAllowded;
    }

    public void setDateAvailableFrom(Date dateAvailableFrom) {
        this.dateAvailableFrom = dateAvailableFrom;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


}
