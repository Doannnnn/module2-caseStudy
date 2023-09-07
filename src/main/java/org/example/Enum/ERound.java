package org.example.Enum;

public enum ERound {
    QUALIFYINGROUND(1, "Vòng loại"),QUARTERFINALS(2, "Tứ kết"),SEMIFINAL(3, "Bán kết"),FINAL(4, "Chung kết");
    private ERound(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ERound findById(long id) {
        for (ERound e : values()) {
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
