package org.example.views;

import org.example.Enum.ENumbersTeam;
import org.example.model.*;
import org.example.CRUD.ITeamService;
import org.example.CRUD.ITournamentsService;
import org.example.service.TeamService;
import org.example.service.TournamentsService;
import org.example.utils.GetValue;

import java.util.List;
import java.util.Scanner;

public class TournamentView {
    private ITournamentsService iTournamentsService;
    private ITeamService iTeamService;
    private ScheduleView scheduleView;
    private Scanner scanner = new Scanner(System.in);
    public TournamentView(){
        iTournamentsService = new TournamentsService();
        iTeamService = new TeamService();
        scheduleView = new ScheduleView();
    }
    public void menuTournament() {
        boolean checkAction = false;
        do {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║                Quản lý giải đấu                ║");
            System.out.println("║════════════════════════════════════════════════║");
            System.out.println("║           1. Thêm giải đấu.                    ║");
            System.out.println("║           2. Quản lý lịch giải đấu.            ║");
            System.out.println("║           3. Thống kê trận đấu.                ║");
            System.out.println("║           4. Xem danh sách giải đấu.           ║");
            System.out.println("║           5. Xóa giải đấu.                     ║");
            System.out.println("║           6. Sửa thông tin giải đấu.           ║");
            System.out.println("║           7. Xem danh sách đội bóng.           ║");
            System.out.println("║           0. Trở lại.                          ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            int actionMenu = GetValue.getInt("Nhập: ");
            switch (actionMenu) {
                case 1: {
                    addTournaments();
                    break;
                }
                case 2: {
                    scheduleView.menuSchedule();
                    break;
                }
                case 3: {
                    GameHistoryView gameHistoryView = new GameHistoryView();
                    gameHistoryView.menuGameHistory();
                }
                case 4: {
                    showTournaments();
                    break;
                }
                case 5: {
                    deleteTournaments();
                    break;
                }
                case 6: {
                    updateTournaments();
                    break;
                }
                case 7: {
                    showTeam();
                    break;
                }
                case 0: {
                    LoginView loginView = new LoginView();
                    loginView.adminPage();
                    break;
                }
                default: {
                    menuTournament();
                    break;
                }
            }
        }while (!checkAction);
    }

    private void showTeam() {
        showTournaments();
        long idTournaments = GetValue.getIdTournaments("Nhập ID giải đấu: ");
        Tournaments tournaments = iTournamentsService.findTournaments(idTournaments);
        System.out.println("Thông tin Giải Đấu:");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n", "ID", "Name", "ADDRESS", "NUMBERSTEAM");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n",
                tournaments.getId(), tournaments.getName(), tournaments.getAddress(), tournaments.getNumbersTeam());
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.println("Danh sách Đội tham gia:");
        System.out.println("+------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s |\n", "ID", "Name", "HVL");
        System.out.println("+------------+----------------------+----------------------+");
        List<Team> teams = iTeamService.getAllTeamByTeamId(idTournaments);
        for (Team t : teams) {
            System.out.printf("| %10s | %20s | %20s |\n",
                    t.getId(), t.getName(), t.getCoach());
        }
        System.out.println("+------------+----------------------+----------------------+");
    }
    private void deleteTournaments() {
        showTournaments();
        long id = GetValue.getIdTournaments("Nhập ID cần xóa: ");

        iTournamentsService.deleteTournaments(id);
        showTournaments();
    }
    private void updateTournaments() {
        showTournaments();
        Tournaments tournaments = new Tournaments();
        long id = GetValue.getIdTournaments("Nhập ID cần update: ");
        String name = GetValue.getString("Nhập tên giải đấu: ");
        String address = GetValue.getString("Nhập address: ");
        ENumbersTeam numbersTeam = GetValue.getNumbersTeam("Nhập numbersTeam: ");
        tournaments.setNumbersTeam(numbersTeam);
        tournaments.setId(id);
        tournaments.setName(name);
        tournaments.setAddress(address);
        tournaments.setNumbersTeam(numbersTeam);
        iTournamentsService.updateTournaments(id, tournaments);
        System.out.println("Update thành công.");
    }
    private void addTournaments() {
        Tournaments tournaments = new Tournaments();
        tournaments.setName(GetValue.getString("Nhập tên giải đấu: "));
        tournaments.setAddress(GetValue.getString("Nhập address:"));
        ENumbersTeam numbersTeam = GetValue.getNumbersTeam("Nhập numbersTeam: ");
        tournaments.setNumbersTeam(numbersTeam);
        iTournamentsService.createTournaments(tournaments);
        System.out.println("Thêm giải đấu thành công.");
        showTournaments();
        System.out.println("Tạo lịch giải đấu");
        scheduleView.createSchedule();
    }
    public void showTournaments() {
        System.out.println("Danh Sách Giải Đấu:");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.println("|     ID     |        Name          |       ADDRESS        |    NUMBERSTEAM       |");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        List<Tournaments> tournaments = iTournamentsService.getAllTournaments();
        for (Tournaments t : tournaments) {
            System.out.printf("| %10s | %20s | %20s | %20s |\n",
                    t.getId(), t.getName(), t.getAddress(), t.getNumbersTeam());
        }
        System.out.println("+------------+----------------------+----------------------+----------------------+");
    }

    public static void main(String[] args) {
        TournamentView tournamentView = new TournamentView();
        tournamentView.menuTournament();
    }
}
