package lesson8.entity;

import java.util.Date;

public class Filter {

    private int numberOfGuests;
    private double price;
    private int breakfastIncluded = -1; //this -1 needs to filtering all rooms by HotelName for example(coz its 0 or 1)
    private int petsAllowed = -1;
    private Date dateAvailableFrom;
    private String hotelName;
    private String country;
    private String city;

    public Filter() {
    }

    public Filter(int numberOfGuests, double price, int breakfastIncluded, int petsAllowed, Date dateAvailableFrom, String hotelName, String country, String city) {
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.hotelName = hotelName;
        this.country = country;
        this.city = city;
    }


    public boolean findRoomByFilter(Room room) {
        if (numberOfGuests != 0 && room.getNumberOfGuests() != numberOfGuests) return false;
        if (price != 0 && room.getPrice() != price) return false;
        if (breakfastIncluded != room.getBreakfastIncluded()) return false;
        if (petsAllowed != room.getPetsAllowed()) return false;
        if (dateAvailableFrom != null && dateAvailableFrom.getTime() < room.getDateAvailableFrom().getTime())
            return false;
        if (hotelName != null && !hotelName.equals(room.getHotel().getName())) return false;
        if (country != null && !country.equals(room.getHotel().getCountry())) return false;
        if (city != null && !city.equals(room.getHotel().getCity())) return false;

        return true;


    }


    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public double getPrice() {
        return price;
    }

    public int getBreakfastIncluded() {
        return breakfastIncluded;
    }

    public int getPetsAllowed() {
        return petsAllowed;
    }

    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
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

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
