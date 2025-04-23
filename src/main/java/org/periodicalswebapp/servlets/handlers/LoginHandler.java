package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginHandler implements Handler{
    public void handleGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println(request.getRequestURI()+" Get");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(request, response);

    }
    public void handlePost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println(request.getRequestURI()+" Post");
    }
}
