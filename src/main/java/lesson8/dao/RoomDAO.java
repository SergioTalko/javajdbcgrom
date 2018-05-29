package lesson8.dao;

import lesson8.entity.Filter;
import lesson8.entity.Hotel;
import lesson8.entity.Room;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO extends GeneralDAO<Room> {

    private final String FIND_BY_ID = "FROM Room WHERE id = :id ";
    private final String DELETE_BY_ID = "DELETE FROM Room WHERE id = :id";
    private final String FIND_BY_FILTER = " FROM Room WHERE NUM_GUESTS = :NUM_GUESTS, PRICE=:PRICE, BREAKFAST_INCLUD = :BREAKFAST_INCLUD, PETS_ALLOW = :PETS_ALLOW, DATE_FROM = :DATE_FROM, HOTEL.NAME = :NAME, HOTEL.COUNTRY=:COUNTRY, HOTEL.CITY = :CITY";

    HotelDAO hotelDAO;


    public RoomDAO() {
        setTableName("Room");
    }


    @SuppressWarnings("uncheked")
    public List<Room> findRooms(Filter filter) {

        List<Room> rooms = null;

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {


            Criteria criteria = session.createCriteria(Room.class).
                    createAlias("hotel", "hotel");

            if (filter.getNumberOfGuests() >= 1)
                criteria.add(Restrictions.eq("numberOfGuests", filter.getNumberOfGuests()));

            if (filter.getPrice() >= 1)
                criteria.add(Restrictions.eq("price", filter.getPrice()));


            if (filter.getBreakfastIncluded() == 0 || filter.getBreakfastIncluded() == 1)
                criteria.add(Restrictions.eq("breakfastIncluded", filter.getBreakfastIncluded()));

            if (filter.getPetsAllowed() == 0 || filter.getPetsAllowed() == 1)
                criteria.add(Restrictions.eq("petsAllowed", filter.getPetsAllowed()));

            if (filter.getDateAvailableFrom() != null)
                criteria.add(Restrictions.eq("dateAvailableFrom", filter.getDateAvailableFrom()));


            if (filter.getHotelName() != null)
                criteria.add(Restrictions.eq("hotel.name", filter.getHotelName()));

            if (filter.getCountry() != null)
                criteria.add(Restrictions.eq("hotel.country", filter.getCountry()));
            if (filter.getCity() != null)
                criteria.add(Restrictions.eq("hotel.city", filter.getCity()));

            return rooms = criteria.list();




        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Something went wrong");
        }
        return rooms;

    }


    public Room findById(long id) {
        return findById(id, FIND_BY_ID);
    }


    public void delete(long id) {
        super.delete(id, DELETE_BY_ID);
    }
}
