package org.example.views;

import org.example.model.Player;
import org.example.model.Team;
import org.example.CRUD.IPlayerService;
import org.example.CRUD.ITeamService;
import org.example.service.PlayerService;
import org.example.service.TeamService;
import org.example.utils.DateUtils;
import org.example.utils.GetValue;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PlayerView {
    private Scanner scanner = new Scanner(System.in);
    private IPlayerService iPlayerService;
    private ITeamService iTeamService;
    private int nextId;

    public PlayerView() {
        iPlayerService = new PlayerService();
        iTeamService = new TeamService();
    }
    public void menuPlayer() {
        boolean checkAction = false;
        do {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║            Quản lý cầu thủ              ║");
            System.out.println("║═════════════════════════════════════════║");
            System.out.println("║        1. Xem danh sách cầu thủ.        ║");
            System.out.println("║        2. Thêm cầu thủ.                 ║");
            System.out.println("║        3. Sửa thông tin cầu thủ.        ║");
            System.out.println("║        4. Xóa cầu thủ.                  ║");
            System.out.println("║        5. Tìm kiếm cầu thủ.             ║");
            System.out.println("║        0. Trở lại.                      ║");
            System.out.println("╚═════════════════════════════════════════╝");
            int actionMenu = GetValue.getInt("Nhập: ");
            switch (actionMenu) {
                case 1: {
                    showPlayer();
                    break;
                }
                case 2: {
                    addPlayer();
                    break;
                }
                case 3: {
                    updatePlayer();
                    break;
                }
                case 4: {
                    deletePlayer();
                    break;
                }
                case 5: {
                    searchName();
                    break;
                }
                case 0: {
                    LoginView loginView = new LoginView();
                    loginView.adminPage();
                    break;
                }
                default: {
                    menuPlayer();
                    break;
                }
            }
        }while (!checkAction);
    }
    private void searchName() {
        Player p = iPlayerService.searchName(GetValue.getName("Nhập tên tìm kiếm: "));
        System.out.printf("| %10s | %20s | %10s | %10S | \n", "ID", "Name", "AGE", "POSITION");
        System.out.printf("| %10s | %20s | %10s | %10S | \n",
                p.getId(), p.getName(), p.getAge(), p.getPosition());
    }
    private void deletePlayer() {
        long id = GetValue.getIdPlayer("Nhập ID cần xóa: ");

        iPlayerService.deletePlayer(id);
        System.out.println("Xóa thành công.");
    }
    private void updatePlayer() {
        Player player = new Player();
        long id = GetValue.getIdPlayer("Nhập ID cần update: ");
        String name = GetValue.getName("Nhập tên cầu thủ: ");
        int age = GetValue.getAge("Nhập tuổi: ");
        String position = GetValue.getString("Nhập vị trí thi đấu:");
        player.setId(id);
        player.setName(name);
        player.setAge(age);
        player.setPosition(position);
        iPlayerService.updatePlayer(id, player);
        System.out.println("Update thành công.");
    }
    private void addPlayer() {
        Player player = new Player();
        player.setName(GetValue.getName("Nhập tên cầu thủ: "));
        player.setPosition(GetValue.getString("Nhập vị trí thi đấu:"));
        player.setAge(GetValue.getAge("Nhập tuổi: "));
        player.setDob(GetValue.getDate("Nhập ngày sinh: (dd-MM-yyyy)"));
        player.setIdTeam(GetValue.getIdTeam("Nhập ID đội bóng: "));
        iPlayerService.createPlayer(player);
        System.out.println("Thêm cầu thủ thành công.");
    }

    public void showPlayer() {
        TeamView teamView = new TeamView();
        teamView.showTeam();
        Long idTeam = GetValue.getIdTeam("Nhập ID đội bóng: ");
        Team team = iTeamService.findTeam(idTeam);
        System.out.println("Thông tin Đội:");
        System.out.println("+------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s |\n", "ID", "Name", "HVL");
        System.out.println("+------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s |\n",
                team.getId(), team.getName(), team.getCoach());
        System.out.println("+------------+----------------------+----------------------+");
        System.out.println("Danh sách cầu thủ:");
        System.out.println("+------------+----------------------+----------------------+----------+------------+");
        System.out.printf("| %10s | %20s | %20s | %8s | %10s |\n",
                "ID", "Name", "DOB", "Age", "Position");
        System.out.println("+------------+----------------------+----------------------+----------+------------+");

        List<Player> players = iPlayerService.getAllPlayersByTeamId(idTeam);
        for (Player p : players) {
            System.out.printf("| %10s | %20s | %20s | %8s | %10s |\n",
                    p.getId(), p.getName(), p.getDob(), p.getAge(), p.getPosition());
        }
        System.out.println("+------------+----------------------+----------------------+----------+------------+");
    }
    public void showPlayerById(long idTeam) {
        Team team = iTeamService.findTeam(idTeam);
        System.out.println("Thông tin Đội:");
        System.out.println("+------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s |\n", "ID", "Name", "HVL");
        System.out.println("+------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s |\n",
                team.getId(), team.getName(), team.getCoach());
        System.out.println("+------------+----------------------+----------------------+");
        System.out.println("Danh sách cầu thủ:");
        System.out.println("+------------+----------------------+----------------------+----------+------------+");
        System.out.printf("| %10s | %20s | %20s | %8s | %10s |\n",
                "ID", "Name", "DOB", "Age", "Position");
        System.out.println("+------------+----------------------+----------------------+----------+------------+");
        List<Player> players = iPlayerService.getAllPlayersByTeamId(idTeam);
        for (Player p : players) {
            System.out.printf("| %10s | %20s | %20s | %8s | %10s |\n",
                    p.getId(), p.getName(), p.getDob(), p.getAge(), p.getPosition());
        }
        System.out.println("+------------+----------------------+----------------------+----------+------------+");
    }

    public static void main(String[] args) {
        PlayerView playerView = new PlayerView();
        playerView.menuPlayer();
        playerView.showPlayer();
    }
}


