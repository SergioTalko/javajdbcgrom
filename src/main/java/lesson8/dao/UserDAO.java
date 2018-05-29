package lesson8.dao;

import lesson8.entity.User;

public class UserDAO extends GeneralDAO<User> {

    private final String FIND_BY_ID = "FROM User WHERE id = :id ";
    private final String DELETE_BY_ID = "DELETE FROM User WHERE id = :id";


    public UserDAO() {
        setTableName("User");
    }

    public User findById(long id) {
        return super.findById(id, FIND_BY_ID);
    }

    public void delete(long id) {
        super.delete(id, DELETE_BY_ID);
    }
}
