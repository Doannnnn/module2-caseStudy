package org.example.views;

import org.example.model.Team;
import org.example.CRUD.ITeamService;
import org.example.service.TeamService;
import org.example.utils.GetValue;

import java.util.List;
import java.util.Scanner;

public class TeamView {
    private Scanner scanner = new Scanner(System.in);
    private ITeamService iTeamService;
    private int nextId;

    public TeamView() {
        iTeamService = new TeamService();
    }
    public void menuTeam() {
        boolean checkAction = false;
        do {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║            Quản lý đội bóng             ║");
            System.out.println("║═════════════════════════════════════════║");
            System.out.println("║        1. Xem danh sách đội bóng.       ║");
            System.out.println("║        2. Thêm đội bóng.                ║");
            System.out.println("║        3. Sửa thông tin đội bóng.       ║");
            System.out.println("║        4. Xóa đội bóng.                 ║");
            System.out.println("║        5. Tìm kiếm đội bóng.            ║");
            System.out.println("║        6. Sắp xếp.                      ║");
            System.out.println("║        0. Trở lại.                      ║");
            System.out.println("╚═════════════════════════════════════════╝");
            int actionMenu = GetValue.getInt("Nhập: ");
            switch (actionMenu) {
                case 1: {
                    showTeam();
                    break;
                }
                case 2: {
                    addTeam();
                    break;
                }
                case 3: {
                    updateTeam();
                    break;
                }
                case 4: {
                    deleteTeam();
                    break;
                }
                case 5: {
                    searchName();
                    break;
                }
                case 6: {
                    sortName();
                    break;
                }
                case 0: {
                    LoginView loginView = new LoginView();
                    loginView.adminPage();
                    break;
                }
                default: {
                    menuTeam();
                    break;
                }
            }
        }while (!checkAction);
    }

    private void sortName() {
        iTeamService.sortName();
    }
    private void searchName() {
        showTeam();
        String name = GetValue.getString("Nhập tên tìm kiếm: ");
        Team t = iTeamService.searchName(name);
        System.out.println("Thông tin đội bóng:");
        System.out.println("+------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s |\n", "ID", "Name", "HVL");
        System.out.println("+------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s |\n",
                t.getId(), t.getName(), t.getCoach());
        System.out.println("+------------+----------------------+----------------------+");
    }
    private void deleteTeam() {
        showTeam();
        long id = GetValue.getIdTeam("Nhập ID cần xóa: ");

        iTeamService.deleteTeam(id);
        showTeam();
    }
    private void updateTeam() {
        showTeam();
        long id = GetValue.getIdTeam("Nhập ID cần update: ");
        String name = GetValue.getString("Nhập tên đội: ");
        String coach = GetValue.getName("Nhập tên HVL: ");
        long idTournaments = GetValue.getIdTournaments("Nhập ID giải đấu");
        Team team = new Team();
        team.setId(id);
        team.setName(name);
        team.setCoach(coach);
        team.setIdTournaments(idTournaments);
        iTeamService.updateTeam(id, team);
        showTeam();
    }
    private void addTeam() {
        long idTournaments = GetValue.getIdTournaments("Nhập ID giải đấu");
        String name = GetValue.getString("Nhập tên đội: ");
        String coach = GetValue.getName("Nhập tên HVL: ");
        Team team = new Team();
        team.setId(nextId);
        team.setIdTournaments(idTournaments);
        team.setName(name);
        team.setCoach(coach);
        iTeamService.createTeam(team);
        showTeam();
    }
    public void showTeam() {
        System.out.println("Danh sách đội bóng:");
        System.out.println("+------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s |\n", "ID", "Name", "HVL");
        System.out.println("+------------+----------------------+----------------------+");
        List<Team> teams = iTeamService.getAllTeam();
        for (Team t : teams) {
            System.out.printf("| %10s | %20s | %20s |\n",
                    t.getId(), t.getName(), t.getCoach());
        }
        System.out.println("+------------+----------------------+----------------------+");
    }

    public static void main(String[] args) {
        TeamView teamView = new TeamView();
        teamView.menuTeam();
    }
}

