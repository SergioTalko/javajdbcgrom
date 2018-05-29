package lesson8.dao;

import lesson8.entity.Hotel;

import java.util.List;

public class HotelDAO extends GeneralDAO<Hotel> {

    private final String FIND_BY_NAME = "FROM Hotel WHERE name = :param";
    private final String FIND_BY_CITY = "FROM Hotel WHERE city = :param";
    private final String FIND_BY_ID = "FROM Hotel WHERE id = :id ";
    private final String DELETE_BY_ID = "DELETE FROM Hotel WHERE id = :id";

    public HotelDAO() {
        setTableName("Hotel");
    }


    public List<Hotel> findHotelByName(String name) {
        return selectByOneParameter(name, FIND_BY_NAME);
    }

    public List<Hotel> findHotelByCity(String city) {
        return selectByOneParameter(city, FIND_BY_CITY);
    }

    public Hotel findById(long id) {
        return findById(id, FIND_BY_ID);
    }

    public void delete(long id) {
        super.delete(id, DELETE_BY_ID);
    }
}
