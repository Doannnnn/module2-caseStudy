package org.example.views;

import org.example.CRUD.ITournamentsService;
import org.example.Enum.ENumbersTeam;
import org.example.Enum.ERound;
import org.example.model.*;
import org.example.service.*;
import org.example.utils.GetValue;

import java.util.List;
import java.util.Scanner;

public class GameHistoryView {
    private Scanner scanner = new Scanner(System.in);
    private GameHistoryService gameHistoryService;
    private ScheduleService scheduleService;
    private TeamService teamService;
    private ScheduleView scheduleView;
    private ITournamentsService iTournamentsService;
    private ScoreView scoreView;
    private CardView cardView;
    private CardService cardService;
    private TournamentsService tournamentsService;
    private PlayerService playerService;
    private ScoreService scoreService;

    public GameHistoryView(){
        gameHistoryService = new GameHistoryService();
        scheduleService = new ScheduleService();
        teamService = new  TeamService();
        iTournamentsService = new TournamentsService();
        scheduleView = new ScheduleView();
        scoreView = new ScoreView();
        cardView = new CardView();
        cardService = new CardService();
        tournamentsService = new TournamentsService();
        playerService = new PlayerService();
        scoreService = new ScoreService();
    }
    public void menuGameHistory() {
        boolean checkAction = false;
        do {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║            Thống kê trận đấu            ║");
            System.out.println("║═════════════════════════════════════════║");
            System.out.println("║           1. Cập nhật trận đấu.         ║");
            System.out.println("║           2. Xem thống kê giải đấu.     ║");
            System.out.println("║           0. Trở lại.                   ║");
            System.out.println("╚═════════════════════════════════════════╝");
            int actionMenu = GetValue.getInt("Nhập: ");
            switch (actionMenu) {
                case 1: {
                    updateMatch();
                    break;
                }
                case 2: {
                    diaryTournaments();
                    break;
                }
                case 0: {
                    TournamentView tournamentView = new TournamentView();
                    tournamentView.menuTournament();
                    break;
                }
                default: {
                    menuGameHistory();
                    break;
                }
            }
        }while (!checkAction);
    }

