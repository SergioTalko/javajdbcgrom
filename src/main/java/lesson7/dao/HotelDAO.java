package lesson7.dao;

import lesson7.entity.Hotel;

public class HotelDAO extends GeneralDAO<Hotel> {

    public HotelDAO() {
        setTableName("Hotel");
    }
}
