package org.periodicalswebapp.daoimpl;

import org.periodicalswebapp.dao.UserDao;
import org.periodicalswebapp.models.User;
import org.periodicalswebapp.sql.ConnectionManager;

import javax.sql.DataSource;
import java.sql.*;

public class UserDaoImpl implements UserDao {
    private static final UserDaoImpl userDaoImpl = new UserDaoImpl();

    public UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return userDaoImpl;
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "select * from users where email like ?";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = ConnectionManager.getDataSource().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.executeQuery();
            conn.commit();

            ResultSet rs = stmt.getResultSet();
            if(rs.next()) {
                return new User(rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("address"),
                        rs.getString("index"),
                        rs.getString("email"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при пошуку користувача", e);
        }
        return null;
    }

    public User updateUser(User user) {
        String sql = "update users set name = ?, lastname = ?, address = ?, index = ?, email = ?, password = ? where id = ?";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = ConnectionManager.getDataSource().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getIndex());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getPassword());
            stmt.setInt(7, user.getId());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при додаванні користувача", e);
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        String sql = "insert into users (name, lastname, address, index, email, password) values (?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = ConnectionManager.getDataSource().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getIndex());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getPassword());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при додаванні користувача", e);
        }
    }

    @Override
    public void deleteUser(User user) {
        String sql = "delete from users where email = ?";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = ConnectionManager.getDataSource().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при видаленні користувача", e);
        }
    }
}
