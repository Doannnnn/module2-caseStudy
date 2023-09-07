package org.example.service;

import org.example.CRUD.IUserService;
import org.example.model.GameHistory;
import org.example.model.User;
import org.example.utils.DateUtils;
import org.example.utils.FileUtils;
import org.example.utils.GetValue;

import java.util.Comparator;
import java.util.List;

public class UserService implements IUserService {
    public final String fileUser = "./data/user.csv";
    @Override
    public List<User> getAllUsers() {
        return FileUtils.readData(fileUser, User.class);
    }
    @Override
    public User findUser(long id) {
        List<User> users = getAllUsers();
        User u = users.stream().filter(user -> user.getId() == id).findFirst().orElseThrow(null);
        return u;
    }
    public String checkUserName(String userName) {
        List<User> users = getAllUsers();
        boolean userExists = false;
        for (User existingUser : users){
            if (existingUser.getUserName().equals(userName)){
                userExists = true;
                break;
            }
        }
        if (userExists){
            System.out.println("Tên người dùng đã tồn tại.");
            return checkUserName(GetValue.getUserName("Nhập username: "));
        } else {
            return userName;
        }
    }

    @Override
    public void updateUser(long id, User user) {
        List<User> users = getAllUsers();
        for (User u : users) {
            if (u.getId() == id) {

                u.setAge(user.getAge());
                u.setName(user.getName());
                u.setDob(user.getDob());
                u.setGender(user.getGender());
                u.setPassword(user.getPassword());
                u.setRole(user.getRole());
                u.setUserName(user.getUserName());
            }
        }
        FileUtils.writeData(fileUser, users);
    }

    @Override
    public void deleteUser(long id) {
        List<User> users = getAllUsers();
        User user = null;
        for (User u : users) {
            if (u.getId() == id) {
                user = u;
            }
        }
        users.remove(user);
        FileUtils.writeData(fileUser, users);
    }

    @Override
    public void createUser(User user) {
        List<User> users = getAllUsers();
        user.setId(users.size() + 1);
        users.add(user);

        FileUtils.writeData(fileUser, users);
    }

    @Override
    public User searchName(String name) {
        List<User> users = getAllUsers();
        if (!users.stream().anyMatch(user -> user.getName().equalsIgnoreCase(name))){
            System.out.println("Không tìm thấy tên " + name);
        } else {
            System.out.println("Kết quả tìm kiếm: ");
            return users.stream().filter(user -> user.getName().equalsIgnoreCase(name)).findFirst().get();
        }
        return null;
    }

    @Override
    public void sortName() {
        List<User> users = getAllUsers();
        System.out.println("+------------+----------------------+----------------------+----------------------+------------+------------+------------+");
        System.out.println("|     ID     |        Name          |       USERNAME       |         DOB          |    AGE     |   GENDER   |    ROLE    |");
        System.out.println("+------------+----------------------+----------------------+----------------------+------------+------------+------------+");
        users.stream().sorted(Comparator.comparing(User::getName)).forEach(u -> {
            System.out.printf("| %10s | %20s | %20s | %20s | %10s | %10s | %10s |\n",
                    u.getId(), u.getName(), u.getUserName(), DateUtils.formatDate(u.getDob()), u.getAge(), u.getGender(), u.getRole());
        });
        System.out.println("+------------+----------------------+----------------------+----------------------+------------+------------+------------+");
    }

    @Override
    public void sortAge() {
        List<User> users = getAllUsers();
        System.out.println("+------------+----------------------+----------------------+----------------------+------------+------------+------------+");
        System.out.println("|     ID     |        Name          |       USERNAME       |         DOB          |    AGE     |   GENDER   |    ROLE    |");
        System.out.println("+------------+----------------------+----------------------+----------------------+------------+------------+------------+");
        users.stream().sorted(Comparator.comparingInt(User::getAge).reversed()).forEach(u -> {
            System.out.printf("| %10s | %20s | %20s | %10s | %10s | %10S |\n",
                    u.getId(), u.getName(), DateUtils.formatDate(u.getDob()), u.getAge(), u.getGender(), u.getRole());
        });
        System.out.println("+------------+----------------------+----------------------+----------------------+------------+------------+------------+");
    }

    @Override
    public void sortDate() {
        List<User> users = getAllUsers();
        System.out.println("+------------+----------------------+----------------------+----------------------+------------+------------+------------+");
        System.out.println("|     ID     |        Name          |       USERNAME       |         DOB          |    AGE     |   GENDER   |    ROLE    |");
        System.out.println("+------------+----------------------+----------------------+----------------------+------------+------------+------------+");
        users.stream().sorted(Comparator.comparing(User::getDob)).forEach(u -> {
            System.out.printf("| %10s | %20s | %20s | %10s | %10s | %10S |\n",
                    u.getId(), u.getName(), DateUtils.formatDate(u.getDob()), u.getAge(), u.getGender(), u.getRole());
        });
        System.out.println("+------------+----------------------+----------------------+----------------------+------------+------------+------------+");
    }
}

