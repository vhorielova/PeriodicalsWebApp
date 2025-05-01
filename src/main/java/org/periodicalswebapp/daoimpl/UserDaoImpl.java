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
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setAddress(rs.getString("address"));
                user.setIndex(rs.getString("index"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при пошуку користувача", e);
        }
        return null;
    }

    public User updateUser(User user) {
        String sql = "update users set name = ?, lastname = ?, address = ?, index = ?, password = ? where email = ?";
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
            stmt.setString(5, user.getPassword());
            stmt.setString(6, user.getEmail());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при оновлені користувача", e);
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

    @Override
    public boolean isUserAdmin(User user) {
        String sql = "select * from admin where email = ?";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = ConnectionManager.getDataSource().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.executeQuery();
            conn.commit();
            ResultSet rs = stmt.getResultSet();
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка при видаленні користувача", e);
        }
        return false;
    }
}
