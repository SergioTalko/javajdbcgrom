package lesson8.demo;

import lesson8.controller.*;
import lesson8.dao.HotelDAO;
import lesson8.entity.Hotel;
import lesson8.entity.Room;

import java.util.Date;

public class DemoController {
    public static void main(String[] args) throws Exception {

        HotelController hotelController = new HotelController();
        UserController userController = new UserController();
        OrderController orderController = new OrderController();
        RoomController roomController = new RoomController();
        HotelDAO hotelDAO = new HotelDAO();

        System.out.println( hotelDAO.findHotelByName("Ritz"));

//        roomController.addRoom(room);


    }
}
