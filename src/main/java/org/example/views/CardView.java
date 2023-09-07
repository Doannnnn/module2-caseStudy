package org.example.views;

import org.example.CRUD.ITournamentsService;
import org.example.Enum.ECard;
import org.example.Enum.EGender;
import org.example.Enum.ERole;
import org.example.model.Card;
import org.example.model.Tournaments;
import org.example.model.User;
import org.example.service.CardService;
import org.example.service.PlayerService;
import org.example.service.TeamService;
import org.example.service.TournamentsService;
import org.example.utils.DateUtils;
import org.example.utils.GetValue;

import java.time.LocalDate;
import java.util.List;

public class CardView {
    private CardService cardService;
    private TournamentsService tournamentsService;
    private TeamService teamService;
    private PlayerService playerService;
    private PlayerView playerView;
    private ITournamentsService iTournamentsService;
    private long nextId;
    public CardView(){
        cardService = new CardService();
        tournamentsService = new TournamentsService();
        teamService = new TeamService();
        playerService = new PlayerService();
        playerView = new PlayerView();
        iTournamentsService = new TournamentsService();
    }
    public void menuCard() {
        boolean checkAction = false;
        do {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║              Quản lý thẻ                ║");
            System.out.println("║═════════════════════════════════════════║");
            System.out.println("║            1. Thêm thẻ.                 ║");
            System.out.println("║            2. Xóa thẻ.                  ║");
            System.out.println("║            3. Sửa thẻ.                  ║");
            System.out.println("║            4. Xem danh sách thẻ.        ║");
            System.out.println("║            0. Trở lại.                  ║");
            System.out.println("╚═════════════════════════════════════════╝");
            int actionMenu = GetValue.getInt("Nhập: ");
            switch (actionMenu) {
                case 1: {
                    createCard();
                    break;
                }
                case 2: {
                    deleteCard();
                    break;
                }
                case 3: {
                    updateCard();
                    break;
                }
                case 4: {
                    showCard();
                    break;
                }
                case 0: {
                    TournamentView tournamentView = new TournamentView();
                    tournamentView.menuTournament();
                    break;
                }
                default: {
                    menuCard();
                    break;
                }
            }
        }while (!checkAction);
    }

