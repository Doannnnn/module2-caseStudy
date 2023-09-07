package org.example.views;

import org.example.Enum.ENumbersTeam;
import org.example.Enum.ERound;
import org.example.model.*;
import org.example.CRUD.ITeamService;
import org.example.CRUD.ITournamentsService;
import org.example.service.*;
import org.example.utils.DateUtils;
import org.example.utils.GetValue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ScheduleView {
    private Scanner scanner = new Scanner(System.in);
    private ScheduleService scheduleService;
    private ITournamentsService iTournamentsService;
    private ITeamService iTeamService;
    private TeamView teamView;
    public ScheduleView(){
        scheduleService = new ScheduleService();
        iTournamentsService = new TournamentsService();
        iTeamService = new TeamService();
        teamView = new TeamView();
    }
    public void menuSchedule() {
        boolean checkAction = false;
        do {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║          Quản lý lịch giải đấu          ║");
            System.out.println("║═════════════════════════════════════════║");
            System.out.println("║          1. Tạo lịch đấu.               ║");
            System.out.println("║          2. Xem lịch thi đấu.           ║");
            System.out.println("║          0. Trở lại.                    ║");
            System.out.println("╚═════════════════════════════════════════╝");
            int actionMenu = GetValue.getInt("Nhập: ");
            switch (actionMenu) {
                case 1: {
                    createSchedule();
                    break;
                }
                case 2: {
                    showSchedule();
                    break;
                }
                case 0: {
                    TournamentView tournamentView = new TournamentView();
                    tournamentView.menuTournament();
                    break;
                }
                default: {
                    menuSchedule();
                    break;
                }
            }
        }while (!checkAction);
    }

    public void showSchedule() {
        TournamentView tournamentView = new TournamentView();
        tournamentView.showTournaments();
        Long idTournaments = GetValue.getIdTournaments("Nhập ID giải đấu: ");
        Tournaments tournaments = iTournamentsService.findTournaments(idTournaments);
        System.out.println("Thông tin Giải Đấu:");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n", "ID", "Name", "Address", "NumbersTeam");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n",
                tournaments.getId(), tournaments.getName(), tournaments.getAddress(), tournaments.getNumbersTeam());
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.println("Danh sách lịch thi đấu:");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s | %20s | %20s |\n",
                "ID", "TEAM1", "TEAM2", "START", "END", "ROUND");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
        List<Schedule> schedules = scheduleService.getAllScheduleByTeamId(idTournaments);
        for (Schedule s : schedules) {
            System.out.printf("| %10s | %20s | %20s | %20s | %20s | %20s |\n",
                    s.getId(), s.getTeam1(), s.getTeam2(), DateUtils.formatDateTime(s.getTimeStart()),
                    DateUtils.formatDateTime(s.getTimeEnd()), s.getRound());
        }
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
    }
    public void createSchedule() {
        TournamentView tournamentView = new TournamentView();
        tournamentView.showTournaments();
        long idTournaments = GetValue.getIdTournaments("Nhập ID giải đấu: ");
       List<Tournaments> tournaments = iTournamentsService.getAllTournaments();
        if(tournaments.stream().anyMatch(t->t.getId() == idTournaments && t.getNumbersTeam().equals(ENumbersTeam.FOUR))){
            createScheduleFour(idTournaments);
        } else if (tournaments.stream().anyMatch(t->t.getId() == idTournaments && t.getNumbersTeam().equals(ENumbersTeam.EIGHT))) {
            createScheduleEight(idTournaments);
        }
        else {
            System.out.println("giải đấu không tồn tại.");
        }
    }
    private void createScheduleEight(Long idTournaments) {
        for (int i = 1; i <= 4; i++){
            teamView.showTeam();
            System.out.println("Nhập trận " + i);
            String team1 = GetValue.getString("Nhập tên Team 1: ");
            String team2 = GetValue.getString("Nhập tên Team 2: ");
            LocalDateTime timeStart = GetValue.getDateTime("Nhập thời gian bắt đầu: (HH:mm dd-MM-yyyy)");
            LocalDateTime timeEnd = GetValue.getDateTime("Nhập thời gian kết thúc: (HH:mm dd-MM-yyyy)");
            ERound round = ERound.QUARTERFINALS;
            Schedule schedule = new Schedule();
            schedule.setIdTournaments(idTournaments);
            schedule.setTeam1(team1);
            schedule.setTeam2(team2);
            schedule.setTimeStart(timeStart);
            schedule.setTimeEnd(timeEnd);
            schedule.setRound(round);
            scheduleService.createSchedule(schedule);
            System.out.println("Tạo lịch trận " + i + " thành công.");
            showScheduleById(idTournaments);
        }
        for (int i = 5; i <= 6; i++){
            teamView.showTeam();
            System.out.println("Nhập trận " + i);
            String team1 = GetValue.getString("Nhập tên Team 1: ");
            String team2 = GetValue.getString("Nhập tên Team 2: ");
            LocalDateTime timeStart = GetValue.getDateTime("Nhập thời gian bắt đầu: (HH:mm dd-MM-yyyy)");
            LocalDateTime timeEnd = GetValue.getDateTime("Nhập thời gian kết thúc: (HH:mm dd-MM-yyyy)");
            ERound round = ERound.SEMIFINAL;
            Schedule schedule = new Schedule();
            showScheduleById(idTournaments);
            schedule.setIdTournaments(idTournaments);
            schedule.setTeam1(team1);
            schedule.setTeam2(team2);
            schedule.setTimeStart(timeStart);
            schedule.setTimeEnd(timeEnd);
            schedule.setRound(round);
            scheduleService.createSchedule(schedule);
            System.out.println("Tạo lịch trận " + i + " thành công.");
            showScheduleById(idTournaments);
        }
        for (int i = 7; i <= 7; i++){
            teamView.showTeam();
            System.out.println("Nhập trận " + i);
            String team1 = GetValue.getString("Nhập tên Team 1: ");
            String team2 = GetValue.getString("Nhập tên Team 2: ");
            LocalDateTime timeStart = GetValue.getDateTime("Nhập thời gian bắt đầu: (HH:mm dd-MM-yyyy)");
            LocalDateTime timeEnd = GetValue.getDateTime("Nhập thời gian kết thúc: (HH:mm dd-MM-yyyy)");
            ERound round = ERound.FINAL;
            showScheduleById(idTournaments);
            Schedule schedule = new Schedule();
            showScheduleById(idTournaments);
            schedule.setIdTournaments(idTournaments);
            schedule.setTeam1(team1);
            schedule.setTeam2(team2);
            schedule.setTimeStart(timeStart);
            schedule.setTimeEnd(timeEnd);
            schedule.setRound(round);
            scheduleService.createSchedule(schedule);
            System.out.println("Tạo lịch trận " + i + " thành công.");
            showScheduleById(idTournaments);
        }
        showScheduleById(idTournaments);
        System.out.println("Tạo lịch giải đấu thành công.");
    }
    private void createScheduleFour(Long idTournaments) {
        for (int i = 1; i <= 2; i++){
            teamView.showTeam();
            System.out.println("Nhập trận " + i);
            String team1 = GetValue.getString("Nhập tên Team 1: ");
            String team2 = GetValue.getString("Nhập tên Team 2: ");
            LocalDateTime timeStart = GetValue.getDateTime("Nhập thời gian bắt đầu: (HH:mm dd-MM-yyyy)");
            LocalDateTime timeEnd = GetValue.getDateTime("Nhập thời gian kết thúc: (HH:mm dd-MM-yyyy)");
            ERound round = ERound.SEMIFINAL;
            Schedule schedule = new Schedule();
            schedule.setIdTournaments(idTournaments);
            schedule.setTeam1(team1);
            schedule.setTeam2(team2);
            schedule.setTimeStart(timeStart);
            schedule.setTimeEnd(timeEnd);
            schedule.setRound(round);
            scheduleService.createSchedule(schedule);
            System.out.println("Tạo lịch trận " + i + " thành công.");
            showScheduleById(idTournaments);
        }
        for (int i = 3; i <= 3; i++){
            teamView.showTeam();
            System.out.println("Nhập trận " + i);
            String team1 = GetValue.getString("Nhập tên Team 1: ");
            String team2 = GetValue.getString("Nhập tên Team 2: ");
            LocalDateTime timeStart = GetValue.getDateTime("Nhập thời gian bắt đầu: (HH:mm dd-MM-yyyy)");
            LocalDateTime timeEnd = GetValue.getDateTime("Nhập thời gian kết thúc: (HH:mm dd-MM-yyyy)");
            ERound round = ERound.FINAL;
            showScheduleById(idTournaments);
            Schedule schedule = new Schedule();
            schedule.setIdTournaments(idTournaments);
            schedule.setTeam1(team1);
            schedule.setTeam2(team2);
            schedule.setTimeStart(timeStart);
            schedule.setTimeEnd(timeEnd);
            schedule.setRound(round);
            scheduleService.createSchedule(schedule);
            System.out.println("Tạo lịch trận " + i + " thành công.");
        }
        showScheduleById(idTournaments);
        System.out.println("Tạo lịch giải đấu thành công.");
    }
    public void showScheduleById(Long idTournaments) {
        Tournaments tournaments = iTournamentsService.findTournaments(idTournaments);
        System.out.println("Thông tin Giải Đấu:");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n", "ID", "Name", "Address", "NumbersTeam");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n",
                tournaments.getId(), tournaments.getName(), tournaments.getAddress(), tournaments.getNumbersTeam());
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.println("Danh sách lịch thi đấu:");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s | %20s | %20s |\n",
                "ID", "TEAM1", "TEAM2", "START", "END", "ROUND");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
        List<Schedule> schedules = scheduleService.getAllScheduleByTeamId(idTournaments);
        for (Schedule s : schedules) {
            System.out.printf("| %10s | %20s | %20s | %20s | %20s | %20s |\n",
                    s.getId(), s.getTeam1(), s.getTeam2(), DateUtils.formatDateTime(s.getTimeStart()),
                    DateUtils.formatDateTime(s.getTimeEnd()), s.getRound());
        }
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
    }

    public static void main(String[] args) {
        ScheduleView scheduleView = new ScheduleView();
        scheduleView.menuSchedule();
    }
}
