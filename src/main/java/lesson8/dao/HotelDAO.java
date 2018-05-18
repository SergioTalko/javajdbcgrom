package lesson8.dao;

import lesson8.entity.Hotel;

import java.util.List;

public class HotelDAO extends GeneralDAO<Hotel> {

    private final String FIND_BY_NAME = "FROM Hotel WHERE name = :param";
    private final String FIND_BY_CITY = "FROM Hotel WHERE city = :param";

    public HotelDAO() {
        setTableName("Hotel");
    }


  /*  public Hotel get(Hotel hotel) {
        Transaction transaction = null;
        Hotel hotel1 = null;
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            hotel1 = (Hotel) session.get(hotel.getName(), hotel.getId());
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
            if (transaction != null) transaction.rollback();

        }
        return hotel1;
    }*/

    public List<Hotel> findHotelByName(String name) {
        return selectByOneParameter(name, FIND_BY_NAME);
    }

    public List<Hotel> findHotelByCity(String city){
        return selectByOneParameter(city, FIND_BY_CITY);
    }


}
