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
public class Card implements IParseModel{
    private long id;
    private long idTournaments;
    private long idTeam;
    private long idSchedule;
    private long idPlayer;
    private ECard card;
    private int quantity;

    @Override
    public Card parse(String line) {
        Card card = null;
        String[] items = line.split(",");
        try {
            card = new Card(Long.parseLong(items[0]), Long.parseLong(items[1]),Long.parseLong(items[2]), Long.parseLong(items[3]),
                    Long.parseLong(items[4]), ECard.valueOf(items[5]), Integer.parseInt(items[6]));
        } catch (Exception e){
            e.printStackTrace();
        }
        return card;
    }
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s", this.id, this.idTournaments,this.idTeam, this.idSchedule, this.idPlayer, this.card, this.quantity);
    }
}
