package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.periodicalswebapp.models.User;

import java.io.IOException;

public class RegisterHandler implements Handler{
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

        if(name == null || lastname == null || email == null || address == null || index == null || password == null || confirmPassword == null) {
            request.setAttribute("error", "Please fill all the fields");
            handleGet(request, response);
        }
        else if(!password.equals(confirmPassword)) {
            request.setAttribute("error", "The passwords do not match");
            handleGet(request, response);
        }
        else {
            User user = new User(name, lastname, email, index, address, index);
            System.out.println(user.toString());
            response.sendRedirect("/WEB-INF/login.jsp");
        }


    }
}
