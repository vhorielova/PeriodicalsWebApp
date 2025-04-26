package org.periodicalswebapp.dao;

import org.periodicalswebapp.models.User;

import java.sql.SQLException;

public interface UserDao {
    User updateUser(User user);
    User getUserByEmail(String email);
    void saveUser(User periodical);
    void deleteUser(User periodical);
}
