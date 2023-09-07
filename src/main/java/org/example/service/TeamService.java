package org.example.service;

import org.example.CRUD.ITeamService;
import org.example.model.Team;
import org.example.utils.FileUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TeamService implements ITeamService {
    private final String fileTeam = "./data/team.csv";
    @Override
    public List<Team> getAllTeam() {
        return FileUtils.readData(fileTeam, Team.class);
    }

    @Override
    public Team findTeam(long id) {
        List<Team> teams = getAllTeam();
        return teams.stream().filter(team -> team.getId() == id).findFirst().orElseThrow(null);
    }
    public List<Team> getAllTeamByTeamId(long idTournaments) {
        List<Team> teams = getAllTeam();
        List<Team> results = teams.stream()
                .filter(team -> team.getIdTournaments() == idTournaments)
                .collect(Collectors.toList());
        return results;
    }
    public String getTeamNameById(long idTeam){
        List<Team> teams = getAllTeam();
        for (Team t : teams){
            if (t.getId() == idTeam){
                return t.getName();
            }
        }
        return null;
    }
    @Override
    public void updateTeam(long id, Team team) {
        List<Team> teams = getAllTeam();
        for (Team t : teams){
            if(t.getId() == id){
                t.setName(team.getName());
                t.setCoach(team.getCoach());
                t.setIdTournaments(team.getIdTournaments());
            }
        }
        FileUtils.writeData(fileTeam, teams);
    }

    @Override
    public void deleteTeam(long id) {
        List<Team> teams = getAllTeam();
        Team team = null;
        for (Team t : teams){
            if (t.getId() == id){
                team = t;
            }
        }
        teams.remove(team);
        FileUtils.writeData(fileTeam, teams);
    }

    @Override
    public void createTeam(Team team) {
        List<Team> teams = getAllTeam();
        team.setId(teams.size() + 1);
        teams.add(team);

        FileUtils.writeData(fileTeam, teams);
    }

    @Override
    public Team searchName(String name) {
        List<Team> teams = getAllTeam();
        if (!teams.stream().anyMatch(team -> team.getName().equalsIgnoreCase(name))){
            System.out.println("Không tìm thấy tên " + name);
        } else {
            System.out.println("Kết quả tìm kiếm: ");
            return teams.stream().filter(team -> team.getName().equalsIgnoreCase(name)).findFirst().get();
        }
        return null;
    }

    @Override
    public void sortName() {
        List<Team> teams = getAllTeam();
        System.out.println("+------------+----------------------+----------------------+");
        System.out.printf("| %10s | %20s | %20s |\n", "ID", "Name", "HVL");
        System.out.println("+------------+----------------------+----------------------+");
        teams.stream().sorted(Comparator.comparing(Team::getName)).forEach(t -> {
            System.out.printf("%10s | %20s | %20s \n",
                    t.getId(), t.getName(), t.getCoach());
        });
        System.out.println("+------------+----------------------+----------------------+");
    }

}

