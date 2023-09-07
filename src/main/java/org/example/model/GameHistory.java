package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Enum.ERound;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameHistory implements IParseModel{
    private long id;
    private long idTournaments;
    private long idSchedule;
    private int scoreTeam1;
    private int scoreTeam2;
    private ERound round;
    @Override
    public GameHistory parse(String line) {
        GameHistory gameHistory = null;
        String[] items = line.split(",");
        try {
            gameHistory = new GameHistory(Long.parseLong(items[0]), Long.parseLong(items[1]), Long.parseLong(items[2]), Integer.parseInt(items[3]),
                    Integer.parseInt(items[4]), ERound.valueOf(items[5]));
        } catch (Exception e){
            e.printStackTrace();
        }
        return gameHistory;
    }
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s", this.id, this.idTournaments, this.idSchedule, this.scoreTeam1, this.scoreTeam2, this.round);
    }
}
