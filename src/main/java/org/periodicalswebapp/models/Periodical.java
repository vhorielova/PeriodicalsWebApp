package org.periodicalswebapp.models;

public class Periodical {
    private int id;
    private String name;
    private String type;
    private double halfYearPrice;
    private double fullYearPrice;

    public Periodical(){
    }

    public Periodical(String name, String type, double halfYearPrice, double fullYearPrice) {
        this.name = name;
        this.type = type;
        this.halfYearPrice = halfYearPrice;
        this.fullYearPrice = fullYearPrice;
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

    public double getHalfYearPrice() {
        return halfYearPrice;
    }

    public void setHalfYearPrice(double halfYearPrice) {
        this.halfYearPrice = halfYearPrice;
    }

    public double getFullYearPrice() {
        return fullYearPrice;
    }

    public void setFullYearPrice(double fullYearPrice) {
        this.fullYearPrice = fullYearPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
