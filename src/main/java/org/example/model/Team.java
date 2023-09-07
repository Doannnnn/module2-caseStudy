package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team implements IParseModel<Team> {
    private long id;
    private long idTournaments;
    private String name;
    private String coach;
    List<Player> playerList;
    @Override
    public Team parse(String line) {
        Team team = null;
        String[] items = line.split(",");
        try {
            team = new Team(Long.parseLong(items[0]), Long.parseLong(items[1]), items[2], items[3],null);
        } catch (Exception e){
            e.printStackTrace();
        }
        return team;
    }
    public String toString() {
        return String.format("%s,%s,%s,%s", this.id, this.idTournaments, this.name, this.coach);
    }
}
