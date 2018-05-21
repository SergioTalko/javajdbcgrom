package lesson8.service;

import lesson8.controller.Session;
import lesson8.dao.UserDAO;
import lesson8.entity.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();


    public void registerUser(User user){
      userDAO.save(user);
    }

    public void login(String name, String password){
            if (name == null || password == null) throw new NullPointerException("You put null for checking");
            for (User user : userDAO.getAll()) {
                if (user.getUserName().equals(name) && user.getPassword().equals(password)) {
                    Session.login(user);
                    break;
                }
            }
    }
}
