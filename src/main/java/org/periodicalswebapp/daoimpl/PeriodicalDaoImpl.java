package org.periodicalswebapp.daoimpl;

import org.periodicalswebapp.dao.PeriodicalDao;
import org.periodicalswebapp.models.*;
import org.periodicalswebapp.sql.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PeriodicalDaoImpl implements PeriodicalDao {

    private static final PeriodicalDaoImpl periodicalDaoImpl = new PeriodicalDaoImpl();

    public PeriodicalDaoImpl() {
    }

    public static PeriodicalDaoImpl getInstance() {
        return periodicalDaoImpl;
    }

    @Override
    public List<Periodical> getAllPeriodicals() {
        String sql = "select * from periodicals";
        List<Periodical> periodicals = new ArrayList<>();
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = ConnectionManager.getDataSource().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            conn.commit();

            ResultSet rs = stmt.getResultSet();
            while(rs.next()) {
                Periodical periodical = new Periodical();
                periodical.setId(rs.getInt("id"));
                periodical.setName(rs.getString("name"));
                periodical.setHalfYearPrice(rs.getDouble("halfyearprice"));
                periodical.setFullYearPrice(rs.getDouble("fullyearprice"));
                periodicals.add(periodical);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при пошуку періодичних видань", e);
        }
        return periodicals;
    }

    @Override
    public Periodical getPeriodicalById(int id) {
        String sql = "select * from periodicals where id = ?";
        List<Periodical> periodicals = new ArrayList<>();
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = ConnectionManager.getDataSource().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeQuery();
            conn.commit();

            ResultSet rs = stmt.getResultSet();
            if(rs.next()) {
                Periodical periodical = new Periodical();
                periodical.setId(rs.getInt("id"));
                periodical.setName(rs.getString("name"));
                periodical.setType(rs.getString("type"));
                periodical.setHalfYearPrice(rs.getDouble("halfyearprice"));
                periodical.setFullYearPrice(rs.getDouble("fullyearprice"));
                periodicals.add(periodical);
                return periodical;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при пошуку періодичних видань", e);
        }
        return null;
    }

    @Override
    public void savePeriodical(Periodical periodical) {

    }

    @Override
    public void deletePeriodical(Periodical periodical) {

    }
}
