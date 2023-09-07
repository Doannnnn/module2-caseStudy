package org.example.views;

import org.example.CRUD.ITournamentsService;
import org.example.Enum.ECard;
import org.example.model.Card;
import org.example.model.Score;
import org.example.model.Tournaments;
import org.example.service.PlayerService;
import org.example.service.ScoreService;
import org.example.service.TeamService;
import org.example.service.TournamentsService;
import org.example.utils.GetValue;

import java.util.List;

public class ScoreView {
    private ScoreService scoreService;
    private TournamentsService tournamentsService;
    private TeamService teamService;
    private PlayerService playerService;
    private PlayerView playerView;
    private ITournamentsService iTournamentsService;
    private TournamentView tournamentView;
    private TeamView teamView;
    private ScheduleView scheduleView;
    private long nextId;
    public ScoreView(){
        scoreService = new ScoreService();
        teamService = new TeamService();
        playerService = new PlayerService();
        tournamentsService = new TournamentsService();
        playerView = new PlayerView();
        iTournamentsService = new TournamentsService();
        tournamentView = new TournamentView();
        teamView = new TeamView();
        scheduleView = new ScheduleView();
    }
    public void menuScore() {
        boolean checkAction = false;
        do {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║            Quản lý bàn thắng            ║");
            System.out.println("║═════════════════════════════════════════║");
            System.out.println("║         1. Thêm bàn thắng.              ║");
            System.out.println("║         2. Xóa bàn thắng.               ║");
            System.out.println("║         3. Sửa bàn thắng.               ║");
            System.out.println("║         4. Xem danh sách bàn thắng.     ║");
            System.out.println("║         0. Trở lại.                     ║");
            System.out.println("╚═════════════════════════════════════════╝");
            int actionMenu = GetValue.getInt("Nhập: ");
            switch (actionMenu) {
                case 1: {
                    createScore();
                    break;
                }
                case 2: {
                    deleteScore();
                    break;
                }
                case 3: {
                    updateScore();
                    break;
                }
                case 4: {
                    showScore();
                    break;
                }
                case 0: {
                    TournamentView tournamentView = new TournamentView();
                    tournamentView.menuTournament();
                    break;
                }
                default: {
                    menuScore();
                    break;
                }
            }
        }while (!checkAction);
    }

