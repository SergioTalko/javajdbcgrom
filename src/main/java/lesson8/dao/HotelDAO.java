package lesson8.dao;

import lesson8.entity.Hotel;

import java.util.List;

public class HotelDAO extends GeneralDAO<Hotel> {

    private final String FIND_BY_NAME = "FROM Hotel WHERE name = :param";
    private final String FIND_BY_CITY = "FROM Hotel WHERE city = :param";

    public HotelDAO() {
        setTableName("Hotel");
    }


    public List<Hotel> findHotelByName(String name) {
        return selectByOneParameter(name, FIND_BY_NAME);
    }

    public List<Hotel> findHotelByCity(String city){
        return selectByOneParameter(city, FIND_BY_CITY);
    }


}
