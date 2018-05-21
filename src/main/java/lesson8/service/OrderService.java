package lesson8.service;

import lesson8.dao.OrderDAO;
import lesson8.dao.RoomDAO;
import lesson8.dao.UserDAO;
import lesson8.entity.Order;
import lesson8.entity.Room;
import lesson8.entity.User;

import java.util.Date;

public class OrderService {


    private UserDAO userDAO = new UserDAO();
    private RoomDAO roomDAO = new RoomDAO();
    private OrderDAO orderDAO = new OrderDAO();


    public void bookRoom(long userId, long roomId, Date dateTo) {


        Room room = roomDAO.findById(roomId);
        User user = userDAO.findById(userId);

        Order order = new Order(user, room, dateTo);
        order.setDateFrom(room.getDateAvailableFrom());
        long difference = order.getDateTo().getTime() - order.getDateFrom().getTime();
        long days = difference / (24 * 60 * 60 * 1000);
        order.setMoneyPaid(room.getPrice() * days); // price and int days in order
        room.setDateAvailableFrom(order.getDateTo()); //change availible date to room

        orderDAO.save(order);
        roomDAO.update(room); //update availible date in DB


    }

    public void cancelReservation(long roomId, long userId) {

        Room roomForCancel = roomDAO.findById(roomId);

        for (Order order : orderDAO.getAll()) {
            if (order.getRoom().getId() == roomId && order.getUser().getId() == userId) {
                roomForCancel.setDateAvailableFrom(order.getDateFrom());
                orderDAO.delete(order.getId());
                roomDAO.update(roomForCancel);
                break;
            }


        }


    }
}
