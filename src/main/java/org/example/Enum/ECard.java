package org.example.Enum;

public enum ECard {
    YELLOW(1, "Thẻ vàng"),RED(2, "Thẻ đỏ");
    private ECard(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ECard findById(long id) {
        for (ECard e : values()) {
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
