package org.periodicalswebapp.daoimpl;

import org.periodicalswebapp.dao.SubscriptionDao;
import org.periodicalswebapp.models.Periodical;
import org.periodicalswebapp.models.Subscription;
import org.periodicalswebapp.sql.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao {
    private static final SubscriptionDaoImpl SubscriptionDao = new SubscriptionDaoImpl();

    public SubscriptionDaoImpl() {

    }

    public List <Subscription> getAllUserSubscriptions(int userId) {
        String sql = "select * from subscriptions where user_id = ?";
        List<Subscription> subscriptions = new ArrayList<>();
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = ConnectionManager.getDataSource().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.executeQuery();
            conn.commit();

            ResultSet rs = stmt.getResultSet();
            while(rs.next()) {
                Subscription subscription = new Subscription();
                subscription.setId(rs.getInt("id"));
                subscription.setUserId(rs.getInt("user_id"));
                subscription.setPeriodicalId(rs.getInt("periodical_id"));
                subscription.setPeriod(rs.getInt("period"));
                subscription.setCreatedAt(rs.getDate("created_at"));
                subscription.setExpired(rs.getBoolean("expired"));
                subscriptions.add(subscription);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при пошуку періодичних видань", e);
        }
        return subscriptions;
    }

    public void saveSubscription(Subscription subscription) {
        String sql = "insert into subscriptions (user_id, periodical_id, period) values (?,?,?)";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = ConnectionManager.getDataSource().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, subscription.getUserId());
            stmt.setInt(2, subscription.getPeriodicalId());
            stmt.setInt(3, subscription.getPeriod());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при додаванні підписки", e);
        }
    }

    public void deleteSubscription(int subscriptionId) {
        String sql = "delete from subscriptions where id = ?";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = ConnectionManager.getDataSource().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, subscriptionId);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при видаленні підписки", e);
        }
    }

    public void update() {
        String sql = "update subscriptions\n" +
                "set expired = true\n" +
                "where (created_at + (period || ' months')::interval) < NOW();";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = ConnectionManager.getDataSource().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при оновленні підписок", e);
        }
    }
}
