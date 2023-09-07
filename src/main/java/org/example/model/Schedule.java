package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Enum.ERound;
import org.example.utils.DateUtils;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule implements IParseModel{
    private long id;
    private long idTournaments;
    private String team1;
    private String team2;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private ERound round;

    @Override
    public Schedule parse(String line) {
        Schedule schedule = null;
        String[] items = line.split(",");
        try {
            schedule = new Schedule(Long.parseLong(items[0]), Long.parseLong(items[1]),items[2], items[3], DateUtils.parseDateTime(items[4]),
                    DateUtils.parseDateTime(items[5]), ERound.valueOf(items[6]));
        } catch (Exception e){
            e.printStackTrace();
        }
        return schedule;
    }
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s", this.id, this.idTournaments, this.team1, this.team2, DateUtils.formatDateTime(this.timeStart),
                DateUtils.formatDateTime(this.timeEnd), this.round);
    }
}
