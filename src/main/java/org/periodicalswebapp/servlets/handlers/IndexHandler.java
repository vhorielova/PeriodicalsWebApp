package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class IndexHandler implements Handler{
    public void handleGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println(request.getRequestURI()+" Get");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
        dispatcher.forward(request, response);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "index handler" + "</h1>");
        out.println("</body></html>");
    }

    public void handlePost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println(request.getRequestURI()+" Post");
    }
}
