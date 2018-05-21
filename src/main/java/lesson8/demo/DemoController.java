package lesson8.demo;

import lesson8.controller.*;
import lesson8.entity.Hotel;
import lesson8.entity.Room;

import java.util.Date;

public class DemoController {
    public static void main(String[] args) throws Exception {

        HotelController hotelController = new HotelController();
        UserController userController = new UserController();
        OrderController orderController = new OrderController();
        RoomController roomController = new RoomController();

        userController.login("admin", "admin");

        Hotel hotel = new Hotel();
        hotel.setCity("Hello");
        hotel.setCountry("Hello");
        hotel.setName("Hello");
        hotel.setStreet("Hello");
        Room room = new Room();
        room.setHotel(hotel);
        room.setNumberOfGuests(1);
        room.setPetsAllowed(1);
        room.setBreakfastIncluded(1);
        room.setDateAvailableFrom(new Date(100));

        System.out.println(Session.getUserInSession());

//        roomController.addRoom(room);


    }
}
