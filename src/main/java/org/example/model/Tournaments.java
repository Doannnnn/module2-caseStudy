package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Enum.ENumbersTeam;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tournaments implements IParseModel{
        private long id;
        private String name;
        private String address;
        private ENumbersTeam numbersTeam;

        @Override
        public Object parse(String line) {
                String[] items = line.split(",");
                Tournaments t = new Tournaments(Long.parseLong(items[0]),items[1], items[2], ENumbersTeam.valueOf(items[3]));
                return t;
        }
        public String toString() {
                return String.format("%s,%s,%s,%s", this.id, this.name, this.address, this.numbersTeam);
        }
}
