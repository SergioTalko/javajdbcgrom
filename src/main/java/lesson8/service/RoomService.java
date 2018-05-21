package lesson8.service;

import lesson8.dao.RoomDAO;
import lesson8.entity.Filter;
import lesson8.entity.Room;

import java.util.List;

public class RoomService {

    private RoomDAO roomDAO;


    public void addRoom(Room room){
        roomDAO.save(room);
    }

    public void deleteRoom(long id){
        roomDAO.delete(id);
    }

    public void update(Room room){
        roomDAO.update(room);
    }


    public Room findRoomById(long id){
        return roomDAO.findById(id);
    }

    public List<Room> getAllRooms(){
        return roomDAO.getAll();
    }


    public List<Room> findRoomsByFilter(Filter filter){
        return roomDAO.findRooms(filter);
    }


}
