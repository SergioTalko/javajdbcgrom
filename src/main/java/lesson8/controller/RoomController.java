package lesson8.controller;


import lesson8.entity.Filter;
import lesson8.entity.Room;
import lesson8.entity.UserType;
import lesson8.service.RoomService;

import java.nio.file.AccessDeniedException;
import java.util.List;

public class RoomController {

    private RoomService roomService = new RoomService();

    public void addRoom(Room room) throws Exception {
        if (Session.getUserInSession() == null) throw new AccessDeniedException("You dont login in system");

        if (Session.getUserInSession().getUserType() == UserType.ADMIN) {
             roomService.addRoom(room);
        } else {
            throw new AccessDeniedException("User with name " + Session.getUserInSession().getUserName() + "  have not access to this operation");

        }
    }

    public void deleteRoom(Room room) throws Exception {
        if (Session.getUserInSession() == null) throw new AccessDeniedException("You dont login in system");

        if (Session.getUserInSession().getUserType() == UserType.ADMIN) {
            roomService.deleteRoom(room.getId());
        } else {
            throw new AccessDeniedException("User with name " + Session.getUserInSession().getUserName() + "  have not access to this operation");
        }


    }

    public List<Room> findRooms(Filter filter) throws Exception {
        if (Session.getUserInSession() != null) {
            return roomService.findRoomsByFilter(filter);
        }
        throw new AccessDeniedException("Please login in system");
    }

}
