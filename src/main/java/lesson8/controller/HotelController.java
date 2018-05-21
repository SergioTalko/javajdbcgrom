package lesson8.controller;

import lesson8.entity.Hotel;
import lesson8.entity.UserType;
import lesson8.service.HotelService;

import java.nio.file.AccessDeniedException;
import java.util.List;


public class HotelController {
    private HotelService hotelService = new HotelService();


    public List<Hotel> findHoteByName(String name) throws Exception {
        if (Session.getUserInSession() != null) {
            return hotelService.findHotelByName(name);
        } else
            throw new AccessDeniedException("Please login in system");
    }

    public List<Hotel> findHotelByCity(String city) throws Exception {
        if (Session.getUserInSession() != null) {
            return hotelService.findHotelByCity(city);
        } else
            throw new AccessDeniedException("Please login in system");
    }

    public void deleteHotel(Hotel hotel) throws Exception {
        if (Session.getUserInSession() == null) throw new AccessDeniedException("You dont login in system");

        if (Session.getUserInSession().getUserType() == UserType.ADMIN) {
            hotelService.deleteHotel(hotel.getId());
        } else {
            throw new AccessDeniedException("User with name " + Session.getUserInSession().getUserName() + "  have not access to this operation");


        }
    }


    public void addHotel(Hotel hotel) throws Exception {

        if (Session.getUserInSession() == null) throw new AccessDeniedException("You dont login in system");

        if (Session.getUserInSession().getUserType() == UserType.ADMIN) {
            hotelService.addHotel(hotel);
        } else {
            throw new AccessDeniedException("User with name " + Session.getUserInSession().getUserName() + "  have not access to this operation");

        }
    }


}
