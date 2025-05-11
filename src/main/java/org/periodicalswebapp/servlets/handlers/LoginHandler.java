package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.periodicalswebapp.daoimpl.UserDaoImpl;
import org.periodicalswebapp.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

public class LoginHandler implements Handler{

    private final UserDaoImpl userDao = UserDaoImpl.getInstance();
    Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    public void handleGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("Handling GET request for login page.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(request, response);

    }
    public void handlePost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        logger.info("Handling POST request for login.");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        request.setAttribute("email", email);
        request.setAttribute("password", password);

        logger.debug("Attempting to find user by email: {}", email);
        User user = userDao.getUserByEmail(email);

        if(user != null && user.getPassword().equals(password)) {
            logger.info("User '{}' logged in successfully.", email);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(-1);
            response.sendRedirect("/");
        }
        else {
            logger.warn("Login failed for email '{}': invalid email or password.", email);
            request.setAttribute("error", "Неправильний емейл чи пароль");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
}
