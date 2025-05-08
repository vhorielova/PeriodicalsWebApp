package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.periodicalswebapp.daoimpl.UserDaoImpl;
import org.periodicalswebapp.models.User;

import java.io.IOException;

public class RegisterHandler implements Handler{

    private final UserDaoImpl userDao = UserDaoImpl.getInstance();

    public void handleGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/register.jsp");
        dispatcher.forward(request, response);

    }

    public void handlePost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String index = request.getParameter("index");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        request.setAttribute("name", name);
        request.setAttribute("lastname", lastname);
        request.setAttribute("email", email);
        request.setAttribute("address", address);
        request.setAttribute("index", index);
        request.setAttribute("password", password);
        request.setAttribute("confirmPassword", confirmPassword);

        if(userDao.getUserByEmail(email) != null) {
            request.setAttribute("error", "Користувач з даним емейлом вже зареєстрований");
            handleGet(request, response);
        }
        else if(name.isEmpty() || lastname.isEmpty() || email.isEmpty() || address.isEmpty() || index.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            request.setAttribute("error", "Будь ласка, заповніть всі поля");
            handleGet(request, response);
        }
        else if(!password.equals(confirmPassword)) {
            request.setAttribute("error", "Пароль не співпадає");
            handleGet(request, response);
        }
        else {
            User newUser = new User(name, lastname, address, index, email, password);
            userDao.saveUser(newUser);

            User user = userDao.getUserByEmail(email);
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(-1);
            session.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/catalog.jsp");
            dispatcher.forward(request, response);
        }


    }
}
