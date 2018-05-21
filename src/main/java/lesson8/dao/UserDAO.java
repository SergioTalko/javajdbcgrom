package lesson8.dao;

import lesson8.entity.User;

public class UserDAO extends GeneralDAO<User> {


    public UserDAO() {
        setTableName("User");
    }
}
