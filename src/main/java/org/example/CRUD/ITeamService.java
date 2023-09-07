package org.example.CRUD;

import org.example.model.Team;

import java.util.List;

public interface ITeamService {
    List<Team> getAllTeam();
    Team findTeam(long id);
    List<Team> getAllTeamByTeamId(long idTournaments);
    String getTeamNameById(long idTeam);

    void updateTeam(long id, Team team);

    void deleteTeam(long id);

    void createTeam(Team team);
    Team searchName(String name);
    void sortName();
}
