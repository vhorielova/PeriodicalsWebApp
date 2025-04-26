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
        System.out.println(request.getRequestURI()+" Get");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/register.jsp");
        dispatcher.forward(request, response);

    }

    public void handlePost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println(request.getRequestURI()+" Post");

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

        if(name.isEmpty() || lastname.isEmpty() || email.isEmpty() || address.isEmpty() || index.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            request.setAttribute("error", "Будь ласка, заповніть всі поля");
            handleGet(request, response);
        }
        else if(!password.equals(confirmPassword)) {
            request.setAttribute("error", "Пароль не співпадає");
            handleGet(request, response);
        }
        else {
            User user = new User(name, lastname, address, index, email, password);
            System.out.println(user.toString());
            userDao.saveUser(user);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/catalog.jsp");
            dispatcher.forward(request, response);
        }


    }
}
