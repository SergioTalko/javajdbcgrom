package lesson8.controller;

import lesson8.entity.User;
import lesson8.service.UserService;

public class UserController {
    private UserService userService = new UserService();




    public void registerUser(User user) {
        userService.registerUser(user);

    }

    public void login(String name, String password) {
     userService.login(name, password);
    }

    public void logout() {
       Session.logout();
    }
}
