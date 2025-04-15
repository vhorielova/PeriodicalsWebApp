package org.periodicalswebapp.models;

public class Subscription {
    private int id;
    private int user_id;
    private int periodical_id;

    public Subscription() {
    }

    public Subscription(int id, int user_id, int periodical_id) {
        this.id = id;
        this.user_id = user_id;
        this.periodical_id = periodical_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPeriodical_id() {
        return periodical_id;
    }

    public void setPeriodical_id(int periodical_id) {
        this.periodical_id = periodical_id;
    }
}
