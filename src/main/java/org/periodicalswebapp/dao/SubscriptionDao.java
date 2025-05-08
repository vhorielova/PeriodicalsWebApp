package org.periodicalswebapp.dao;

import org.periodicalswebapp.models.Subscription;

import java.util.List;

public interface SubscriptionDao {
    List<Subscription> getAllUserSubscriptions(int userId);
    void saveSubscription(Subscription subscription);
    void update();
}
