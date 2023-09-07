package org.example.service;

import org.example.CRUD.ITeamService;
import org.example.model.Schedule;
import org.example.model.Team;
import org.example.utils.FileUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ScheduleService{
    private ITeamService iTeamService = new TeamService();
    public final String fileSchedule = "./data/schedule.csv";
    public List<Schedule> getAllSchedule() {
        return FileUtils.readData(fileSchedule, Schedule.class);
    }
    public List<Schedule> getAllScheduleByTeamId(long idTournaments) {
        List<Schedule> schedules = getAllSchedule();
        List<Schedule> results = schedules.stream()
                .filter(schedule -> schedule.getIdTournaments() == idTournaments)
                .collect(Collectors.toList());
        return results;
    }
    public void createSchedule(Schedule schedule) {
        List<Schedule> schedules = getAllSchedule();
        schedule.setId(schedules.size() + 1);
        schedules.add(schedule);

        FileUtils.writeData(fileSchedule, schedules);
    }
    public void updateSchedule(long id, String team1, String team2) {
        List<Schedule> schedules = getAllSchedule();
        for (Schedule s : schedules){
            if(s.getId() == id){
                s.setTeam1(team1);
                s.setTeam2(team2);
            }
        }
        FileUtils.writeData(fileSchedule, schedules);
    }
    public void updateScheduleTeam2(long id, String team2) {
        List<Schedule> schedules = getAllSchedule();
        for (Schedule s : schedules){
            if(s.getId() == id){
                s.setTeam2(team2);
            }
        }
        FileUtils.writeData(fileSchedule, schedules);
    }
}
