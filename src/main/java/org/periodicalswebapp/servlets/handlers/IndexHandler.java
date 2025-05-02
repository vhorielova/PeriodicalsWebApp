package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class IndexHandler implements Handler{
    public void handleGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
//        System.out.println(session.getAttribute("user"));
        if(session.getAttribute("user") == null) {
            request.setAttribute("loggedIn", false);
        }
        else {
            request.setAttribute("loggedIn", true);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
        dispatcher.forward(request, response);
    }

    public void handlePost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }
}