    private void createScore() {
        tournamentView.showTournaments();
        long idTournaments = GetValue.getIdTournaments("Nhập id giải đấu");
        teamView.showTeam();
        long idTeam = GetValue.getIdTeam("Nhập id đội bóng");
        scheduleView.showSchedule();
        long idSchedule = GetValue.getIdSchedule("Nhập id trận đấu");
        playerView.showPlayerById(idTeam);
        long idPlayer = GetValue.getIdPlayer("Nhập id cầu thủ");
        int quantity = GetValue.getInt("Số lượng bàn thắng");
        Score score = new Score(nextId, idTournaments, idTeam, idSchedule, idPlayer, quantity);
        List<Score> scores = scoreService.getAllScore();
        boolean playerExists = false;
        for (Score sc : scores) {
            if (sc.getIdPlayer() == idPlayer) {
                int goals = sc.getQuantity();
                goals += quantity;
                sc.setQuantity(goals);
                scoreService.updateScore(sc.getId(), sc);
                playerExists = true;
                break;
            }
        }
        if (!playerExists) {
            scoreService.createScore(score);
            showScore();
        }
    }
    private void deleteScore() {
        tournamentView.showTournaments();
        long idTournaments = GetValue.getIdTournaments("Nhập id giải đấu");
        showScoreById(idTournaments);
        long id = GetValue.getIdScore("Nhập ID cần xóa: ");
        scoreService.deleteScore(id);
        showScoreById(idTournaments);
    }
    private void updateScore() {
        showScore();
        Score score = new Score();
        long id = GetValue.getIdScore("Nhập id cần update");
        tournamentView.showTournaments();
        long idTournaments = GetValue.getIdTournaments("Nhập id giải đấu");
        showScoreById(idTournaments);
        long idTeam = GetValue.getIdTeam("Nhập id đội bóng");
        scheduleView.showScheduleById(idTournaments);
        long idSchedule = GetValue.getIdSchedule("Nhập id trận đấu");
        playerView.showPlayerById(idTeam);
        long idPlayer = GetValue.getIdPlayer("Nhập id cầu thủ");
        int quantity = GetValue.getInt("Số lượng bàn thắng");
        score.setId(id);
        score.setIdTournaments(idTournaments);
        score.setIdTeam(idTeam);
        score.setIdSchedule(idSchedule);
        score.setIdPlayer(idPlayer);
        score.setQuantity(quantity);
        scoreService.updateScore(id,score);
        showScore();
    }
    private void showScore() {
        tournamentView.showTournaments();
        long idTournaments = GetValue.getIdTournaments("Nhập id giải đấu: ");
        Tournaments tournaments = iTournamentsService.findTournaments(idTournaments);
        System.out.println("Thông tin Giải Đấu:");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n", "ID", "Name", "ADDRESS", "NUMBERSTEAM");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n",
                tournaments.getId(), tournaments.getName(), tournaments.getAddress(), tournaments.getNumbersTeam());
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.println("Nhật ký giải đấu:");
        System.out.println("Danh sách bàn thắng:");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+");
        System.out.printf("| %10s | %20s | %20s | %20s | %20s | %10s |\n",
                "ID", "TOURNAMENTS", "TEAM", "SCHEDULE", "NAME", "QUANTITY");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+");
        List<Score> scores = scoreService.getAllScore();
        for (Score sc : scores) {
            String nameTournaments = tournamentsService.getNameTournamentsByTeamId(sc.getIdTournaments());
            String nameTeam = teamService.getTeamNameById(sc.getIdTeam());
            String namePlayer = playerService.getNamePlayersByTeamId(sc.getIdPlayer());
            System.out.printf("| %10s | %20s | %20s | %20s | %20s | %10s |\n",
                    sc.getId(), nameTournaments, nameTeam, sc.getIdSchedule(), namePlayer, sc.getQuantity());
        }
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+");
    }
    private void showScoreById(long idTournaments) {
        Tournaments tournaments = iTournamentsService.findTournaments(idTournaments);
        System.out.println("Thông tin Giải Đấu:");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n", "ID", "Name", "ADDRESS", "NUMBERSTEAM");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n",
                tournaments.getId(), tournaments.getName(), tournaments.getAddress(), tournaments.getNumbersTeam());
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.println("Nhật ký giải đấu:");
        System.out.println("Danh sách bàn thắng:");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+");
        System.out.printf("| %10s | %20s | %20s | %20s | %20s | %10s |\n",
                "ID", "TOURNAMENTS", "TEAM", "SCHEDULE", "NAME", "QUANTITY");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+");
        List<Score> scores = scoreService.getAllScore();
        for (Score sc : scores) {
            String nameTournaments = tournamentsService.getNameTournamentsByTeamId(sc.getIdTournaments());
            String nameTeam = teamService.getTeamNameById(sc.getIdTeam());
            String namePlayer = playerService.getNamePlayersByTeamId(sc.getIdPlayer());
            System.out.printf("| %10s | %20s | %20s | %20s | %20s | %10s |\n",
                    sc.getId(), nameTournaments, nameTeam, sc.getIdSchedule(), namePlayer, sc.getQuantity());
        }
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+");
    }
    public void createScoreById(long idTournaments, long idSchedule) {
        TeamView teamView = new TeamView();
        teamView.showTeam();
        long idTeam = GetValue.getIdTeam("Nhập id đội bóng ghi bàn");
        playerView.showPlayerById(idTeam);
        long idPlayer = GetValue.getIdPlayer("Nhập id cầu thủ ghi bàn");
        int quantity = GetValue.getInt("Số lượng bàn thắng");
        Score score = new Score(nextId, idTournaments, idTeam, idSchedule, idPlayer, quantity);
        List<Score> scores = scoreService.getAllScore();
        boolean playerExists = false;
        for (Score sc : scores) {
            if (sc.getIdPlayer() == idPlayer) {
                int goals = sc.getQuantity();
                goals += quantity;
                sc.setQuantity(goals);
                scoreService.updateScore(sc.getId(), sc);
                playerExists = true;
                break;
            }
        }
        if (!playerExists) {
            scoreService.createScore(score);
        }
    }

    public static void main(String[] args) {
        ScoreView scoreView = new ScoreView();
        scoreView.menuScore();
    }
}
