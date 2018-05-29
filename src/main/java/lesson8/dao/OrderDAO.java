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

    private final String FIND_BY_ID = "FROM Order WHERE id = :id ";
    private final String DELETE_BY_ID = "DELETE FROM Order WHERE id = :id";


    private UserDAO userDAO;


    public OrderDAO() {
       setTableName("Orders");
    }


    public Order findById(long id) {
        return super.findById(id, FIND_BY_ID);
    }

    public void delete(long id) {
        super.delete(id, DELETE_BY_ID);
    }
}
