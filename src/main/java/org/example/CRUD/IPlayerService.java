package org.example.CRUD;

import org.example.model.Player;

import java.util.List;

public interface IPlayerService {
    List<Player> getAllPlayer();
    Player findPlayer(long id);
    List<Player> getAllPlayersByTeamId(long idTeam);
    void updatePlayer(long id, Player player);
    void deletePlayer(long id);
    void createPlayer(Player player);
    Player searchName(String name);

}
