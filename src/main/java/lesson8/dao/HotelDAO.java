package lesson8.dao;

import lesson7.entity.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HotelDAO extends GeneralDAO<Hotel> {

    public HotelDAO() {
        setTableName("Hotel");
    }


    public Hotel get(Hotel hotel){
        Transaction transaction = null;
        Hotel hotel1 = null;
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            hotel1 = (Hotel) session.get(hotel.getName(),hotel.getId());
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
            if (transaction != null) transaction.rollback();

        }
        return hotel1;
    }
}
