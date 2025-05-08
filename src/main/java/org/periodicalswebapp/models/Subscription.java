package org.periodicalswebapp.models;

import java.util.Date;

public class Subscription {
    private int id;
    private int userId;
    private int periodicalId;
    private int period;
    private Date createdAt;
    private boolean expired;

    public Subscription() {
    }

    public Subscription(int id, int userId, int periodicalId, int period) {
        this.id = id;
        this.userId = userId;
        this.periodicalId = periodicalId;
        this.period = period;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    public int getPeriodicalId() {
        return periodicalId;
    }

    public void setPeriodicalId(int periodicalId) {
        this.periodicalId = periodicalId;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
