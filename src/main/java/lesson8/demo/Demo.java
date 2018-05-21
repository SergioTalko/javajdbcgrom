package lesson8.demo;


import lesson8.controller.UserController;
import lesson8.dao.HotelDAO;
import lesson8.dao.RoomDAO;
import lesson8.dao.UserDAO;
import lesson8.entity.*;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {

        HotelDAO hotelDAO = new HotelDAO();
        RoomDAO roomDAO = new RoomDAO();
        UserDAO userDAO = new UserDAO();
        Hotel hotel = new Hotel();
        hotel.setCity("Hello");
        hotel.setCountry("Hello");
        hotel.setName("Hello");
        hotel.setStreet("Hello");

//        hotelDAO.save(hotel);

//        System.out.println(hotelDAO.findById(11));

//        System.out.println(hotelDAO.findHotelByName("Hello"));

//        System.out.println(hotelDAO.getAll());

        UserController userController = new UserController();
        User user = new User();
        user.setUserName("H");
        user.setPassword("Hi");
        user.setCountry("jj");
        user.setUserType(UserType.USER);
//        userDAO.save(user);

        Room room = new Room();
        room.setHotel(hotel);
        room.setNumberOfGuests(1);
        room.setPetsAllowed(1);
        room.setBreakfastIncluded(1);
        room.setDateAvailableFrom(new Date(100));

//        roomDAO.save(room);

        Filter filter = new Filter(1,100,1,1,new Date(100),"Hello","Hello","Hello");

        System.out.println(roomDAO.findRooms(filter));




//        userController.login("Hello", "Hello");



    }
}
