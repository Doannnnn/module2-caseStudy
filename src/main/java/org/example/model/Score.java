package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Enum.ECard;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Score implements IParseModel{
    private long id;
    private long idTournaments;
    private long idTeam;
    private long idSchedule;
    private long idPlayer;
    private int quantity;

    @Override
    public Score parse(String line) {
        Score score = null;
        String[] items = line.split(",");
        try {
            score = new Score(Long.parseLong(items[0]), Long.parseLong(items[1]),Long.parseLong(items[2]), Long.parseLong(items[3]),
                    Long.parseLong(items[4]), Integer.parseInt(items[5]));
        } catch (Exception e){
            e.printStackTrace();
        }
        return score;
    }
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s", this.id, this.idTournaments,this.idTeam, this.idSchedule, this.idPlayer, this.quantity);
    }
}
