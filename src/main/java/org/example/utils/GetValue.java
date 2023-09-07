package org.example.utils;

import org.example.Enum.*;
import org.example.model.*;
import org.example.service.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class GetValue {
    static Scanner input = new Scanner(System.in);
    public static String getString(String str) {
        try {
            System.out.println(str);
            return input.nextLine();
        } catch (Exception e){
            System.out.println("Đã xảy ra lỗi khi đọc chuỗi. Vui lòng thử lại.");
            return null;
        }
    }
    public static int getAge(String str) {
        while (true){
            try {
                System.out.println(str);
                int age = Integer.parseInt(input.nextLine());
                if (age < 0 || age > 100) {
                    throw new Exception(" Tuổi nằm trong khoảng 1 - 99");
                }
                return age;
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
    public static int getInt(String str) {
        while (true){
            try {
                System.out.println(str);
                int temp = Integer.parseInt(input.nextLine());
                if (temp < 0 || temp > 1000) {
                    throw new Exception(" số nằm trong khoảng từ 0 - 1000");
                }
                return temp;
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
    public static long getLong(String str) {
        while (true){
            try {
                System.out.println(str);
                long temp = Long.parseLong(input.nextLine());
                if (temp < 0 || temp > 1000) {
                    throw new Exception(" số nằm trong khoảng từ 0 - 1000");
                }
                return temp;
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
    public static ERound getRound(String str){
        while (true){
            try {
                System.out.println(str);
                for (ERound eRound : ERound.values()) {
                    System.out.println(eRound.getName() + " : " + eRound.getId());
                }
                int idRound = Integer.parseInt(input.nextLine());
                ERound selectedRound = ERound.findById(idRound);
                if (selectedRound == null) {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    continue;
                }
                return selectedRound;
            } catch (NumberFormatException e){
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
    public static ECard getCard(String str){
        while (true){
            try {
                System.out.println(str);
                for (ECard eCard : ECard.values()) {
                    System.out.println(eCard.getName() + " : " + eCard.getId());
                }
                int idCard = Integer.parseInt(input.nextLine());
                ECard selectedCard = ECard.findById(idCard);
                if (selectedCard == null) {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    continue;
                }
                return selectedCard;
            } catch (NumberFormatException e){
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
    public static EGender getGender(String str){
        while (true){
            try {
                System.out.println(str);
                for (EGender eGender : EGender.values()) {
                    System.out.println(eGender.getName() + " : " + eGender.getId());
                }
                int idGender = Integer.parseInt(input.nextLine());
                EGender selectedGender = EGender.findById(idGender);
                if (selectedGender == null) {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    continue;
                }
                return selectedGender;
            } catch (NumberFormatException e){
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
    public static ERole getRole(String str){
        while (true){
            try {
                System.out.println(str);
                for (ERole eRole : ERole.values()) {
                    System.out.println(eRole.getName() + " : " + eRole.getId());
                }
                int idRole = Integer.parseInt(input.nextLine());
                ERole selectedRole = ERole.findById(idRole);
                if (selectedRole == null) {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    continue;
                }
                return selectedRole;
            } catch (NumberFormatException e){
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
    public static ENumbersTeam getNumbersTeam(String str){
        while (true) {
            try {
                System.out.println(str);
                for (ENumbersTeam eNumbersTeam : ENumbersTeam.values()) {
                    System.out.println(eNumbersTeam.getName() + " : " + eNumbersTeam.getId());
                }
                int idRole = Integer.parseInt(input.nextLine());
                ENumbersTeam selectedTeam = ENumbersTeam.findById(idRole);
                if (selectedTeam == null) {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    continue;
                }
                return selectedTeam;
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    public static String getName(String str){
        String name;
        do {
            System.out.println(str);
            name = input.nextLine();
            if (!ValiDateUtils.isNameValid(name)) {
                System.out.println("Tên không hợp lệ. Vui lòng chỉ sử dụng chữ cái, dấu cách, dấu ngoặc đơn, dấu phẩy, dấu chấm và dấu gạch ngang.");            }
        } while (!ValiDateUtils.isNameValid(name));
        return name;
    }
    public static String getUserName(String str){
        String username;
        do {
            System.out.println(str);
            username = input.nextLine();
            if (!ValiDateUtils.isUsernameValid(username)) {
                System.out.println("Tên đăng nhập không hợp lệ. Tên đăng nhập phải bắt đầu bằng một chữ cái, " +
                        "sau đó là 7-19 ký tự chữ cái, chữ số hoặc gạch dưới.");
            }
        } while (!ValiDateUtils.isUsernameValid(username));
        return username;
    }
    public static String getPassword(String str){
        String password;
        do {
            System.out.println(str);
            password = input.nextLine();
            if (!ValiDateUtils.isPasswordValid(password)) {
                System.out.println("Mật khẩu không hợp lệ. Mật khẩu phải chứa ít nhất 8 ký tự, " +
                        "bao gồm ít nhất một chữ cái, một chữ số và một ký tự đặc biệt trong [@$!%*#?&]");
            }
        } while (!ValiDateUtils.isPasswordValid(password));
        return password;
    }
    public static LocalDateTime getDateTime(String str){
        while (true) {
            try {
                System.out.println(str);
                String inputDateTime = input.nextLine();
                if (!inputDateTime.matches("\\d{2}:\\d{2} \\d{2}-\\d{2}-\\d{4}")) {
                    System.out.println("Ngày tháng không hợp lệ. Vui lòng nhập theo định dạng (HH:mm dd-MM-yyyy).");
                    continue;
                }
                LocalDateTime dateTime = DateUtils.parseDateTime(inputDateTime);
                LocalDateTime currentDateTime = LocalDateTime.now();
                if (dateTime.isBefore(currentDateTime)) {
                    System.out.println("Ngày giờ phải lớn hơn hiện tại. Vui lòng nhập lại.");
                    continue;
                }
                return dateTime;
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public static LocalDate getDate(String str){
        while (true) {
            try {
                System.out.println(str);
                String inputDate = input.nextLine();
                if (!inputDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
                    System.out.println("Ngày tháng không hợp lệ. Vui lòng nhập theo định dạng (dd-MM-yyyy).");
                    continue;
                }
                LocalDate date = DateUtils.parseDate(inputDate);
                return date;
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public static long getIdTournaments(String str) {
        while (true) {
            try {
                System.out.println(str);
                long temp = Long.parseLong(input.nextLine());
                TournamentsService tournamentsService = new TournamentsService();
                List<Tournaments> validTournamentIds = tournamentsService.getAllTournaments();
                if (temp < 0 || temp > 1000 || !isValidTournamentId(temp, validTournamentIds)) {
                    throw new Exception("ID giải đấu không hợp lệ. Vui lòng nhập lại.");
                }
                return temp;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: ID giải đấu không hợp lệ. Vui lòng nhập lại.");
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
    private static boolean isValidTournamentId(long id, List<Tournaments> validTournamentIds) {
        for (Tournaments tournament : validTournamentIds) {
            if (tournament.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public static long getIdTeam(String str) {
        while (true) {
            try {
                System.out.println(str);
                long temp = Long.parseLong(input.nextLine());
                TeamService teamService = new TeamService();
                List<Team> validTeamIds = teamService.getAllTeam();
                if (temp < 0 || !isValidTeamId(temp, validTeamIds)) {
                    throw new Exception("ID đội bóng không hợp lệ. Vui lòng nhập lại.");
                }
                return temp;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: ID đội bóng không hợp lệ. Vui lòng nhập lại.");
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
    private static boolean isValidTeamId(long id, List<Team> validTeamIds) {
        for (Team team : validTeamIds) {
            if (team.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public static long getIdSchedule(String str) {
        while (true) {
            try {
                System.out.println(str);
                long temp = Long.parseLong(input.nextLine());
                ScheduleService scheduleService = new ScheduleService();
                List<Schedule> validScheduleIds = scheduleService.getAllSchedule();
                if (temp < 0 || !isValidScheduleId(temp, validScheduleIds)) {
                    throw new Exception("ID lịch trình không hợp lệ. Vui lòng nhập lại.");
                }
                return temp;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: ID lịch trình không hợp lệ. Vui lòng nhập lại.");
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
    private static boolean isValidScheduleId(long id, List<Schedule> validScheduleIds) {
        for (Schedule schedule : validScheduleIds) {
            if (schedule.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public static long getIdPlayer(String str) {
        while (true) {
            try {
                System.out.println(str);
                long temp = Long.parseLong(input.nextLine());
                PlayerService playerService = new PlayerService();
                List<Player> validPlayerIds = playerService.getAllPlayer();
                if (temp < 0 || !isValidPlayerId(temp, validPlayerIds)) {
                    throw new Exception("ID người chơi không hợp lệ. Vui lòng nhập lại.");
                }
                return temp;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: ID người chơi không hợp lệ. Vui lòng nhập lại.");
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
    private static boolean isValidPlayerId(long id, List<Player> validPlayerIds) {
        for (Player player : validPlayerIds) {
            if (player.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public static long getIdUser(String str) {
        while (true) {
            try {
                System.out.println(str);
                long temp = Long.parseLong(input.nextLine());
                UserService userService = new UserService();
                List<User> validUserIds = userService.getAllUsers();
                if (temp < 0 || !isValidUserId(temp, validUserIds)) {
                    throw new Exception("ID người dùng không hợp lệ. Vui lòng nhập lại.");
                }
                return temp;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: ID người dùng không hợp lệ. Vui lòng nhập lại.");
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
    private static boolean isValidUserId(long id, List<User> validUserIds) {
        for (User user : validUserIds) {
            if (user.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public static long getIdScore(String str) {
        while (true) {
            try {
                System.out.println(str);
                long temp = Long.parseLong(input.nextLine());
                ScoreService scoreService = new ScoreService();
                List<Score> validScoreIds = scoreService.getAllScore();
                if (temp < 0 || !isValidScoreId(temp, validScoreIds)) {
                    throw new Exception("ID điểm số không hợp lệ. Vui lòng nhập lại.");
                }
                return temp;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: ID điểm số không hợp lệ. Vui lòng nhập lại.");
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
    private static boolean isValidScoreId(long id, List<Score> validScoreIds) {
        for (Score score : validScoreIds) {
            if (score.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public static long getIdCard(String str) {
        while (true) {
            try {
                System.out.println(str);
                long temp = Long.parseLong(input.nextLine());
                CardService cardService = new CardService();
                List<Card> validCardIds = cardService.getAllCard();
                if (temp < 0 || !isValidCardId(temp, validCardIds)) {
                    throw new Exception("ID thẻ không hợp lệ. Vui lòng nhập lại.");
                }
                return temp;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: ID thẻ không hợp lệ. Vui lòng nhập lại.");
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
    private static boolean isValidCardId(long id, List<Card> validCardIds) {
        for (Card card : validCardIds) {
            if (card.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public static long getIdGameHistory(String str) {
        while (true) {
            try {
                System.out.println(str);
                long temp = Long.parseLong(input.nextLine());
                GameHistoryService gameHistoryService = new GameHistoryService();
                List<GameHistory> validGameHistoryIds = gameHistoryService.getAllGameHistory();
                if (temp < 0 || !isValidGameHistoryId(temp, validGameHistoryIds)) {
                    throw new Exception("ID lịch sử trò chơi không hợp lệ. Vui lòng nhập lại.");
                }
                return temp;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: ID lịch sử trò chơi không hợp lệ. Vui lòng nhập lại.");
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
    private static boolean isValidGameHistoryId(long id, List<GameHistory> validGameHistoryIds) {
        for (GameHistory gameHistory : validGameHistoryIds) {
            if (gameHistory.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
