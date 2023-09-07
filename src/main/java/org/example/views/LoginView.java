package org.example.views;

import org.example.Enum.EGender;
import org.example.Enum.ERole;
import org.example.model.User;
import org.example.CRUD.IUserService;
import org.example.service.LoginService;
import org.example.service.UserService;
import org.example.utils.GetValue;
import org.example.utils.ValiDateUtils;

import java.time.LocalDate;
import java.util.Scanner;

public class LoginView {
    private Scanner scanner = new Scanner(System.in);
    private IUserService iUserService;
    private LoginService loginService;
    public LoginView(){
        iUserService = new UserService();
        loginService = new LoginService();
    }
    public void menu(){
        System.out.println("╔═════════════════════════════════════════╗");
        System.out.println("║                                         ║");
        System.out.println("║═════════════════════════════════════════║");
        System.out.println("║             1. Đăng Nhập.               ║");
        System.out.println("║             2. Đăng Ký.                 ║");
        System.out.println("║             0. Thoát.                   ║");
        System.out.println("╚═════════════════════════════════════════╝");
        int select = GetValue.getInt("Nhập: ");
        switch (select){
            case 1:{
                login();
            }
            case 2:{
                addUser();
                menu();
                break;
            }
            case 0:{
                System.exit(0);
            }
            default: {
                menu();
                break;
            }
        }
    }
    private void addUser(){
        User user = new User();
        String name = GetValue.getName("Nhập Họ Tên: ");
        String username = iUserService.checkUserName(GetValue.getUserName("Nhập username: "));
        String password = GetValue.getPassword("Nhập password: ");
        user.setAge(GetValue.getAge("Nhập tuổi: "));
        LocalDate dob = GetValue.getDate("Nhập ngày sinh: (dd-MM-yyyy)");
        EGender gender = GetValue.getGender("Nhập giới tính: ");
        user.setName(name);
        user.setPassword(password);
        user.setUserName(username);
        user.setDob(dob);
        user.setGender(gender);
        user.setRole(ERole.USER);
        iUserService.createUser(user);
        System.out.println("Đăng ký thành công.");
    }
    public void login(){
            boolean check= false;
            do {
                String userName = GetValue.getString("Nhập username: ");
                String password = GetValue.getString("Nhập password: ");
                if (ValiDateUtils.isUsernameValid(userName) && ValiDateUtils.isPasswordValid(password)) {
                    loginService.checkLogin(userName, password);
                } else {
                    System.out.println("Tên người dùng hoặc mật khẩu không hợp lệ. Vui lòng nhập lại.");
                }
            } while (!check);
    }
    public static void adminPage(){
        boolean check = false;
        do {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║                 ADMIN                   ║");
            System.out.println("║═════════════════════════════════════════║");
            System.out.println("║          1. Quản lý giải đấu.           ║");
            System.out.println("║          2. Quản lý đội bóng.           ║");
            System.out.println("║          3. Quản lý cầu thủ.            ║");
            System.out.println("║          4. Quản lý User.               ║");
            System.out.println("║          0. Đăng xuất.                  ║");
            System.out.println("╚═════════════════════════════════════════╝");
            int select = GetValue.getInt("Nhập: ");
            switch (select){
                case 1: {
                        TournamentView tournamentView = new TournamentView();
                        tournamentView.menuTournament();
                        break;
                }
                case 2: {
                        TeamView teamView = new TeamView();
                        teamView.menuTeam();
                        break;
                }
                case 3: {
                        PlayerView playerView = new PlayerView();
                        playerView.menuPlayer();
                        break;
                }
                case 4: {
                        UserView userView = new UserView();
                        userView.menuUser();
                        break;
                }
                case 0: {
                    LoginView loginView = new LoginView();
                    loginView.menu();
                    break;
                }
                default: {
                    adminPage();
                    break;
                }
            }
        } while (check);
    }
    public static void userPage(){
        boolean check = false;
        do {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║                  USER                   ║");
            System.out.println("║═════════════════════════════════════════║");
            System.out.println("║        1. Xem danh sách đội bóng.       ║");
            System.out.println("║        2. Xem danh sách cầu thủ.        ║");
            System.out.println("║        3. Xem danh sách giải đấu.       ║");
            System.out.println("║        4. Xem lịch thi đấu.             ║");
            System.out.println("║        5. Xem thống kê giải đấu.        ║");
            System.out.println("║        0. Đăng xuất.                    ║");
            System.out.println("╚═════════════════════════════════════════╝");
            int select = GetValue.getInt("Nhập: ");
            switch (select){
                case 1: {
                    TeamView teamView = new TeamView();
                    teamView.showTeam();
                    break;
                }
                case 2: {
                    PlayerView playerView = new PlayerView();
                    playerView.showPlayer();
                    break;
                }
                case 3: {
                    TournamentView tournamentView = new TournamentView();
                    tournamentView.showTournaments();
                    break;
                }
                case 4: {
                    ScheduleView scheduleView = new ScheduleView();
                    scheduleView.showSchedule();
                    break;
                }
                case 5: {
                    GameHistoryView gameHistoryView = new GameHistoryView();
                    gameHistoryView.diaryTournaments();
                    break;
                }
                case 0: {
                    LoginView loginView = new LoginView();
                    loginView.menu();
                    break;
                }
                default: {
                    userPage();
                    break;
                }
            }
        } while (!check);
    }

    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        loginView.menu();
    }
}
