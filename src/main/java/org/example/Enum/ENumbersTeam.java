package org.example.Enum;

public enum ENumbersTeam {
    FOUR(1, "4 đội"),EIGHT(2, "8 đội");
    private ENumbersTeam(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ENumbersTeam findById(long id) {
        for (ENumbersTeam e : values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
    private long id;
    private String name;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