    private void updateCard() {
        showCard();
        Card cards = new Card();
        long id = GetValue.getIdCard("Nhập id cần update");
        long idTournaments = GetValue.getIdTournaments("Nhập id giải đấu");
        long idTeam = GetValue.getIdTeam("Nhập id đội bóng");
        long idSchedule = GetValue.getIdSchedule("Nhập id trận đấu");
        playerView.showPlayerById(idTeam);
        long idPlayer = GetValue.getIdPlayer("Nhập id cầu thủ");
        ECard card = GetValue.getCard("Chọn thẻ");
        int quantity = GetValue.getInt("Số lượng thẻ");
        cards.setId(id);
        cards.setIdTournaments(idTournaments);
        cards.setIdTeam(idTeam);
        cards.setIdSchedule(idSchedule);
        cards.setIdPlayer(idPlayer);
        cards.setCard(card);
        cards.setQuantity(quantity);
        cardService.updateCard(id, cards);
        showCard();
    }
    private void deleteCard() {
        showCard();
        long id = GetValue.getIdCard("Nhập ID cần xóa: ");
        cardService.deleteCard(id);
        showCard();
    }
    private void createCard() {
        TournamentView tournamentView = new TournamentView();
        tournamentView.showTournaments();
        long idTournaments = GetValue.getIdTournaments("Nhập id giải đấu");
        long idTeam = GetValue.getIdTeam("Nhập id đội bóng");
        long idSchedule = GetValue.getIdSchedule("Nhập id trận đấu");
        playerView.showPlayerById(idTeam);
        long idPlayer = GetValue.getIdPlayer("Nhập id cầu thủ");
        ECard card = GetValue.getCard("Chọn thẻ");
        int quantity = GetValue.getInt("Số lượng thẻ");
        Card cards = new Card(nextId, idTournaments, idTeam, idSchedule, idPlayer, card, quantity);
        List<Card> cards1 = cardService.getAllCard();
        boolean playerExists = false;
        for (Card c : cards1) {
            if (c.getIdPlayer() == idPlayer && c.getCard().equals(card)) {
                int cardQuantity = c.getQuantity();
                cardQuantity += quantity;
                c.setQuantity(cardQuantity);
                cardService.updateCard(c.getId(), c);
                playerExists = true;
                break;
            }
        }

        if (!playerExists) {
            cardService.createCard(cards);
            showCard();
        }
    }
    private void showCard() {
        TournamentView tournamentView = new TournamentView();
        tournamentView.showTournaments();
        long idTournaments = GetValue.getIdTournaments("Nhập id giải đấu");
        Tournaments tournaments = iTournamentsService.findTournaments(idTournaments);
        System.out.println("Thông tin Giải Đấu:");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n", "ID", "Name", "ADDRESS", "NUMBERSTEAM");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n",
                tournaments.getId(), tournaments.getName(), tournaments.getAddress(), tournaments.getNumbersTeam());
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.println("\nDanh sách thẻ:");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+------------+");
        System.out.printf("| %10s | %20s | %20s | %20s | %20s | %10s | %10s |\n",
                "ID", "TOURNAMENTS", "TEAM", "SCHEDULE", "NAME", "CARD", "QUANTITY");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+------------+");
        List<Card> cards = cardService.getAllCard();
        for (Card c : cards) {
            String nameTournaments = tournamentsService.getNameTournamentsByTeamId(c.getIdTournaments());
            String nameTeam = teamService.getTeamNameById(c.getIdTeam());
            String namePlayer = playerService.getNamePlayersByTeamId(c.getIdPlayer());
            System.out.printf("| %10s | %20s | %20s | %20s | %20s | %10s | %10s |\n",
                    c.getId(), nameTournaments, nameTeam, c.getIdSchedule(), namePlayer, c.getCard(), c.getQuantity());
        }
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+------------+");
    }
    public void createCardById(long idTournaments, long idSchedule) {
        TeamView teamView = new TeamView();
        teamView.showTeam();
        long idTeam = GetValue.getIdTeam("Nhập id đội bóng bị thẻ");
        playerView.showPlayerById(idTeam);
        long idPlayer = GetValue.getIdPlayer("Nhập id cầu thủ bị thẻ");
        ECard card = GetValue.getCard("Chọn thẻ");
        int quantity = GetValue.getInt("Số lượng thẻ");
        Card cards = new Card(nextId, idTournaments, idTeam, idSchedule, idPlayer, card, quantity);
        List<Card> cards1 = cardService.getAllCard();
        boolean playerExists = false;
        for (Card c : cards1) {
            if (c.getIdPlayer() == idPlayer && c.getCard().equals(card)) {
                int cardQuantity = c.getQuantity();
                cardQuantity += quantity;
                c.setQuantity(cardQuantity);
                cardService.updateCard(c.getId(), c);
                playerExists = true;
                break;
            }
        }

        if (!playerExists) {
            cardService.createCard(cards);
        }
    }
    private void showCardById(long idTournaments) {
        Tournaments tournaments = iTournamentsService.findTournaments(idTournaments);
        System.out.println("Thông tin Giải Đấu:");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n", "ID", "Name", "ADDRESS", "NUMBERSTEAM");
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s | %20s |\n",
                tournaments.getId(), tournaments.getName(), tournaments.getAddress(), tournaments.getNumbersTeam());
        System.out.println("+------------+----------------------+----------------------+----------------------+");
        System.out.println("\nDanh sách thẻ:");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+------------+");
        System.out.printf("| %10s | %20s | %20s | %20s | %20s | %10s | %10s |\n",
                "ID", "TOURNAMENTS", "TEAM", "SCHEDULE", "NAME", "CARD", "QUANTITY");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+------------+");
        List<Card> cards = cardService.getAllCard();
        for (Card c : cards) {
            String nameTournaments = tournamentsService.getNameTournamentsByTeamId(c.getIdTournaments());
            String nameTeam = teamService.getTeamNameById(c.getIdTeam());
            String namePlayer = playerService.getNamePlayersByTeamId(c.getIdPlayer());
            System.out.printf("| %10s | %20s | %20s | %20s | %20s | %10s | %10s |\n",
                    c.getId(), nameTournaments, nameTeam, c.getIdSchedule(), namePlayer, c.getCard(), c.getQuantity());
        }
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+------------+------------+");
    }
    public static void main(String[] args) {
        CardView cardView = new CardView();
        cardView.menuCard();
    }
}
