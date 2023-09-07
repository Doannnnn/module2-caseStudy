package org.example.CRUD;

import  org.example.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User findUser(long id);
    public String checkUserName(String userName);
    void updateUser(long id, User user);

    void deleteUser(long id);

    void createUser(User user);
    User searchName(String name);
    void sortName();
    void sortAge();
    void sortDate();
}

