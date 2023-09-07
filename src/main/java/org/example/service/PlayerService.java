package org.example.service;

import org.example.CRUD.IPlayerService;
import org.example.model.Player;
import org.example.model.Team;
import org.example.utils.FileUtils;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerService implements IPlayerService {
    private final String filePlayer = "./data/player.csv";
    @Override
    public List<Player> getAllPlayer() {
        return FileUtils.readData(filePlayer, Player.class);
    }

    @Override
    public Player findPlayer(long id) {
        List<Player> players = getAllPlayer();
        return players.stream().filter(player -> player.getId() == id).findFirst().orElseThrow(null);
    }
    public List<Player> getAllPlayersByTeamId(long idTeam) {
        List<Player> players = getAllPlayer();
        List<Player> results = players.stream()
                .filter(player -> player.getIdTeam() == idTeam)
                .collect(Collectors.toList());
        return results;
    }
    public String getNamePlayersByTeamId(long idPlayer) {
        List<Player> players = getAllPlayer();
        for (Player p : players){
            if (p.getId() == idPlayer){
                return p.getName();
            }
        }
        return null;
    }

    @Override
    public void updatePlayer(long id, Player player) {
        List<Player> players = getAllPlayer();
        for (Player p : players){
            if (p.getId() == id){
                p.setName(player.getName());
                p.setAge(player.getAge());
                p.setPosition(player.getPosition());
            }
        }
        FileUtils.writeData(filePlayer, players);
    }

    @Override
    public void deletePlayer(long id) {
        List<Player> players = getAllPlayer();
        Player player = null;
        for (Player p : players){
            if (p.getId() == id){
                player = p;
            }
        }
        players.remove(player);
        FileUtils.writeData(filePlayer, players);
    }

    @Override
    public void createPlayer(Player player) {
        List<Player> players = getAllPlayer();
        player.setId(players.size() + 1);
        players.add(player);
        FileUtils.writeData(filePlayer, players);
    }

    @Override
    public Player searchName(String name) {
        List<Player> players = getAllPlayer();
        if (!players.stream().anyMatch(player -> player.getName().equalsIgnoreCase(name))){
            System.out.println("Không tìm thấy tên " + name);
        } else {
            System.out.println("Kết quả tìm kiếm: ");
            return players.stream().filter(player -> player.getName().equalsIgnoreCase(name)).findFirst().get();
        }
        return null;
    }
}
