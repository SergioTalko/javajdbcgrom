package lesson8.service;

import lesson8.entity.Hotel;
import lesson8.dao.HotelDAO;

import java.util.List;

public class HotelService {

    private HotelDAO hotelDAO;

    public void addHotel(Hotel hotel){
        hotelDAO.save(hotel);
    }

    public void deleteHotel(long id){
        hotelDAO.delete(id);
    }

    public void updateHotel(Hotel hotel){
        hotelDAO.update(hotel);
    }

    public List<Hotel> findHotelByName(String name){
        return hotelDAO.findHotelByName(name);
    }

    public List<Hotel> findHotelByCity(String city){
        return hotelDAO.findHotelByCity(city);
    }

    public Hotel getHotelById(long id){
        return hotelDAO.findById(id);
    }

    public List<Hotel> getAllHotel(){
        return hotelDAO.getAll();
    }
}
