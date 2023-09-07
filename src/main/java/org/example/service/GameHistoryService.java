package org.example.service;

import org.example.model.GameHistory;
import org.example.model.Player;
import org.example.model.Schedule;
import org.example.model.Team;
import org.example.utils.FileUtils;

import java.util.List;
import java.util.stream.Collectors;

public class GameHistoryService {
    private final String fileGameHistory = "./data/history.csv";
    private ScheduleService scheduleService;
    public GameHistoryService(){
        scheduleService = new ScheduleService();
    }
    public List<GameHistory> getAllGameHistory() {
        return FileUtils.readData(fileGameHistory, GameHistory.class);
    }
    public void createGameHistory(GameHistory gameHistory) {
        List<GameHistory> gameHistories = getAllGameHistory();
        gameHistory.setId(gameHistories.size() + 1);
        gameHistories.add(gameHistory);

        FileUtils.writeData(fileGameHistory, gameHistories);
    }
    public List<GameHistory> getAllGameHistoryByTournamentsId(long idTournaments) {
        List<GameHistory> gameHistories = getAllGameHistory();
        List<GameHistory> results = gameHistories.stream()
                .filter(gameHistory -> gameHistory.getIdTournaments() == idTournaments)
                .collect(Collectors.toList());
        return results;
    }
    public String getNameTeam1ByTeamId(long idSchedule) {
        List<Schedule> schedules = scheduleService.getAllSchedule();
        for (Schedule s : schedules){
            if (s.getId() == idSchedule){
                return s.getTeam1();
            }
        }
        return null;
    }
    public String getNameTeam2ByTeamId(long idSchedule) {
        List<Schedule> schedules = scheduleService.getAllSchedule();
        for (Schedule s : schedules){
            if (s.getId() == idSchedule){
                return s.getTeam2();
            }
        }
        return null;
    }
}
