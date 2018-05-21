package lesson8.demo;

import lesson8.dao.OrderDAO;
import lesson8.dao.RoomDAO;
import lesson8.dao.UserDAO;
import lesson8.entity.Order;
import lesson8.service.OrderService;

import java.util.Date;

public class DemoOrder {
    public static void main(String[] args) {


        UserDAO userDAO = new UserDAO();
        RoomDAO roomDAO = new RoomDAO();
        OrderService orderService = new OrderService();
//        Order order = new Order();
//        order.setUser(userDAO.findById(1));
//        order.setRoom(roomDAO.findById(2));
//        order.setDateTo(new Date());

//        orderService.bookRoom(1,2,new Date());

        System.out.println( userDAO.getAll());



    }
}
