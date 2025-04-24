package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.periodicalswebapp.daoimpl.UserDaoImpl;
import org.periodicalswebapp.models.User;

import java.io.IOException;

public class LoginHandler implements Handler{

    private final UserDaoImpl userDao = UserDaoImpl.getInstance();

    public void handleGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println(request.getRequestURI()+" Get");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(request, response);

    }
    public void handlePost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println(request.getRequestURI()+" Post");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDao.getUserByEmail(email);

        if(user != null && user.getPassword().equals(password)) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        else {
            request.setAttribute("error", "Wrong email or password");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
}
