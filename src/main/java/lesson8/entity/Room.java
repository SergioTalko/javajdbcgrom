package lesson8.entity;


import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ROOM")
public class Room {
    private long id;
    private int numberOfGuests;
    private double price;
    private int breakfastIncluded;
    private int petsAllowed;
    private Date dateAvailableFrom;
    private Hotel hotel;

    @Id
    @SequenceGenerator(name = "ROOM_SEQ", sequenceName = "R_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROOM_SEQ")
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
    public int getPetsAllowed() {
        return petsAllowed;
    }


    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_FROM")
    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel")
    public Hotel getHotel() {
        return hotel;
    }


    //--SETTERS


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

    public void setPetsAllowed(int petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public void setDateAvailableFrom(Date dateAvailableFrom) {
        this.dateAvailableFrom = dateAvailableFrom;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
