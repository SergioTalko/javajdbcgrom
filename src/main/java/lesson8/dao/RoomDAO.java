package lesson8.dao;

import lesson8.entity.Filter;
import lesson8.entity.Room;

import java.util.ArrayList;

public class RoomDAO extends GeneralDAO<Room> {

    public RoomDAO() {
        setTableName("Room");
    }





    public ArrayList<Room> findRooms(Filter filter){

        ArrayList<Room> filteredRooms = new ArrayList<>();

        for (Room room : getAll()) {
            if (filter.findRoomByFilter(room)) {
                filteredRooms.add(room);
            }
        }

        return filteredRooms;
    }



}
