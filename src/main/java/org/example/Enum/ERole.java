package org.example.Enum;

public enum ERole {
    ADMIN(1, "ADMIN"),USER(2, "USER");
    private ERole(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ERole findById(long id) {
        for (ERole e : values()) {
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
