package org.example.model;

import org.example.Enum.EGender;
import org.example.Enum.ERole;
import org.example.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements IParseModel<User> {
    private long id;
    private String name;
    private String userName;
    private String password;
    private int age;
    private LocalDate dob;
    private EGender gender;
    private ERole role;


    public User(long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public static void main(String[] args) {
    }

    @Override
    public User parse(String line) {
        String[] items = line.split(",");
        User u = new User(Long.parseLong(items[0]), items[1], items[2], items[3],Integer.parseInt(items[4]),
                DateUtils.parseDate(items[5]), EGender.valueOf(items[6]), ERole.valueOf(items[7]));
        return u;
    }

    @Override
    public String toString() {
        //3,Quang Dang2,123123,18,18-07-1992,MALE
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s", this.id, this.name, this.userName, this.password, this.age,
                DateUtils.formatDate(this.dob), this.gender, this.role);
    }
}