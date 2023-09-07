package org.example.service;

import org.example.CRUD.ILoginService;
import org.example.Enum.ERole;
import org.example.model.User;

import java.util.List;

import static org.example.views.LoginView.adminPage;
import static org.example.views.LoginView.userPage;

public class LoginService implements ILoginService {
    UserService userService = new UserService();
    @Override
    public void checkLogin(String userName, String passWord) {
        List<User> users = userService.getAllUsers();
        if(users.stream().anyMatch(e->e.getUserName().equals(userName)&&e.getPassword().equals(passWord) && e.getRole().equals(ERole.ADMIN))){
            adminPage();
        } else if (users.stream().anyMatch(e->e.getUserName().equals(userName)&&e.getPassword().equals(passWord) && e.getRole().equals(ERole.USER))) {
            userPage();
        }
        else {
            System.out.println("Tai khoan hoac mat khau sai.");
        }
    }
}
