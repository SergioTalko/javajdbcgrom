package lesson8.dao;

import lesson8.entity.Order;
import lesson8.entity.Room;
import lesson8.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class OrderDAO extends GeneralDAO<Order> {

    private UserDAO userDAO;


    public OrderDAO() {
       setTableName("Orders");
    }




}
