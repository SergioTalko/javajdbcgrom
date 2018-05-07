package lesson7.dao;

import lesson7.entity.Room;

public class RoomDAO extends GeneralDAO<Room> {

    public RoomDAO() {
        setTableName("Room");
    }
}
