package org.periodicalswebapp.models;

public class Periodical {
    private int id;
    private String name;

    public Periodical(){
    }

    public Periodical(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int setId(int id) {
        return this.id = id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        return this.name = name;
    }
}