    public void diaryTournaments() {
        TournamentView tournamentView = new TournamentView();
        tournamentView.showTournaments();
        long idTournaments = GetValue.getIdTournaments("Nhập ID giải đấu: ");
        Tournaments tournaments = iTournamentsService.findTournaments(idTournaments);
        System.out.println("Thông tin Giải Đấu:");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n", "ID", "Name", "ADDRESS", "NUMBERSTEAM");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n",
                tournaments.getId(), tournaments.getName(), tournaments.getAddress(), tournaments.getNumbersTeam());
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.println("\nNhật ký giải đấu:");
        System.out.println("+------------+----------------------+----------+----------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %8s | %8s | %20s | %20s |\n", "ID", "Team 1", "Score 1", "Score 2", "Team 2", "Round");
        System.out.println("+------------+----------------------+----------+----------+----------------------+----------------------+");
        List<GameHistory> gameHistories = gameHistoryService.getAllGameHistoryByTournamentsId(idTournaments);
        for (GameHistory gh : gameHistories) {
            String team1 = gameHistoryService.getNameTeam1ByTeamId(gh.getIdSchedule());
            String team2 = gameHistoryService.getNameTeam2ByTeamId(gh.getIdSchedule());
            System.out.printf("| %10s | %20s | %8s | %8s | %20s | %20s |\n",
                    gh.getId(), team1, gh.getScoreTeam1(), gh.getScoreTeam2(), team2, gh.getRound());
        }
        System.out.println("+------------+----------------------+----------+----------+----------------------+----------------------+");
        System.out.println("\nDanh sách thẻ:");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+------------+");
        System.out.printf("| %10s | %20s | %20s | %20s | %20s | %10s | %10s |\n",
                "ID", "TOURNAMENTS", "TEAM", "SCHEDULE", "NAME", "CARD", "QUANTITY");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+------------+");
        List<Card> cards = cardService.getAllCardByTournamentsId(idTournaments);
        for (Card c : cards) {
            String nameTournaments = tournamentsService.getNameTournamentsByTeamId(c.getIdTournaments());
            String nameTeam = teamService.getTeamNameById(c.getIdTeam());
            String namePlayer = playerService.getNamePlayersByTeamId(c.getIdPlayer());
            System.out.printf("| %10s | %20s | %20s | %20s | %20s | %10s | %10s |\n",
                    c.getId(), nameTournaments, nameTeam, c.getIdSchedule(), namePlayer, c.getCard(), c.getQuantity());
        }
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+------------+");
        System.out.println("\nDanh sách bàn thắng:");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+");
        System.out.printf("| %10s | %20s | %20s | %20s | %20s | %10s |\n",
                "ID", "TOURNAMENTS", "TEAM", "SCHEDULE", "NAME", "QUANTITY");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+");
        List<Score> scores = scoreService.getAllScoreByTournamentsId(idTournaments);
        int maxGoals = 0;
        String topScore = "";
        for (Score sc : scores) {
            String nameTournaments = tournamentsService.getNameTournamentsByTeamId(idTournaments);
            String nameTeam = teamService.getTeamNameById(sc.getIdTeam());
            String namePlayer = playerService.getNamePlayersByTeamId(sc.getIdPlayer());
            System.out.printf("| %10s | %20s | %20s | %20s | %20s | %10s |\n",
                    sc.getId(), nameTournaments, nameTeam, sc.getIdSchedule(), namePlayer, sc.getQuantity());
            if (sc.getQuantity() > maxGoals) {
                maxGoals = sc.getQuantity();
                topScore = playerService.getNamePlayersByTeamId(sc.getIdPlayer());
            }
        }
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+");
        System.out.println("\nVua phá lưới: " + topScore);
    }
    private void diaryTournamentsById(long idTournaments) {
        Tournaments tournaments = iTournamentsService.findTournaments(idTournaments);
        System.out.println("Thông tin Giải Đấu:");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n", "ID", "Name", "ADDRESS", "NUMBERSTEAM");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n",
                tournaments.getId(), tournaments.getName(), tournaments.getAddress(), tournaments.getNumbersTeam());
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.println("\nNhật ký giải đấu:");
        System.out.println("+------------+----------------------+----------+----------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %8s | %8s | %20s | %20s |\n", "ID", "Team 1", "Score 1", "Score 2", "Team 2", "Round");
        System.out.println("+------------+----------------------+----------+----------+----------------------+----------------------+");
        List<GameHistory> gameHistories = gameHistoryService.getAllGameHistoryByTournamentsId(idTournaments);
        for (GameHistory gh : gameHistories) {
            String team1 = gameHistoryService.getNameTeam1ByTeamId(gh.getIdSchedule());
            String team2 = gameHistoryService.getNameTeam2ByTeamId(gh.getIdSchedule());
            System.out.printf("| %10s | %20s | %8s | %8s | %20s | %20s |\n",
                    gh.getId(), team1, gh.getScoreTeam1(), gh.getScoreTeam2(), team2, gh.getRound());
        }
        System.out.println("+------------+----------------------+----------+----------+----------------------+----------------------+");
        System.out.println("\nDanh sách thẻ:");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+----------+");
        System.out.printf("| %10s | %20s | %20s | %20s | %20s | %10s | %8s |\n",
                "ID", "Tournaments", "Team", "Schedule", "Player", "Card", "Quantity");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+----------+");
        List<Card> cards = cardService.getAllCardByTournamentsId(idTournaments);
        for (Card c : cards) {
            String nameTournaments = tournamentsService.getNameTournamentsByTeamId(idTournaments);
            String nameTeam = teamService.getTeamNameById(c.getIdTeam());
            String namePlayer = playerService.getNamePlayersByTeamId(c.getIdPlayer());
            System.out.printf("| %10s | %20s | %20s | %20s | %20s | %10s | %8s |\n",
                    c.getId(), nameTournaments, nameTeam, c.getIdSchedule(), namePlayer, c.getCard(), c.getQuantity());
        }
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+------------+");
        System.out.println("\nDanh sách bàn thắng:");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+----------+");
        System.out.printf("| %10s | %20s | %20s | %20s | %20s | %8s |\n",
                "ID", "Tournaments", "Team", "Schedule", "Player", "Quantity");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+----------+");
        List<Score> scores = scoreService.getAllScoreByTournamentsId(idTournaments);
        int maxGoals = 0;
        String topScore = "";
        for (Score sc : scores) {
            String nameTournaments = tournamentsService.getNameTournamentsByTeamId(idTournaments);
            String nameTeam = teamService.getTeamNameById(sc.getIdTeam());
            String namePlayer = playerService.getNamePlayersByTeamId(sc.getIdPlayer());
            System.out.printf("| %10s | %20s | %20s | %20s | %20s | %8s |\n",
                    sc.getId(), nameTournaments, nameTeam, sc.getIdSchedule(), namePlayer, sc.getQuantity());
            if (sc.getQuantity() > maxGoals) {
                maxGoals = sc.getQuantity();
                topScore = playerService.getNamePlayersByTeamId(sc.getIdPlayer());
            }
        }
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+");
        System.out.println("\nVua phá lưới: " + topScore);
    }
    private void updateMatch() {
        TournamentView tournamentView = new TournamentView();
        tournamentView.showTournaments();
        long idTournaments = GetValue.getIdTournaments("Nhập ID giải đấu: ");
        List<Tournaments> tournaments = iTournamentsService.getAllTournaments();
        if(tournaments.stream().anyMatch(t->t.getId() == idTournaments && t.getNumbersTeam().equals(ENumbersTeam.FOUR))){
            updateTournamentsFour(idTournaments);
        } else if (tournaments.stream().anyMatch(t->t.getId() == idTournaments && t.getNumbersTeam().equals(ENumbersTeam.EIGHT))) {
            updateTournamentsEight(idTournaments);
        }
        else {
            System.out.println("giải đấu không tồn tại.");
        }
    }
    private void updateTournamentsEight(long idTournaments) {
        int numberOfMatches = 7;
        String winningMatch1 = null;
        String winningMatch2 = null;
        String winningMatch3 = null;
        String winningMatch4 = null;
        String winningMatch5 = null;
        String winningMatch6 = null;
        String winningMatch7 = null;
        for (int i = 1; i <= numberOfMatches ; i++){
            scheduleView.showScheduleById(idTournaments);
            Long idSchedule = GetValue.getIdSchedule("Nhập ID Trận đấu " + i);
            int scoreTeam1 = GetValue.getInt("Nhập bàn thắng Team1");
            for (int j = 0; j < scoreTeam1; j++) {
                scoreView.createScoreById(idTournaments, idSchedule);
            }
            scheduleView.showScheduleById(idTournaments);
            int scoreTeam2 = GetValue.getInt("Nhập bàn thắng Team2");
            for (int j = 0; j < scoreTeam2; j++) {
                scoreView.createScoreById(idTournaments, idSchedule);
            }
            int numbersCard = GetValue.getInt("Nhập số thẻ");
            for (int k = 0; k < numbersCard; k++) {
                cardView.createCardById(idTournaments, idSchedule);
            }
            ERound round = GetValue.getRound("Chọn vòng đấu");
            String winningTeam  = checkTeamWin(idSchedule, scoreTeam1, scoreTeam2);
            GameHistory gameHistory = new GameHistory();
            gameHistory.setIdTournaments(idTournaments);
            gameHistory.setIdSchedule(idSchedule);
            gameHistory.setScoreTeam1(scoreTeam1);
            gameHistory.setScoreTeam2(scoreTeam2);
            gameHistory.setRound(round);
            gameHistoryService.createGameHistory(gameHistory);
            if (i == 1) {
                winningMatch1 = winningTeam;
            } else if (i == 2) {
                winningMatch2 = winningTeam;
                long id = GetValue.getIdSchedule("Nhập ID trận bán kết 1");
                scheduleService.updateSchedule(id, winningMatch1, winningMatch2);
                scheduleView.showScheduleById(idTournaments);
            } else if (i == 3) {
                winningMatch3 = winningTeam;
            } else if (i == 4){
                winningMatch4 = winningTeam;
                long id = GetValue.getIdSchedule("Nhập ID trận bán kết 2");
                scheduleService.updateSchedule(id, winningMatch3, winningMatch4);
                scheduleView.showScheduleById(idTournaments);
            } else if (i == 5){
                winningMatch5 = winningTeam;
            } else if (i == 6){
                winningMatch6= winningTeam;
                long id = GetValue.getIdSchedule("Nhập ID trận chung kết");
                scheduleService.updateSchedule(id, winningMatch5, winningMatch6);
            } else if (i == 7){
                winningMatch7 = winningTeam;
                diaryTournamentsById(idTournaments);
                System.out.println("Đội vô địch: " + winningMatch7);
                System.out.println("Kết thúc giải đấu.");
            }
        }
    }
    private void updateTournamentsFour(long idTournaments){
        int numberOfMatches = 3;
        String winningMatch1 = null;
        String winningMatch2 = null;
        String winningMatch3 = null;
        for (int i = 1; i <= numberOfMatches ; i++){
            scheduleView.showScheduleById(idTournaments);
            Long idSchedule = GetValue.getIdSchedule("Nhập ID Trận đấu " + i);
            int scoreTeam1 = GetValue.getInt("Nhập bàn thắng Team1");
            for (int j = 0; j < scoreTeam1; j++) {
                scoreView.createScoreById(idTournaments, idSchedule);
            }
            scheduleView.showScheduleById(idTournaments);
            int scoreTeam2 = GetValue.getInt("Nhập bàn thắng Team2");
            for (int j = 0; j < scoreTeam2; j++) {
                scoreView.createScoreById(idTournaments, idSchedule);
            }
            int numbersCard = GetValue.getInt("Nhập số thẻ");
            for (int k = 0; k < numbersCard; k++) {
                cardView.createCardById(idTournaments, idSchedule);
            }
            ERound round = GetValue.getRound("Chọn vòng đấu");
            String winningTeam  = checkTeamWin(idSchedule, scoreTeam1, scoreTeam2);
            GameHistory gameHistory = new GameHistory();
            gameHistory.setIdTournaments(idTournaments);
            gameHistory.setIdSchedule(idSchedule);
            gameHistory.setScoreTeam1(scoreTeam1);
            gameHistory.setScoreTeam2(scoreTeam2);
            gameHistory.setRound(round);
            gameHistoryService.createGameHistory(gameHistory);
            if (i == 1) {
                winningMatch1 = winningTeam;
            } else if (i == 2) {
                winningMatch2 = winningTeam;
                scheduleView.showScheduleById(idTournaments);
                long id = GetValue.getIdSchedule("Nhập ID trận chung kết");
                scheduleService.updateSchedule(id, winningMatch1, winningMatch2);
            } else if (i == 3){
                winningMatch3 = winningTeam;
                diaryTournamentsById(idTournaments);
                System.out.println("Đội vô địch: " + winningMatch3);
                System.out.println("Kết thúc giải đấu.");
            }
        }
    }
    private String checkTeamWin(long idSchedule,int scoreTeam1, int scoreTeam2){
        if (scoreTeam1 > scoreTeam2) {
            String team1 = gameHistoryService.getNameTeam1ByTeamId(idSchedule);
            return team1;
        } else if (scoreTeam1 < scoreTeam2) {
            String team2 = gameHistoryService.getNameTeam2ByTeamId(idSchedule);
            return team2;
        } else {
            int random = Math.random() < 0.5 ? 1 : 2;
            if (random == 1) {
                String team1 = gameHistoryService.getNameTeam1ByTeamId(idSchedule);
                return team1;
            } else {
                String team2 = gameHistoryService.getNameTeam2ByTeamId(idSchedule);
                return team2;
            }
        }
    }

    public static void main(String[] args) {
        GameHistoryView gameHistory = new GameHistoryView();
        gameHistory.menuGameHistory();
    }
}
