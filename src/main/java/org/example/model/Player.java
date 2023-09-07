package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.utils.DateUtils;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player implements IParseModel{
    private long id;
    private long idTeam;
    private String name;
    private LocalDate dob;
    private int age;
    private String position;

    @Override
    public Object parse(String line) {
        String[] items = line.split(",");
        Player p = new Player(Long.parseLong(items[0]),Long.parseLong(items[1]), items[2], DateUtils.parseDate(items[3]),
                Integer.parseInt(items[4]), items[5]);
        return p;
    }
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s", this.id, this.idTeam, this.name, DateUtils.formatDate(this.dob), this.age, this.position);
    }
}
