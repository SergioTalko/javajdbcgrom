package lesson8.demo;

import lesson8.dao.RoomDAO;
import lesson8.entity.Filter;

public class DemoFilter {
    public static void main(String[] args) {

        Filter filter = new Filter();
//        filter.setNumberOfGuests(1);
//        filter.setPrice(200);
//        filter.setBreakfastIncluded(1);
//        filter.setPetsAllowed(1);
        filter.setHotelName("Ritz");

        RoomDAO roomDAO = new RoomDAO();
        System.out.println(roomDAO.findRooms(filter));
    }
}
