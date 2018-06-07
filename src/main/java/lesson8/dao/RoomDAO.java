package lesson8.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import lesson8.entity.Filter;
import lesson8.entity.Hotel;
import lesson8.entity.Room;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoomDAO extends GeneralDAO<Room> {

    private final String FIND_BY_ID = "FROM Room WHERE id = :id ";
    private final String DELETE_BY_ID = "DELETE FROM Room WHERE id = :id";
    private final String FIND_BY_FILTER = " FROM Room WHERE NUM_GUESTS = :NUM_GUESTS, PRICE=:PRICE, BREAKFAST_INCLUD = :BREAKFAST_INCLUD, PETS_ALLOW = :PETS_ALLOW, DATE_FROM = :DATE_FROM, HOTEL.NAME = :NAME, HOTEL.COUNTRY=:COUNTRY, HOTEL.CITY = :CITY";

    HotelDAO hotelDAO;


    public RoomDAO() {
        setTableName("Room");
    }


    @SuppressWarnings("uncheked")
   /* public List<Room> findRooms(Filter filter) {

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


            if (filter.getName() != null)
                criteria.add(Restrictions.eq("hotel.name", filter.getName()));

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

    }*/


    public List<Room> findRooms(Filter filter) {

        List<Room> rooms = null;
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> filterParams = objectMapper.convertValue(filter, Map.class);

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Room> criteriaQuery = builder.createQuery(Room.class);

            Root<Room> roomRoot = criteriaQuery.from(Room.class);
            Join<Hotel, Room> joinHotel = roomRoot.join("hotel"); 


            Predicate predicate = builder.conjunction();


            for (String param : filterParams.keySet()) {
                if (filterParams.get(param) != null) {

                    if (param.equals("name") || param.equals("country") || param.equals("city")) {
                        predicate = builder.and(predicate, builder.equal(
                                joinHotel.get(param), filterParams.get(param)));
                    } else {

                        predicate = builder.and(predicate, builder.equal(
                                roomRoot.get(param), filterParams.get(param)));
                    }
                }
            }
            criteriaQuery.select(roomRoot).where(predicate);

            rooms = session.createQuery(criteriaQuery).getResultList();

        } catch (HibernateException e) {
            e.printStackTrace();
            System.err.println("Smth went wrong");
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
