package org.example.CRUD;

import org.example.model.User;

public interface ILoginService {
    void checkLogin(String userName, String passWord);
}
