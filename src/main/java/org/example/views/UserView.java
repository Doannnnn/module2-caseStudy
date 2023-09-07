package org.example.views;

import org.example.Enum.EGender;
import org.example.Enum.ERole;
import org.example.model.User;
import org.example.CRUD.IUserService;
import org.example.service.UserService;
import org.example.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.utils.GetValue;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
public class UserView {
    private Scanner scanner = new Scanner(System.in);
    private IUserService iUserService;
    private int nextId;

    public UserView() {
        iUserService = new UserService();
    }
    public void menuUser() {
        boolean checkAction = false;
        do {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║              Quản lý user               ║");
            System.out.println("║═════════════════════════════════════════║");
            System.out.println("║          1. Xem danh sách.              ║");
            System.out.println("║          2. Thêm user.                  ║");
            System.out.println("║          3. Sửa thông tin user.         ║");
            System.out.println("║          4. Xóa user.                   ║");
            System.out.println("║          5. Tìm kiếm user.              ║");
            System.out.println("║          6. Sắp xếp.                    ║");
            System.out.println("║          0. Trở lại.                    ║");
            System.out.println("╚═════════════════════════════════════════╝");
            int actionMenu = GetValue.getInt("Nhập: ");
            switch (actionMenu) {
                case 1: {
                    showUsers();
                    break;
                }
                case 2: {
                    addUser();
                    break;
                }
                case 3: {
                    updateUser();
                    break;
                }
                case 4: {
                    deleteUser();
                    break;
                }
                case 5: {
                    searchName();
                    break;
                }
                case 6: {
                    sortUser();
                    break;
                }
                case 0: {
                    LoginView loginView = new LoginView();
                    loginView.adminPage();
                    break;
                }
                default: {
                    menuUser();
                    break;
                }
            }
        }while (!checkAction);
    }

    private void sortUser() {
        System.out.println("Sắp xếp theo");
        System.out.println("Nhập 1. Sắp xếp theo tên");
        System.out.println("Nhập 2. Sắp xếp theo tuổi");
        System.out.println("Nhập 3. Sắp xếp theo \"dd-MM-yyyy\"");
        int select = Integer.parseInt(scanner.nextLine());
        switch (select){
            case 1: {
                sortName();
                break;
            }
            case 2: {
                sortAge();
                break;
            }
            case 3: {
                sortDate();
                break;
            }
            default: {
                sortUser();
                break;
            }
        }
    }

    private void sortDate() {
        iUserService.sortDate();
    }

    private void sortAge() {
        iUserService.sortAge();
    }

    private void sortName() {
        iUserService.sortName();
    }

    private void searchName() {
        showUsers();
        String name = GetValue.getName("Nhập tên tìm kiếm: ");
        User u = iUserService.searchName(name);
        System.out.printf("| %10s | %20s | %20s| %20s | %10s | %10s | %10S |\n", "ID", "Name", "USERNAME", "DOB", "AGE", "GENDER", "ROLE");
        System.out.printf("| %10s | %20s | %20s| %20s | %10s | %10s | %10S |\n",
                u.getId(), u.getName(), u.getUserName(), DateUtils.formatDate(u.getDob()), u.getAge(), u.getGender(), u.getRole());
    }

    private void updateUser() {
        showUsers();
        long id = GetValue.getIdUser("Nhập ID cần update: ");
        String name = GetValue.getName("Nhập Họ Tên: ");
        String userName = iUserService.checkUserName(GetValue.getUserName("Nhập username: "));
        String password = GetValue.getString("Nhập password:");
        int age = GetValue.getAge("Nhập tuổi: ");
        LocalDate dob = GetValue.getDate("Nhập ngày sinh: (dd-MM-yyyy)");
        EGender gender = GetValue.getGender("Nhập giới tính: ");
        ERole role = GetValue.getRole("Nhập vai trò: ");
        User user = new User(id, name, userName, password, age, dob, gender, role);
        iUserService.updateUser(id, user);
        showUsers();
    }

    private void deleteUser() {
        showUsers();
        long id = GetValue.getIdUser("Nhập ID cần xóa: ");
        iUserService.deleteUser(id);
        showUsers();
    }

    public void addUser() {
        String name = GetValue.getName("Nhập Họ Tên: ");
        String userName = iUserService.checkUserName(GetValue.getUserName("Nhập username: "));
        String password = GetValue.getPassword("Nhập password:");
        int age = GetValue.getAge("Nhập tuổi: ");
        LocalDate dob = GetValue.getDate("Nhập ngày sinh: (dd-MM-yyyy)");
        EGender gender = GetValue.getGender("Nhập giới tính: ");
        ERole role = GetValue.getRole("Nhập vai trò: ");
        User user = new User(nextId, name, userName, password, age, dob, gender, role);
        iUserService.createUser(user);
        showUsers();
    }

    private void showUsers() {
        System.out.println("Danh Sách USER:");
        System.out.println("+------------+----------------------+----------------------+----------------------+------------+------------+------------+");
        System.out.println("|     ID     |        Name          |       USERNAME       |         DOB          |    AGE     |   GENDER   |    ROLE    |");
        System.out.println("+------------+----------------------+----------------------+----------------------+------------+------------+------------+");

        List<User> users = iUserService.getAllUsers();
        for (User u : users) {
            System.out.printf("| %10s | %20s | %20s | %20s | %10s | %10s | %10s |\n",
                    u.getId(), u.getName(), u.getUserName(), DateUtils.formatDate(u.getDob()), u.getAge(), u.getGender(), u.getRole());
        }

        System.out.println("+------------+----------------------+----------------------+----------------------+------------+------------+------------+");
    }

    public static void main(String[] args) {
        UserView userView = new UserView();
        userView.menuUser();
    }
}

